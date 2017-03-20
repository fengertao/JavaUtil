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
public class HSQLDBConnectionFactory extends AbstractConnectionFactory {

    public HSQLDBConnectionFactory() throws ClassNotFoundException {
        super();
        Class.forName(getDriverName());
    }

    public static void main(String[] args) {
        Connection con = null;
        try {
            con = getConnection("jdbc:hsqldb:hsql://localhost", "sa", "");
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
        return "org.hsqldb.jdbcDriver";
    }

    protected String getDefaultUrl() {
        return "jdbc:hsqldb:hsql://localhost";
    }

    protected String getDefaultUser() {
        return "sa";
    }

    protected String getDefaultPassword() {
        return "";
    }
}
