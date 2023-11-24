package com.andres.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

/**
 * Clase que representa a los empleados.
 * @author Andrés Jesús
 */
@Entity
@Table(name = "Empleados")
public class Empleados {

	@Id
	@Pattern(regexp = "\\d{8}[A-Z]", message = "El DNI debe tener 8 dígitos seguidos de una letra mayúscula")
	private String dni; // DNI del empleado

	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(max = 70, message = "El nombre no puede tener más de 70 caracteres")
	private String nombre; // Nombre del empleado

	@Pattern(regexp = "[MF]", message = "El sexo debe ser 'M' o 'F'")
	private String sexo; // Sexo del empleado

	@Min(value = 1, message = "La categoría debe ser al menos 1")
	@Max(value = 10, message = "La categoría no puede ser mayor que 10")
	private Integer categoria; // Categoría del empleado

	@Min(value = 0, message = "Los años deben ser al menos 0")
	private Integer anyos; // Años de experiencia del empleado

	public Empleados() {
	}

	/**
	 * Constructor de Empleados.
	 * 
	 * @param dni       DNI del empleado.
	 * @param nombre    Nombre del empleado.
	 * @param sexo      Sexo del empleado.
	 * @param categoria Categoría del empleado.
	 * @param anyos     Años de experiencia del empleado.
	 */
	public Empleados(String dni, String nombre, String sexo, Integer categoria, Integer anyos) {
		// Verifica si la categoría está en el rango permitido
		if (categoria >= 1 && categoria <= 10) {
			this.categoria = categoria;
		}

		this.dni = dni;
		this.nombre = nombre;
		this.sexo = sexo;
		this.anyos = anyos;
	}

	// Getters y setters

	/**
	 * Obtiene el DNI del empleado.
	 * 
	 * @return DNI del empleado.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Establece el DNI del empleado.
	 * 
	 * @param dni DNI del empleado.
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Obtiene el nombre del empleado.
	 * 
	 * @return Nombre del empleado.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del empleado.
	 * 
	 * @param nombre Nombre del empleado.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el sexo del empleado.
	 * 
	 * @return Sexo del empleado.
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Establece el sexo del empleado.
	 * 
	 * @param sexo Sexo del empleado.
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtiene la categoría del empleado.
	 * 
	 * @return Categoría del empleado.
	 */
	public Integer getCategoria() {
		return categoria;
	}

	/**
	 * Establece la categoría del empleado.
	 * 
	 * @param categoria Categoría del empleado.
	 */
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	/**
	 * Obtiene los años de experiencia del empleado.
	 * 
	 * @return Años de experiencia del empleado.
	 */
	public Integer getAnyos() {
		return anyos;
	}

	/**
	 * Establece los años de experiencia del empleado.
	 * 
	 * @param anyos Años de experiencia del empleado.
	 */
	public void setAnyos(Integer anyos) {
		this.anyos = anyos;
	}
}
