package unl.cse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <b>DBFactory</b> is a wrapper object for JDBC connectivity.
 * This will provide one connection that can be reused.
 * 
 * @author Jacob Charles
 * @author Alexis Kennedy
 * @version 0.5.0
 */
public class DBFactory {
	private Connection conn;
	private PreparedStatement ps;
	private String query;
	
	public DBFactory() { }
	
	public void createConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		try {
			this.conn = DriverManager.getConnection(DBConnection.DB_URL, DBConnection.DB_USERNAME, DBConnection.DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <i>runUpdate</i> utilizes the JDBC <i>executeUpdate()</i>
	 * @return - boolean returns true if successful
	 */
	public boolean runUpdate() {
		boolean result = false;
		try { 
			this.ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			clearParams();
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		closeConn();
		return result;
	}
	
	/**
	 * <i>runQuery</i> utilizes the JDBC <i>executeQuery()</i>
	 * @param index - (String) must be the Column of selection
	 * @return - string of result
	 */
	public String runQuery(String index) {
		String result = null;
		try {
			ResultSet rs = this.ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(index);
			}
		} catch (SQLException e) {
			clearParams();
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		closeConn();
		return result;
	}
	
	/**
	 * <i>clearParams</i> utilizes the JDBC <i>clearParameters()</i>
	 */
	public void clearParams() {
		try {
			this.ps.clearParameters();
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <i>closeConn</i> closes up the JDBC connection, thus rendering
	 * this object useless.
	 * @return - boolean returns true if successful
	 */
	public boolean closeConn() {
		boolean result = false;
		try {
			if ((this.ps != null) || (!this.ps.isClosed())) {
				this.ps.close();
			} if ((this.conn != null) || (!this.conn.isClosed())) {
				this.conn.close();
			}
			result = true;
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} return result;
	}
	
	/**
	 * 
	 * @param index - prepared statement index
	 * @param param - string parameter of statement
	 */
	public void setStringParam(int index, String param) {
		try {
			this.ps.setString(index, param);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param index - prepared statement index for null
	 */
	public void setNullParam(int index) {
		try {
			this.ps.setNull(index, java.sql.Types.NULL);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @param index - prepared statement index
	 * @param param - int parameter of statement
	 */
	public void setIntParam(int index, int param) {
		try {
			this.ps.setInt(index, param);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <i>setDoubleParam</i> sets a double as SQL DECIMAL
	 * @param index - prepared statement index
	 * @param param - double parameter of statement
	 */
	public void setDoubleParam(int index, double param) {
		try {
			this.ps.setDouble(index, param);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <i>setQuery</i> utilizes the JDBC <i>prepareStatement()</i>
	 * @param query - is a String containing the sql to prepare
	 */
	public void setQuery(String query) {
		createConn();
		this.query = query;
		try {
			this.ps = this.conn.prepareStatement(getQuery());
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * @return - SQL prepared statement
	 */
	public String getQuery() {
		return query;
	}
}