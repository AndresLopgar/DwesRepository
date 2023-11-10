package conexion;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Código para gestionar la BBDD
 */
public class DBUtils {
	 private static BasicDataSource dataSource = null;
	
	 private static DataSource getDataSource() {
	   if (dataSource == null) {
	    dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUsername("root");
	    dataSource.setPassword("usuario");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/company");
	   }
	   return dataSource;
	  }
	
	/**
	 * este metodo es para conexion con la BBDD
	 * @return retorna la conexion con la BBDD
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();

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
