package Laboral;

public class CalculaNominas {

	/**
	 * @param args
	 * @param empleado1 es un objeto de la clase empleado
	 * @param empleado2 es un objeto de la clase empleado
	 */
	public static void main(String[] args) {
		try {
			Empleado empleado1 = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
			Empleado empleado2 = new Empleado("Ada Lovalace", "32000031R", 'F');

			escribe(empleado1);
			escribe(empleado2);

			// incremento los años trabajados del segundo empleado
			empleado2.IncrAnyo();

			// cambio la categoria del empleado1 por la categoria 9
			empleado1.setCategoria(9);

			escribe(empleado1);
			escribe(empleado2);
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} // fin del try-catch
	}// fin de la clase main

	/**
	 * Este es un método que nos imprime por pantalla los datos del empleado que le
	 * pasamos por parámetro.
	 * 
	 * @param empleado objeto de la clase Empleado, del cual conseguimos su sueldo
	 *                 através de el método "nomina".
	 */
	private static void escribe(Empleado empleado) {
		Nomina n1 = new Nomina();

		empleado.Imprime();
		System.out.println("Su sueldo es: " + n1.nomina(empleado));
	}// fin del metodo escribe

}
