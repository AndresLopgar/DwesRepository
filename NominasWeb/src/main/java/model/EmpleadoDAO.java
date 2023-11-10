package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.DBUtils;
import exceptions.DatosNoCorrectosException;

public class EmpleadoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// guardar producto
	public boolean guardar(Empleado empleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO empleado (nombre, dni, sexo, categoria, anyos_trabajados) VALUES(?,?,?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getDni());
			statement.setString(3, empleado.getSexo());
			statement.setInt(4, empleado.getCategoria());
			statement.setDouble(5, empleado.getAnyosTrabajados());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// editar producto
	public boolean editar(Empleado empleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "UPDATE empleado SET nombre=?, sexo=?, categoria=?, anyos_trabajados=? WHERE dni=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, empleado.getNombre());
			statement.setString(2, empleado.getSexo());
			statement.setInt(3, empleado.getCategoria());
			statement.setDouble(4, empleado.getAnyosTrabajados());
			statement.setString(5, empleado.getDni());

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// eliminar producto
	public boolean eliminar(String dniEmpleado) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			connection.setAutoCommit(false);
			sql = "update empleado set eliminado=1 WHERE dni=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dniEmpleado);

			estadoOperacion = statement.executeUpdate() > 0;
			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	// obtener lista de productos
	public List<Empleado> obtenerEmpleados() throws SQLException, DatosNoCorrectosException {
		ResultSet resultSet = null;
		List<Empleado> listaProductos = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleado where eliminado = 0";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Empleado p = new Empleado();

				p.setNombre(resultSet.getString(1));
				p.setDni(resultSet.getString(2));
				p.setSexo(resultSet.getString(3));
				p.setCategoria(resultSet.getInt(4));
				p.setAnyosTrabajados(resultSet.getDouble(5));
				listaProductos.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProductos;
	}

	// obtener producto
	public Empleado obtenerEmpleado(String dniEmpleado) throws SQLException, DatosNoCorrectosException {
		ResultSet resultSet = null;
		Empleado p = new Empleado();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM empleado WHERE dni =?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, dniEmpleado);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				p.setNombre(resultSet.getString(1));
				p.setDni(resultSet.getString(2));
				p.setSexo(resultSet.getString(3));
				p.setCategoria(resultSet.getInt(4));
				p.setAnyosTrabajados(resultSet.getDouble(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	// obtener conexion pool
	private Connection obtenerConexion() throws SQLException {
		return DBUtils.getConnection();
	}
}
