package Laboral;

/**
 * Esta clase extiende de persona
 */
public class Empleado extends Persona {

	private int categoria;
	double anyosTrabajados;

	/**
	 * Este constructor está sobrecargado por la clase padre
	 * 
	 * @param nombre es el nombre 
	 * @param dni es el dni
	 * @param sexo es el sexo
	 * @param categoria es la categoria del empleado
	 * @param anyosTrabajados son los anyos trabajados del empleado
	 * @throws DatosNoCorrectosException cuando la categoria o los anyosTrabajados sean incorrectos.
	 */
	public Empleado(String nombre, String dni, char sexo, int categoria, double anyosTrabajados)
			throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("La categoria debe ser un numero entre 1 y 10");
		} else {
			this.categoria = categoria;
		}

		if (anyosTrabajados < 0) {
			throw new DatosNoCorrectosException("Los anyos deben ser un numero positivo");
		} else {
			this.anyosTrabajados = anyosTrabajados;
		}

	}// fin del constructor de empleado

	/**
	 * Este es el constructor sobrecargado con los atributos del empleado
	 * 
	 * @param nombre es el nombre
	 * @param dni es el dni
	 * @param sexo es el sexo
	 * @throws DatosNoCorrectosException cuando la categoria o los anyosTrabajados sean incorrectos.
	 */
	public Empleado(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		categoria = 1;
		anyosTrabajados = 0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene el valor de la categoria
	 * 
	 * @return El valor del atributo categoria
	 */
	public int getCategoria() {
		return categoria;
	}

	/**
	 * Establece el valor del atributo categoria
	 * 
	 * @param categoria es la categoria
	 * @throws DatosNoCorrectosException cuando la categoria sea incorrecta.
	 */
	public void setCategoria(int categoria) throws DatosNoCorrectosException {
		if (categoria < 1 || categoria > 10) {
			throw new DatosNoCorrectosException("La categoria debe ser un numero entre 1 y 10");
		} else {
			this.categoria = categoria;
		}
	}

	/**
	 * Este metodo incrementa un anyoTrabajado al empleado
	 */
	public void IncrAnyo() {
		anyosTrabajados++;
	}

	/**
	 * Este metodo saca por pantalla la información del empleado
	 */
	public void Imprime() {
		System.out.println("El nombre de la persona es: " + nombre + ", y su DNI es: " + dni + ", el sexo es: " + sexo
				+ ", La categoria es: " + categoria + ", y los anyos trabajados son: " + anyosTrabajados);
	}

}// fin clase Empleado
