package Laboral;

public class CalculaNominas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Empleado empleado1;
		Empleado empleado2;

		try {
			empleado1 = new Empleado("James Cosling", "32000032G", 'M', -1, 7);
			empleado2 = new Empleado("Ada Lovalace", "32000031R", 'F');

			escribe(empleado1);
			escribe(empleado2);

			// implemento los años trabajados del segundo empleado
			empleado2.IncrAnyo();

			// cambio la categoria del empleado1 por la categoria 9
			empleado1.setCategoria(9);

			escribe(empleado1);
			escribe(empleado2);
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} // fin del try-catch
	}//fin de la clase main

	private static void escribe(Empleado empleado) {
		Nomina n1 = new Nomina();

		empleado.Imprime();
		System.out.println("Su sueldo es: " + n1.nomina(empleado));
	}// fin del metodo escribe

}
