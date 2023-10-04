package Laboral;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;

/**
 * Es la clase nominas
 */
public class CalculaNominas {

	/**
	 * @param args son args
	 */
	public static void main(String[] args) {

		/*
		 * Código que usamos para la práctica "Nomina_parte1". try { Empleado empleado1=
		 * new Empleado("James Cosling", "32000032G", 'M', 4, 7); Empleado empleado2 =
		 * new Empleado("Ada Lovalace", "32000031R", 'F');
		 * 
		 * escribe(empleado1); escribe(empleado2);
		 * 
		 * // incremento los años trabajados del segundo empleado empleado2.IncrAnyo();
		 * 
		 * // cambio la categoria del empleado1 por la categoria 9
		 * empleado1.setCategoria(9);
		 * 
		 * escribe(empleado1); escribe(empleado2); } catch (DatosNoCorrectosException e)
		 * { // TODO Auto-generated catch block System.out.println(e.getMessage()); } //
		 * fin del try-catch
		 * 
		 */

		// Creo la variable para añadir las lineas de texto al archivo final
		PrintWriter salida = null;

		// creo el separador y arraylist donde guardar los empleados en empleados.txt
		try {
			// creo el BufferedReader para recorrer el archivo empleados.txt
			File archivo = new File("C:/Users/ajlg/Desktop/DWES/nominas/src/empleados.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

			// creo un array para incrementar anyos en un empleado y cambiar categoria en
			// otro
			ArrayList<Empleado> empleadosArray = new ArrayList<>();
			Empleado empleado;
			Nomina nomina = new Nomina();

			// saco el dni y el sueldo del empleado mediante un separador (,)
			salida = new PrintWriter("C:/Users/ajlg/Desktop/DWES/nominas/src/sueldos.dat");
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] seccion = linea.split(",");

				// parseo las variables para crear un empleado y conseguir su sueldo
				char sexo = seccion[2].charAt(0);
				int categoria = Integer.parseInt(seccion[3]);
				double anyos = Double.parseDouble(seccion[4]);

				empleado = new Empleado(seccion[0], seccion[1], sexo, categoria, anyos);
				empleadosArray.add(empleado);
				double sueldo = nomina.nomina(empleado);

				// saco el dni y sueldo de cada empleado por pantalla
				salida.println("El dni es: " + seccion[1] + ", El sueldo es: " + sueldo);
			} // fin del while

			// hago un contador para saber cual es el primer y segundo empleado
			int contador = 0;
			// hago un foreach para recorrer el array
			for (Empleado empleadoFore : empleadosArray) {
				if (contador == 0) {
					empleadoFore.setCategoria(9);
					contador++;
				}
				if (contador == 1) {
					empleadoFore.IncrAnyo();
				}
			} // fin del foreach

			// inserto la informacion actualizada del arrayList al fichero empleados.txt
			FileWriter fw = new FileWriter("C:/Users/ajlg/Desktop/DWES/nominas/src/sueldos.dat");

			for (Empleado empleado2 : empleadosArray) {
				String nombre = empleado2.nombre;
				String dni = empleado2.dni;
				char sexo = empleado2.sexo;
				int categoria = empleado2.getCategoria();
				double anyos = empleado2.anyosTrabajados;

				String resultado = (nombre + "," + dni + "," + sexo + "," + categoria + "," + anyos);
				fw.write(resultado);
				fw.flush();
			} // fin del bucle for

			/*
			 * Cierro el apartado 2, de la segunda parte de nominas
			 * 
			 * //Hago la conexión a la BBDD try { DBUtils.getConnection();
			 * System.out.println("Conexión exitosa."); } catch (SQLException e) {
			 * System.out.println("No se ha logrado conectar con la BBDD"); }
			 * 
			 * 
			 * 
			 * //Registro a un usuario a través de código Connection con = null; Statement
			 * st = null;
			 * 
			 * try { con = DBUtils.getConnection(); st = con.createStatement(); st.
			 * executeUpdate("INSERT INTO EMPLEADO VALUES('Manuel ortiz','87643826B','M',2,4)"
			 * ); System.out.println("Usuario creado correctamente"); st.close();
			 * }catch(Exception e) { System.out.
			 * println("El usuario no pudo ser registrado o ya lo está en la BBDD");
			 * System.out.println(e.getMessage()); }
			 * 
			 * 
			 * //Voy a actualizar la categoria del empleado1, e incrementar los años
			 * trabajados del segundo try { con = DBUtils.getConnection(); st =
			 * con.createStatement();
			 * st.executeUpdate("UPDATE EMPLEADO SET categoria=9 WHERE DNI LIKE '32000032G'"
			 * ); System.out.println("Empleado 1 actualizado correctamente correctamente");
			 * st.
			 * executeUpdate("UPDATE EMPLEADO SET anyos_trabajados=1 WHERE DNI LIKE '32000031R'"
			 * ); System.out.println("Empleado 2 actualizado correctamente correctamente");
			 * st.close(); }catch(Exception e) { System.out.
			 * println("El usuario no pudo ser registrado o ya lo está en la BBDD");
			 * System.out.println(e.getMessage()); }
			 * 
			 * // Actualizar la BBDD ingresando el sueldo de cada empleado try { con =
			 * DBUtils.getConnection(); st = con.createStatement();
			 * 
			 * st.executeUpdate("ALTER TABLE EMPLEADO ADD sueldo decimal");
			 * System.out.println("La columna sueldo ha sido añadida correctamente");
			 * 
			 * st.
			 * executeUpdate("UPDATE EMPLEADO SET sueldo=145000.0 WHERE DNI LIKE '32000032G'"
			 * ); System.out.println("Empleado 1 actualizado correctamente correctamente");
			 * st.
			 * executeUpdate("UPDATE EMPLEADO SET sueldo=50000.0 WHERE DNI LIKE '32000031R'"
			 * ); System.out.println("Empleado 2 actualizado correctamente correctamente");
			 * st.close(); }catch(Exception e) {
			 * System.out.println("La columna sueldo NO ha sido añadida");
			 * System.out.println(e.getMessage()); }
			 */

			fw.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (DatosNoCorrectosException e) {
			System.out.println(e.getMessage());
		} finally {
			salida.close();
		}

		// descomentar para dar de alta
		// altaEmpleado();

		boolean repetir = true;

		do {
			switch (menu()) {
			case 1:
				ArrayList<Empleado> empleadoBackup = new ArrayList();
				Empleado empleado = null;
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;

				try {
					con = DBUtils.getConnection();
					st = con.createStatement();
					rs = st.executeQuery("Select * from empleado");
					while (rs.next()) {
						String nombre = rs.getString(1);
						String dni = rs.getString(2);
						char sexo = rs.getString(3).charAt(0);
						int categoria = rs.getInt(4);
						double anyo_trabajado = rs.getDouble(5);

						empleado = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
						empleadoBackup.add(empleado);

					} // fin del while
					for (Empleado empleado2 : empleadoBackup) {
						System.out.println(empleado2.nombre + ", " + empleado2.dni + ", " + empleado2.sexo + ", "
								+ empleado2.getCategoria() + ", " + empleado2.anyosTrabajados);
					} // fin del foreach
				} catch (SQLException | DatosNoCorrectosException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				ArrayList<Empleado> empleadoSueldo = new ArrayList();

				try {
					con = DBUtils.getConnection();
					st = con.createStatement();
					rs = st.executeQuery("Select * from empleado");
					while (rs.next()) {
						String nombre = rs.getString(1);
						String dni = rs.getString(2);
						char sexo = rs.getString(3).charAt(0);
						int categoria = rs.getInt(4);
						double anyo_trabajado = rs.getDouble(5);

						empleado = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
						empleadoSueldo.add(empleado);

					} // fin del while
					for (Empleado empleado2 : empleadoSueldo) {
						Nomina n = new Nomina();
						double sueldo = n.nomina(empleado2);

						System.out.println(sueldo);
					} // fin del foreach
				} catch (SQLException | DatosNoCorrectosException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Dame un dni para modificar el empleado");
				Scanner teclado = new Scanner(System.in);
				String dni_a_cambiar = teclado.nextLine();
				
				System.out.println("Dame un nuevo nombre:");
				String nuevoNombre = teclado.nextLine();
				
				System.out.println("Dame un nuevo sexo:");
				String sexoS = teclado.nextLine();
				char nuevoSexo = sexoS.charAt(0);
				
				System.out.println("Dame un nuevo categoria:");
				int nuevoCategoria = teclado.nextInt();
				
				System.out.println("Dame un nuevo anyo_trabajado:");
				double nuevoAnyo_trabajado = teclado.nextDouble();
				break;
			case 4:
				
				System.out.println("Dame un dni para recalcular el sueldo del empleado");
				teclado = new Scanner(System.in);
				String dniIntroducido = teclado.nextLine();
				Empleado empleadoRecalcula = null;
				Nomina nominaRecalcula = new Nomina();
				double sueldoNuevo = 0;
				try {
					con = DBUtils.getConnection();
					st = con.createStatement();
					rs = st.executeQuery("Select sueldo from empleado where dni like " + dniIntroducido);

					while (rs.next()) {
						String nombre = rs.getString(1);
						String dni = rs.getString(2);
						char sexo = rs.getString(3).charAt(0);
						int categoria = rs.getInt(4);
						double anyo_trabajado = rs.getDouble(5);

						empleadoRecalcula = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);

						sueldoNuevo = nominaRecalcula.nomina(empleadoRecalcula);
					} // fin del while

					rs = st.executeQuery(
							"update nomina set sueldo=" + sueldoNuevo + " where dni like " + dniIntroducido);
				} catch (SQLException | DatosNoCorrectosException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				Empleado empleadosRecalcula = null;
				Nomina nominasRecalcula = new Nomina();
				double sueldoNuevo2 = 0;
				try {
					con = DBUtils.getConnection();
					st = con.createStatement();
					rs = st.executeQuery("Select sueldo from empleado");

					while (rs.next()) {
						String nombre = rs.getString(1);
						String dni = rs.getString(2);
						char sexo = rs.getString(3).charAt(0);
						int categoria = rs.getInt(4);
						double anyo_trabajado = rs.getDouble(5);

						empleadosRecalcula = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);

						sueldoNuevo = nominasRecalcula.nomina(empleadosRecalcula);
					} // fin del while

					rs = st.executeQuery("update nomina set sueldo=" + sueldoNuevo2);
				} catch (SQLException | DatosNoCorrectosException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				File empleadosBackup = new File("C:/Users/ajlg/Desktop/DWES/nominas2/src/empleadosBackup.dat");
				PrintWriter pw = null, pw2 = null;
				ArrayList<Empleado> empleadoBackup2 = new ArrayList();

				try {

					pw = new PrintWriter("C:/Users/ajlg/Desktop/DWES/nominas2/src/empleadosBackup.dat");
					pw2 = new PrintWriter("C:/Users/ajlg/Desktop/DWES/nominas2/src/nominasBackup.dat");
					con = DBUtils.getConnection();
					st = con.createStatement();
					rs = st.executeQuery("Select * from empleado");
					while (rs.next()) {
						String nombre = rs.getString(1);
						String dni = rs.getString(2);
						char sexo = rs.getString(3).charAt(0);
						int categoria = rs.getInt(4);
						double anyo_trabajado = rs.getDouble(5);

						empleado = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
						empleadoBackup2.add(empleado);

					} // fin del while
					for (Empleado empleado2 : empleadoBackup2) {
						Nomina n = new Nomina();
						double sueldo = n.nomina(empleado2);
						pw.println(empleado2.nombre + ", " + empleado2.dni + ", " + empleado2.sexo + ", "
								+ empleado2.getCategoria() + ", " + empleado2.anyosTrabajados);
						pw2.println(empleado2.dni + ", " + sueldo);
					} // fin del foreach
					pw.close();
					pw2.close();
				} catch (SQLException | IOException | DatosNoCorrectosException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				System.out.println("Vamos a salir de la app. Adiós");
				repetir = false;
				break;

			default:
				System.out.println("La opción elegida no es posible");
				break;
			}
		} while (repetir);

	}// fin de la clase main

	/**
	 * Menu para gestionar la aplicación
	 * 
	 * @return retorna un valor que elegira que hacer en un switch en el main
	 */
	public static int menu() {
		int devuelve = 0;

		System.out.println("MENÚ");
		System.out.println("1.- Muestra info empleados BBDD");
		System.out.println("2.- Salario por dni específico");
		System.out.println("3.- Modificar datos empleado");
		System.out.println("4.- Recalcula y actualiza sueldo de un empleado");
		System.out.println("5.- Recalcula y actualiza sueldo de todos empleado");
		System.out.println("6.- Realizar backup en ficheros");
		System.out.println("7.- Salir");

		return devuelve;
	}

	/**
	 * metodo para dar de alta a un empleado
	 */
	public static void altaEmpleado() {
		Connection con = null;
		Statement st = null;

		String nombre = null, dni = null;
		char sexo = 0;
		int categoria = 0;
		double anyosTrabajados = 0, sueldo = 0;

		try {
			// creo el BufferedReader para recorrer el archivo empleados.txt
			File archivo = new File("C:/Users/ajlg/Desktop/DWES/nominas2/src/main/java/Laboral/empleadosNuevos.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] seccion = linea.split(",");
				nombre = seccion[0];
				dni = seccion[1];
				// parseo las variables para crear un empleado y conseguir su sueldo
				sexo = seccion[2].charAt(0);
				categoria = Integer.parseInt(seccion[3]);
				anyosTrabajados = Double.parseDouble(seccion[4]);
				Empleado empleado = new Empleado(seccion[0], seccion[1], sexo, categoria, anyosTrabajados);

				con = DBUtils.getConnection();
				st = con.createStatement();
				st.executeUpdate("INSERT INTO EMPLEADO VALUES('" + nombre + "','" + dni + "','" + sexo + "',"
						+ categoria + "," + anyosTrabajados + ")");
				System.out.println("Usuario creado correctamente");
				st.close();
			} // fin del while
		} catch (Exception e) {
			System.out.println("El usuario no pudo ser registrado o ya lo está en la BBDD");
			System.out.println(e.getMessage());
		} // fin del try-catch
	}// fin del metodo altaEmpleado

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
