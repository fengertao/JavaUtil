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
 */
public class DB2ConnectionFactory extends AbstractConnectionFactory {

    protected DB2ConnectionFactory() throws ClassNotFoundException {
        super();
        Class.forName(getDriverName());
    }

    public static void main(String[] args) {
        try {
            Connection con =
                    getConnection("jdbc:db2:grdat123", "s1xo3q1", "godsave1");
            con.close();
            System.err.println("finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getDriverName() {
        return "COM.ibm.db2.jdbc.app.DB2Driver";
    }

    protected String getDefaultUrl() {
        return "jdbc:db2:grdat123";
    }

    protected String getDefaultUser() {
        return "";
    }

    protected String getDefaultPassword() {
        return "";
    }

}
