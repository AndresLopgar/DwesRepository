package Laboral;

/**
 * @Param nombre
 * @param dni
 * @param sexo
 */
public class Persona {
	String nombre, dni;
	char sexo;

	/**
	 * Constructor sobrecargado con todos los atributos
	 * 
	 * @param nombre
	 * @param dni
	 * @param sexo
	 */
	public Persona(String nombre, String dni, char sexo) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.sexo = sexo;
	}

	/**
	 * constructor sobrecargado con nombre y sexo
	 * 
	 * @param nombre
	 * @param sexo
	 */
	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	/**
	 * Establece el valor del atributo dni
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Este metodo saca por pantalla la información de la persona
	 */
	public void Imprime() {
		System.out.println("El nombre de la persona es: " + nombre + ", y su DNI es: " + dni);
	}

}
