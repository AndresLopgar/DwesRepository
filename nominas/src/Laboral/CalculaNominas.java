package Laboral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
		 * Código que usamos para la práctica "Nomina_parte1". try { 
		 * Empleado empleado1= new Empleado("James Cosling", "32000032G", 'M', 4, 7); 
		 * Empleado empleado2 = new Empleado("Ada Lovalace", "32000031R", 'F');
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

			//creo el BufferedReader para recorrer el archivo empleados.txt
			File archivo = new File("C:/Users/ajlg/Desktop/DWES/nominas/src/empleados.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			 
			//creo un array para incrementar anyos en un empleado y cambiar categoria en otro
			ArrayList<Empleado>empleadosArray = new ArrayList<>();
			Empleado empleado;
			Nomina nomina = new Nomina();
			
			//saco el dni y el sueldo del empleado mediante un separador (,)
			salida = new PrintWriter("C:/Users/ajlg/Desktop/DWES/nominas/src/sueldos.dat");
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] seccion = linea.split(",");
				
				//parseo las variables para crear un empleado y conseguir su sueldo
				char sexo = seccion[2].charAt(0);
				int categoria = Integer.parseInt(seccion[3]);
				double anyos = Double.parseDouble(seccion[4]);
				
				empleado = new Empleado(seccion[0],seccion[1],sexo,categoria,anyos);
				empleadosArray.add(empleado);
				double sueldo = nomina.nomina(empleado);
				 
				 //saco el dni y sueldo de cada empleado por pantalla
				salida.println("El dni es: " + seccion[1] + ", El sueldo es: " + sueldo);
			} // fin del while
			
			
			//hago un contador para saber cual es el primer y segundo empleado
			int contador = 0;
			//hago un foreach para recorrer el array
			for (Empleado empleadoFore : empleadosArray) {
				if(contador == 0) {
					empleadoFore.setCategoria(9);
					contador++;
				}
				if(contador == 1) {
					empleadoFore.IncrAnyo();
				}
			}//fin del foreach
			
			
			//inserto la informacion actualizada del arrayList al fichero empleados.txt
			
			
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
