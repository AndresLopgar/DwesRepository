package Laboral;

import java.sql.*;

/**
 * Código para gestionar la BBDD
 */
public class DBUtils {
	
	/**
	 * este metodo es para conexion con la BBDD
	 * @return retorna la conexion con la BBDD
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		final String USER = "root";
		final String PASS = "usuario";
		final String CONN_URL = "jdbc:mariadb://localhost:3306/company";
		Connection conn = null;

		conn = DriverManager.getConnection(CONN_URL, USER, PASS);
		return conn;

	}

	/**
	 * este metodo es para cerrar la conexion con la BBDD
	 * @param conn es el conjunto de URL,USER,PASSWORD
	 * @throws SQLException
	 */
	public void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}//fin del metodo close para la conexion

	/**
	 * este metodo cierra el statement
	 * @param st es una sentencia SQL
	 * @throws SQLException
	 */
	public void close(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}//fin del metodo close para los Statement

	/**
	 * este metodo cierra el resulset
	 * @param rs es el conjunto de resultados de una consulta
	 * @throws SQLException
	 */
	public void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}//fin del metodo close para los ResulSet
}//fin de la clase DBUtils.
