package controller;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.ProductoDAO;
import com.aprendec.model.Producto;

import model.Nomina;
import conexion.DBUtils;
import exceptions.DatosNoCorrectosException;
import model.Empleado;
import model.EmpleadoDAO;

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
			listar(request,response);
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
		}else if(opcion.equals("meditar")) {
			String dni = (request.getParameter("dni"));
			System.out.println("Editar dni: " + dni);
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			Empleado empleado = new Empleado();
			try {
				empleado = empleadoDAO.obtenerEmpleado(dni);
				request.setAttribute("empleado", empleado);
				request.setAttribute("content", "views/modificaEmpleado.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException | DatosNoCorrectosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// fin del metodo doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		RequestDispatcher requestDispatcher;

		if (opcion.equals("buscar")) {
			buscar(request, response);
		} else if (opcion.equals("crear")) {
			crear(request, response);
		} else if(opcion.equals("editar")) {
			modificar(request, response);
		} else if(opcion.equals("eliminar")) {
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			String dni = (request.getParameter("dni"));
			try {
				request.setAttribute("mensaje", "empleado eliminado correctamente");
				empleadoDAO.eliminar(dni);
				listar(request,response);
				System.out.println("Registro eliminado satisfactoriamente...");
				request.setAttribute("content", "views/muestraEmpleado.jsp");
				 requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("mensaje", "El empleado no ha podido ser creado");
				System.out.println("EL Registro no ha sido eliminado satisfactoriamente...");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("mostrar")) {
			mostrarEmpleados(request, response);
		} else if (opcion.equals("iniciar")) {
			Empleado empleado = new Empleado();
			empleado.setDni(request.getParameter("dni"));
			empleado.setNombre(request.getParameter("nombre"));
			if (empleado.getDni().isEmpty()) {
				request.setAttribute("mensaje", "El empleado con ese DNI no se encuentra en la base de datos");
				request.setAttribute("content", "views/login.jsp");
				 requestDispatcher = request.getRequestDispatcher("index.jsp");
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
						request.setAttribute("content", "views/login.jsp");
						 requestDispatcher = request.getRequestDispatcher("index.jsp");
						requestDispatcher.forward(request, response);
					} else {
						request.setAttribute("content", "views/menu.jsp");
						 requestDispatcher = request.getRequestDispatcher("index.jsp");
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
			request.setAttribute("content", "views/formulario.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
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
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		Empleado empleado = new Empleado();
		empleado.setNombre(request.getParameter("nombre"));
		empleado.setDni(request.getParameter("dni"));
		empleado.setSexo(request.getParameter("sexo"));
		try {
			empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		empleado.setAnyosTrabajados(Double.parseDouble(request.getParameter("anyos_trabajados")));
		
		try {
			empleadoDAO.editar(empleado);
			request.setAttribute("mensaje", "empleado editado correctamente");
			System.out.println("Registro editado satisfactoriamente...");
			request.setAttribute("content", "views/modificaEmpleado.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			request.setAttribute("mensaje", "El empleado no ha podido ser creado");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crear(HttpServletRequest request, HttpServletResponse response){
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		Empleado empleado = new Empleado();
		empleado.setNombre(request.getParameter("nombre"));
		empleado.setDni(request.getParameter("dni"));
		empleado.setSexo(request.getParameter("sexo"));
		try {
			empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		empleado.setAnyosTrabajados(Double.parseDouble(request.getParameter("anyos_trabajados")));
		
		try {
			if(empleado.getNombre().isEmpty() || empleado.getCategoria() == 0 || empleado.getAnyosTrabajados() == 0 ||
					empleado.getDni().isEmpty() || empleado.getSexo().isEmpty()) {
				request.setAttribute("mensaje", "El empleado no ha podido ser creado");
				request.setAttribute("content", "views/creaEmpleado.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			}else {
				request.setAttribute("mensaje", "empleado creado correctamente");
				empleadoDAO.guardar(empleado);
				System.out.println("Registro guardado satisfactoriamente...");
				request.setAttribute("content", "views/creaEmpleado.jsp");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
			}//fin del if
		} catch (SQLException e) {
			
			e.getMessage();
			// TODO Auto-generated catch block
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//fin del metodo crear
	
	public List<Empleado> listar(HttpServletRequest request, HttpServletResponse response) {
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		List<Empleado> lista = new ArrayList<>();
		try {
			lista = empleadoDAO.obtenerEmpleados();
			for (Empleado producto : lista) {
				System.out.println(producto);
			}

			request.setAttribute("empleados", lista);
			request.setAttribute("mensaje", "");
			request.setAttribute("content", "views/muestraEmpleados.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
			request.setAttribute("mensaje", "Empleados recuperados correctamente");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatosNoCorrectosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
		
	}
		
			

}// fin de la clase
