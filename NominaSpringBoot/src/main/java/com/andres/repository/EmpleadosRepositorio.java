package com.andres.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.andres.model.Empleados;

/**
 * Repositorio para la entidad Empleados, permite realizar operaciones CRUD en
 * la base de datos.
 * @author Andrés Jesús
 */
@Repository
public interface EmpleadosRepositorio extends JpaRepository<Empleados, String> {

	/**
	 * Verifica si existe un empleado con un DNI específico.
	 * 
	 * @param dni El DNI del empleado a buscar.
	 * @return Verdadero si existe un empleado con el DNI dado, falso en caso
	 *         contrario.
	 */
	public boolean existsByDni(String dni);

	/**
	 * Elimina a un empleado por su DNI.
	 * 
	 * @param dni El DNI del empleado a eliminar.
	 */
	public void deleteByDni(String dni);

	/**
	 * Busca un empleado por su DNI.
	 * 
	 * @param dni El DNI del empleado a buscar.
	 * @return El empleado con el DNI dado, si existe.
	 */
	Optional<Empleados> findByDni(String dni);
}
