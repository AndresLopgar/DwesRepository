package com.andres.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andres.model.Empleados;
import com.andres.model.Nomina;
import com.andres.repository.EmpleadosRepositorio;
import com.andres.repository.NominasRepositorio;

/**
 * Implementación del servicio para operaciones relacionadas con empleados.
 * @author Andrés Jesús
 */
@Service
public class EmpleadosServicioImpl implements EmpleadosServicio {

    @Autowired
    private EmpleadosRepositorio repositorioEmpleado; // Repositorio para empleados

    @Autowired
    private NominasRepositorio repositorioNominas; // Repositorio para nóminas

    @Autowired
    private Validator validator; // Validador para validación de objetos

    @Override
    public List<Empleados> listaEmpleados() {
        return repositorioEmpleado.findAll(); // Devuelve todos los empleados
    }

    @Override
    public Empleados creaEmpleado(Empleados empleado) {
        if (existeEmpleadoConDni(empleado.getDni())) {
            throw new RuntimeException("¡Error! El empleado con DNI " + empleado.getDni() + " ya está registrado.");
        }

        Set<ConstraintViolation<Empleados>> violations = validator.validate(empleado);

        if (violations.isEmpty()) {
            Empleados empleadoGuardado = repositorioEmpleado.save(empleado);

            Nomina nomina = new Nomina(empleadoGuardado);
            repositorioNominas.save(nomina);
            return empleadoGuardado;
        } else {
            throw new RuntimeException("Error de validación: " + violations.toString());
        }
    }

    @Override
    public boolean existeEmpleadoConDni(String dni) {
        return repositorioEmpleado.existsByDni(dni); // Verifica si existe un empleado con el DNI dado
    }

    @Override
    public Empleados obtieneEmpleadoDni(String dni) {
        Optional<Empleados> optionalEmpleado = repositorioEmpleado.findByDni(dni);
        return optionalEmpleado.orElse(null); // Obtiene un empleado por su DNI si existe
    }

    @Override
    public Empleados actualizaEmpleado(Empleados empleado) {
        if (!repositorioEmpleado.existsByDni(empleado.getDni())) {
            throw new RuntimeException("¡Error! El empleado con DNI " + empleado.getDni() + " no está registrado.");
        }

        Set<ConstraintViolation<Empleados>> violations = validator.validate(empleado);

        if (violations.isEmpty()) {
            Nomina nomina = repositorioNominas.findByEmpleado_Dni(empleado.getDni());

            if (nomina != null) {
                nomina.setSueldo(nomina.calcularSueldo(empleado));
                repositorioNominas.save(nomina);
            } else {
                throw new RuntimeException("¡Error! La nómina para el empleado con DNI " + empleado.getDni() + " no está registrada.");
            }
            return repositorioEmpleado.save(empleado);
        } else {
            throw new RuntimeException("Error de validación: " + violations.toString());
        }
    }

    @Override
    @Transactional 
    public void eliminaEmpleado(String dni) {
        Optional<Empleados> empleado = repositorioEmpleado.findByDni(dni);
        if (!empleado.isPresent()) {
            throw new RuntimeException("¡Error! El empleado con DNI " + dni + " no está registrado.");
        }
        Nomina nomina = repositorioNominas.findByEmpleado_Dni(dni);
        if (nomina != null) {
            repositorioNominas.delete(nomina);
        }
        repositorioEmpleado.deleteByDni(dni);
    }

    @Override
    public List<Nomina> obtieneNomina(Empleados empleado) {
        if (empleado != null) {
            Nomina nomina = repositorioNominas.findByEmpleado_Dni(empleado.getDni());

            return Collections.singletonList(nomina);
        } else {
            return Collections.emptyList();
        }
    }
}
