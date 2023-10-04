package Laboral;

/**
 * Es la clase padre
 */
public class Persona {
	String nombre, dni;
	char sexo;

	/**
	 * Constructor sobrecargado con todos los atributos
	 * 
	 * @param nombre es el nombre
	 * @param dni  es el dni
	 * @param sexo es el sexo
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
	 * @param nombre es el nombre
	 * @param sexo es el sexo
	 */
	public Persona(String nombre, char sexo) {
		super();
		this.nombre = nombre;
		this.sexo = sexo;
	}

	/**
	 * Establece el valor del atributo dni
	 * 
	 * @param dni  es el dni
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
