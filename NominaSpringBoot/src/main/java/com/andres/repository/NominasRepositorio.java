package com.andres.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andres.model.Nomina;

/**
 * Repositorio para la entidad Nomina, que permite realizar operaciones CRUD en
 * la base de datos.
 * @author Andrés Jesús
 */
public interface NominasRepositorio extends JpaRepository<Nomina, UUID> {

	/**
	 * Busca una nómina por el DNI del empleado asociado.
	 * 
	 * @param dni El DNI del empleado asociado a la nómina.
	 * @return La nómina asociada al empleado con el DNI dado.
	 */
	Nomina findByEmpleado_Dni(String dni);
}
