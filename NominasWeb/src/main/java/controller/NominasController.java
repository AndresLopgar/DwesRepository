package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.model.Producto;

import model.Nomina;
import conexion.DBUtils;
import exceptions.DatosNoCorrectosException;
import model.Empleado;

/**
 * Servlet implementation class NominasController
 */
@WebServlet("/NominasController")
public class NominasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Empleado> empleados;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NominasController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion.equals("muestraEmpleados")) {
			empleados = new ArrayList<>();
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
					String sexo = rs.getString(3);
					int categoria = rs.getInt(4);
					double anyo_trabajado = rs.getDouble(5);

					empleado = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
					empleados.add(empleado);

				} // fin del while
				request.setAttribute("empleados", empleados);
				request.setAttribute("mensaje", "");
				request.setAttribute("content", "views/muestraEmpleados.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
				request.setAttribute("mensaje", "Empleados recuperados correctamente");
				rs.close();
				st.close();
				con.close();
			} catch (SQLException | DatosNoCorrectosException e) {
				System.out.println(e.getMessage());
			}
		} else if (opcion.equals("muestraSalario")) {
			request.setAttribute("mensaje", "");
			request.setAttribute("content", "views/formulario.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("modificaEmpleado")) {
			request.setAttribute("mensaje", "");
			request.setAttribute("content", "views/formularioModifica.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("creaEmpleado")) {
			request.setAttribute("mensaje", "");
			request.setAttribute("content", "views/creaEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("menu")) {
			request.setAttribute("content", "views/menu.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}
	}// fin del metodo doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("buscar")) {
			buscar(request, response);
		} else if (opcion.equals("crear")) {
			crear(request, response);
		} else if (opcion.equals("modificar")) {
			modificar(request, response);
		} else if (opcion.equals("mostrar")) {
			mostrarEmpleados(request, response);
		} else if (opcion.equals("iniciar")) {
			Empleado empleado = new Empleado();
			empleado.setDni(request.getParameter("dni"));
			empleado.setNombre(request.getParameter("nombre"));
			if (empleado.getDni().isEmpty()) {
				request.setAttribute("mensaje", "El empleado con ese DNI no se encuentra en la base de datos");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);
			} else {
				Connection con = null;
				Statement st = null;
				ResultSet rs = null;
				int registrado = 0;
				try {
					String nombreEmpleado = request.getParameter("nombre");
					String dniEmpleado = request.getParameter("dni");
					con = DBUtils.getConnection();
					st = con.createStatement();
					rs = st.executeQuery("Select registrado from empleado where dni = '" + dniEmpleado
							+ "' and nombre= '" + nombreEmpleado + "'");
					while (rs.next()) {

						registrado = rs.getInt(1);

					}
					if (registrado == 0) {
						request.setAttribute("mensaje",
								"Los datos introducidos no corresponden con un empleado registrado");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
						requestDispatcher.forward(request, response);
					} else {
						request.setAttribute("content", "views/menu.jsp");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
						requestDispatcher.forward(request, response);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} // fin del catch
			} // fin del if
		}
	}// fin del metodo doPost

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Empleado empleado = new Empleado();
		empleado.setDni(request.getParameter("dni"));
		if (empleado.getDni().isEmpty() || devuelveDni(empleado.getDni(), empleados) == false) {
			request.setAttribute("mensaje", "El empleado con ese DNI no se encuentra en la base de datos");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/formulario.jsp");
			requestDispatcher.forward(request, response);
		} else {
			Empleado empleado2 = null;
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;

			try {
				String dniEmpleado = request.getParameter("dni");
				con = DBUtils.getConnection();
				st = con.createStatement();
				rs = st.executeQuery("Select * from empleado where dni = '" + dniEmpleado + "'");
				while (rs.next()) {
					String nombre = rs.getString(1);
					String dni = rs.getString(2);
					String sexo = rs.getString(3);
					int categoria = rs.getInt(4);
					double anyo_trabajado = rs.getDouble(5);

					empleado2 = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
				} // fin del while
				Nomina nomina = new Nomina();
				double sueldo = nomina.nomina(empleado2);

				request.setAttribute("empleadoNombre", empleado2.nombre);
				request.setAttribute("empleadoDni", empleado2.dni);
				request.setAttribute("sueldo", sueldo);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} // fin del catch
			request.setAttribute("mensaje", "Empleado recuperado correctamente");
			request.setAttribute("content", "views/muestraSalario.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		} // fin del if

	}

	public boolean devuelveDni(String dni, ArrayList<Empleado> empleados) {
		for (Empleado empleado : empleados) {
			if (empleado.getDni().equals(dni)) {
				return true;
			}
			break;
		} // fin del for
		return false; // Si no se encuentra el DNI en ningún empleado.
	}

	public void mostrarEmpleados(HttpServletRequest request, HttpServletResponse response) {
		empleados = new ArrayList<>();
		Empleado empleado = new Empleado();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			String nombreEmpleado = request.getParameter("nombre");
			String dniEmpleado = request.getParameter("dni");
			String sexoEmpleado = request.getParameter("sexo");
			int categoriaEmpleado = 0;
			if (request.getParameter("categoria").isEmpty()) {
				categoriaEmpleado = 0;
			}

			else if (request.getParameter("categoria") != "") {
				categoriaEmpleado = Integer.parseInt(request.getParameter("categoria"));
			}
			Double anyoTrabajadoEmpleado = 0d;
			if (request.getParameter("anyos_trabajados").isEmpty()) {
				anyoTrabajadoEmpleado = 0d;
			}

			else if (request.getParameter("anyos_trabajados") != "") {
				anyoTrabajadoEmpleado = Double.parseDouble(request.getParameter("anyos_trabajados"));
			}
			String select = "";

			if (nombreEmpleado.equals("") && dniEmpleado.equals("") && sexoEmpleado.equals("") && categoriaEmpleado == 0
					&& anyoTrabajadoEmpleado == 0d) {
				request.setAttribute("mensaje", "Debe de introducir algún valor en algún campo");
				request.setAttribute("content", "views/formularioModifica.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			} // fin del if

			if (nombreEmpleado != "") {
				select = "select * from empleado where nombre like '" + nombreEmpleado + "'";
			}

			else if (dniEmpleado != "") {
				select = "Select * from empleado where dni like '" + dniEmpleado + "'";
			}

			else if (sexoEmpleado != "") {
				select = "select * from empleado where sexo like '" + sexoEmpleado + "'";
			}

			else if (categoriaEmpleado != 0) {
				select = "select * from empleado where categoria like '" + categoriaEmpleado + "'";
			}

			else if (anyoTrabajadoEmpleado != 0d) {
				select = "select * from empleado where anyos_trabajados like '" + anyoTrabajadoEmpleado + "'";
			} // fin del if

			con = DBUtils.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				String nombre = rs.getString(1);
				String dni = rs.getString(2);
				String sexo = rs.getString(3);
				int categoria = rs.getInt(4);
				double anyo_trabajado = rs.getDouble(5);

				empleado = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
				empleados.add(empleado);
			} // fin del while

			request.setAttribute("empleados", empleados);
			request.setAttribute("content", "views/muestraEmpleados.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			request.setAttribute("mensaje", "Empleados recuperados correctamente");
			requestDispatcher.forward(request, response);

			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} // fin del try-catch
	}

	public void modificar(HttpServletRequest request, HttpServletResponse response) {
		empleados = new ArrayList<>();
		Empleado empleado = new Empleado();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			String nombreEmpleado = request.getParameter("nombre");
			String dniEmpleado = request.getParameter("dni");
			String sexoEmpleado = request.getParameter("sexo");
			int categoriaEmpleado = 0;
			if (request.getParameter("categoria").isEmpty()) {
				categoriaEmpleado = 0;
			}

			else if (request.getParameter("categoria") != "") {
				categoriaEmpleado = Integer.parseInt(request.getParameter("categoria"));
			}
			Double anyoTrabajadoEmpleado = 0d;
			if (request.getParameter("anyos_trabajados").isEmpty()) {
				anyoTrabajadoEmpleado = 0d;
			}

			else if (request.getParameter("anyos_trabajados") != "") {
				anyoTrabajadoEmpleado = Double.parseDouble(request.getParameter("anyos_trabajados"));
			}
			String select = "";

			if (nombreEmpleado.equals("") && dniEmpleado.equals("") && sexoEmpleado.equals("") && categoriaEmpleado == 0
					&& anyoTrabajadoEmpleado == 0d) {
				request.setAttribute("mensaje", "Debe de introducir algún valor en algún campo");
				request.setAttribute("content", "views/formularioModifica.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			} // fin del if

			if (devuelveDni(dniEmpleado, empleados)) {
				select = "UPDATE empleados SET nombre = '" + nombreEmpleado + "', dni = '" + dniEmpleado + "', sexo = '"
						+ sexoEmpleado + "'," + " categoria = '" + categoriaEmpleado + "', anyos_trabajados = '"
						+ anyoTrabajadoEmpleado + "' WHERE dni = '" + dniEmpleado + "'";
			}

			con = DBUtils.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(select);
			while (rs.next()) {
				String nombre = rs.getString(1);
				String dni = rs.getString(2);
				String sexo = rs.getString(3);
				int categoria = rs.getInt(4);
				double anyo_trabajado = rs.getDouble(5);

				empleado = new Empleado(nombre, dni, sexo, categoria, anyo_trabajado);
				empleados.add(empleado);
			} // fin del while

			request.setAttribute("empleados", empleados);
			request.setAttribute("mensaje", "");
			request.setAttribute("content", "views/muestraEmpleados.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} // fin del try-catch

	}

	public void crear(HttpServletRequest request, HttpServletResponse response){
		Empleado empleado = new Empleado();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		

		try {
			String nombreEmpleado = request.getParameter("nombre");
			String dniEmpleado = request.getParameter("dni");
			String sexoEmpleado = request.getParameter("sexo");
			int categoriaEmpleado = 0;
			if (request.getParameter("categoria").isEmpty()) {
				categoriaEmpleado = 0;
			}

			else if (request.getParameter("categoria") != "") {
				categoriaEmpleado = Integer.parseInt(request.getParameter("categoria"));
			}
			Double anyoTrabajadoEmpleado = 0d;
			if (request.getParameter("anyos_trabajados").isEmpty()) {
				anyoTrabajadoEmpleado = 0d;
			}

			else if (request.getParameter("anyos_trabajados") != "") {
				anyoTrabajadoEmpleado = Double.parseDouble(request.getParameter("anyos_trabajados"));
			}
			
			String select= "";
			

			if (nombreEmpleado.equals("") && dniEmpleado.equals("") && sexoEmpleado.equals("") && categoriaEmpleado == 0
					&& anyoTrabajadoEmpleado == 0d) {
				request.setAttribute("mensaje", "Debe de introducir algún valor en algún campo");
				request.setAttribute("content", "views/formularioModifica.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			} // fin del if

			if (devuelveDni(dniEmpleado, empleados)) {
				select = "UPDATE empleados SET nombre = '" + nombreEmpleado + "', dni = '" + dniEmpleado + "', sexo = '"
						+ sexoEmpleado + "'," + " categoria = '" + categoriaEmpleado + "', anyos_trabajados = '"
						+ anyoTrabajadoEmpleado + "' WHERE dni = '" + dniEmpleado + "'";
			}
			
			con = DBUtils.getConnection();
			String query = "INSERT INTO empleado (nombre, dni, sexo, categoria, anyos_trabajados)" +
			" VALUES ('" + nombreEmpleado + "', '" + dniEmpleado + "', '" + sexoEmpleado + "', '" + categoriaEmpleado + "', " + anyoTrabajadoEmpleado + ")";
			Statement statement = con.createStatement();

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate(query);
            
            empleado = new Empleado(nombreEmpleado, dniEmpleado, sexoEmpleado, categoriaEmpleado, anyoTrabajadoEmpleado);
    		empleados.add(empleado);
    		request.setAttribute("empleados", empleados);
    		request.setAttribute("content", "views/muestraEmpleados.jsp");
    		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
    		requestDispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
		
			

}// fin de la clase
