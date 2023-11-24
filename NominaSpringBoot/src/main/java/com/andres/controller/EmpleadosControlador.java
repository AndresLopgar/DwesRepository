package com.andres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.andres.model.*;
import com.andres.services.EmpleadosServicio;

/**
 * Controlador para operaciones relacionadas con empleados.
 * @author Andrés Jesús
 */
@Controller
public class EmpleadosControlador {

	@Autowired
	private EmpleadosServicio servicioEmpleado; // Servicio para operaciones de empleados

	/**
	 * Muestra la página de menú.
	 * 
	 * @return La vista del menú.
	 */
	@GetMapping({ "/", "/menu" })
	public String muestraMenu() {
		return "/menu";
	}
	
	@GetMapping({"/header"})
	public String muestraHeader(){
		return "/header";
	}
	
	@GetMapping({"/footer"})
	public String muestraFooter(){
		return "/footer";
	}

	/**
	 * Lista todos los empleados.
	 * 
	 * @param modelo El modelo para la vista.
	 * @return La vista de la lista de empleados.
	 */
	@GetMapping({ "/empleados/listar" })
	public String listaEmpleados(Model modelo) {
		modelo.addAttribute("listaEmpleados", servicioEmpleado.listaEmpleados());
		return "listaEmpleados";
	}

	/**
	 * Muestra el formulario para crear un empleado.
	 * 
	 * @param modelo El modelo para la vista.
	 * @return La vista para crear un empleado.
	 */
	@GetMapping({ "/empleados/crear" })
	public String muestraFormularioCreaEmpleado(Model modelo) {
		Empleados empleado = new Empleados();
		modelo.addAttribute("empleado", empleado);
		return "creaEmpleado";
	}

	/**
	 * Crea un nuevo empleado.
	 * 
	 * @param empleado El objeto Empleado a crear.
	 * @return Redirecciona al menú.
	 */
	@PostMapping({ "/empleados" })
	public String creaEmpleado(@ModelAttribute("empleado") Empleados empleado) {
		servicioEmpleado.creaEmpleado(empleado);
		return "redirect:/menu";
	}

	/**
	 * Muestra el formulario para editar un empleado.
	 * 
	 * @param dni    El DNI del empleado a editar.
	 * @param modelo El modelo para la vista.
	 * @return La vista para editar un empleado o redirecciona si no existe.
	 */
	@GetMapping({ "/empleados/editar/{dni}" })
	public String muestraFormularioEditar(@PathVariable String dni, Model modelo) {
		boolean empleadoExistente = servicioEmpleado.existeEmpleadoConDni(dni);
		if (empleadoExistente) {
			Empleados empleado = servicioEmpleado.obtieneEmpleadoDni(dni);
			modelo.addAttribute("empleado", empleado);
			return "editaEmpleado";
		} else {
			modelo.addAttribute("error", "El empleado con DNI " + dni + " no existe.");
			return "redirect:/empleados/listar";
		}
	}

	/**
	 * Actualiza un empleado existente.
	 * 
	 * @param dni      El DNI del empleado a actualizar.
	 * @param empleado El objeto Empleado con los cambios.
	 * @param modelo   El modelo para la vista.
	 * @return Redirecciona a la lista de empleados o muestra un error.
	 */
	@PostMapping("/empleados/{dni}")
	public String actualizaEmpleado(@PathVariable String dni, @ModelAttribute("empleado") Empleados empleado,
			Model modelo) {
		boolean empleadoExistente = servicioEmpleado.existeEmpleadoConDni(dni);

		if (empleadoExistente) {
			servicioEmpleado.actualizaEmpleado(empleado);
		} else {
			modelo.addAttribute("error", "El empleado con DNI " + dni + " no existe.");
			return "editaEmpleado";
		}
		return "redirect:/empleados/listar";
	}

	/**
	 * Elimina un empleado.
	 * 
	 * @param dni El DNI del empleado a eliminar.
	 * @return Redirecciona a la lista de empleados.
	 */
	@GetMapping("/empleados/{dni}")
	public String eliminaEmpleado(@PathVariable String dni) {
		servicioEmpleado.eliminaEmpleado(dni);
		return "redirect:/empleados/listar";
	}

	/**
	 * Muestra el formulario para buscar salarios.
	 * 
	 * @return La vista para buscar salarios.
	 */
	@GetMapping("/buscar/salarios")
	public String muestraFormularioBuscaSalarios() {
		return "buscaSalarios";
	}

	/**
	 * Busca el salario de un empleado por su DNI.
	 * 
	 * @param dni    El DNI del empleado.
	 * @param modelo El modelo para la vista.
	 * @return La vista con el resultado de la búsqueda.
	 */
	@PostMapping("/buscar/salarios")
	public String buscaSalario(@RequestParam String dni, Model modelo) {
		Empleados empleado = servicioEmpleado.obtieneEmpleadoDni(dni);

		if (empleado != null) {
			List<Nomina> nominas = servicioEmpleado.obtieneNomina(empleado);
			if (!nominas.isEmpty()) {
				modelo.addAttribute("empleado", empleado);
				modelo.addAttribute("sueldo", nominas.get(0).getSueldo());
			} else {
				modelo.addAttribute("error", "No hay nóminas para el empleado con DNI " + dni);
				modelo.addAttribute("sueldo", null);
			}
		} else {
			modelo.addAttribute("empleadoNoEncontrado", true);
			modelo.addAttribute("error", "No se encontró ningún empleado con DNI " + dni);
		}
		return "buscaSalarios";
	}
}
