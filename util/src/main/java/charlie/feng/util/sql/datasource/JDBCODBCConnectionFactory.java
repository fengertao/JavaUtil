/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.util.sql.datasource;

import java.sql.Connection;

/**
 * @author Charlie Feng 
 * Created on Nov 24, 2004
 */
public class JDBCODBCConnectionFactory extends AbstractConnectionFactory {

    public JDBCODBCConnectionFactory() throws ClassNotFoundException {
        super();
        Class.forName(getDriverName());
    }

    public static void main(String[] args) {
        Connection con = null;
        try {
            con = getConnection("jdbc:odbc:Unit", "", "", true);
            System.out.println("Connection getted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected String getDriverName() {
        return "sun.jdbc.odbc.JdbcOdbcDriver";
    }

    protected String getDefaultUrl() {
        return "jdbc:odbc:Unit";
    }

    protected String getDefaultUser() {
        return "";
    }

    protected String getDefaultPassword() {
        return "";
    }
}
