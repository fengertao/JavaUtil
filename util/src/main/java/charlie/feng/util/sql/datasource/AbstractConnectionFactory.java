/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.util.sql.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;

public abstract class AbstractConnectionFactory {

	protected abstract String getDriverName();
	protected abstract String getDefaultUrl();
	protected abstract String getDefaultUser();
	protected abstract String getDefaultPassword();

	private void initDriver() throws ClassNotFoundException{
		Class.forName(getDriverName());
	}

	public static Connection getConnection(String url, String user, String password) throws SQLException{
		return getConnection(url, user, password, false);
	}

	public static Connection getConnection(String url, String user, String password, boolean autoCommit) throws SQLException {

		try {
			Connection con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(autoCommit);
			return con;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private static void initAll() {
		System.out.println("Initialize JDBC Driver Starting ... ");
		ResourceBundle rb = null;
		Enumeration enum1 = null;
		rb = ResourceBundle.getBundle("charlie.feng.sql.datasource.SupportedDB");
		enum1 = rb.getKeys();

		for (; enum1.hasMoreElements();) {
			String dbname = (String)enum1.nextElement();
			String classname = (String)rb.getString(dbname);
			try {
				Class cls = Class.forName(classname);
				((AbstractConnectionFactory)cls.newInstance()).initDriver();
				System.out.println("JDBC Driver for " + dbname + " initialized.");

			} catch (Exception e) {
				System.err.println("Warning:JDBC Driver for " + dbname + " NOT initialized.");
			}
		}
		System.out.println("Initialize JDBC Driver Ended. ");
	}

	static {
		initAll();
	}


}
