package Laboral;

public class Empleado extends Persona {

	private int categoria;
	double anyosTrabajados;

	public Empleado(String nombre, String dni, char sexo, int categoria, double anyosTrabajados) throws DatosNoCorrectosException {
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

	}//fin del constructor de empleado

	public Empleado(String nombre, String dni, char sexo) throws DatosNoCorrectosException {
		super(nombre, dni, sexo);
		categoria = 1;
		anyosTrabajados=0;
		// TODO Auto-generated constructor stub
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public void IncrAnyo() {
		anyosTrabajados++;
	}

	public void Imprime() {
		System.out.println("El nombre de la persona es: " + nombre + ", y su DNI es: " + dni + 
				", el sexo es: " + sexo + ", La categoria es: " + categoria + 
				", y los anyos trabajados son: " + anyosTrabajados);
	}


}// fin clase Empleado
