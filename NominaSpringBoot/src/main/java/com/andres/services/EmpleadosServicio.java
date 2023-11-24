package com.andres.services;

import java.util.List;
import com.andres.model.*;

/**
 * Interfaz que define las operaciones del servicio para los empleados.
 * @author Andrés Jesús
 */
public interface EmpleadosServicio {

    /**
     * Obtiene una lista de todos los empleados.
     * @return Lista de empleados.
     */
    public List<Empleados> listaEmpleados();

    /**
     * Guarda un nuevo empleado.
     * @param empleado El empleado a guardar.
     * @return El empleado guardado.
     */
    public Empleados creaEmpleado(Empleados empleado);

    /**
     * Verifica si existe un empleado con un DNI específico.
     * @param dni El DNI del empleado a buscar.
     * @return Verdadero si el empleado con el DNI dado existe, falso en caso contrario.
     */
    boolean existeEmpleadoConDni(String dni);

    /**
     * Obtiene un empleado con un DNI específico.
     * @param dni El DNI del empleado a buscar.
     * @return El empleado con el DNI dado, o null si no se encuentra.
     */
    public Empleados obtieneEmpleadoDni(String dni);

    /**
     * Actualiza la información de un empleado existente.
     * @param empleado El empleado con la información actualizada.
     * @return El empleado actualizado.
     */
    public Empleados actualizaEmpleado(Empleados empleado);

    /**
     * Elimina a un empleado dado su DNI.
     * @param dni El DNI del empleado a eliminar.
     */
    public void eliminaEmpleado(String dni);

    /**
     * Obtiene las nóminas de un empleado.
     * @param empleado El empleado del que se desean obtener las nóminas.
     * @return Lista de nóminas del empleado.
     */
    List<Nomina> obtieneNomina(Empleados empleado);
}
