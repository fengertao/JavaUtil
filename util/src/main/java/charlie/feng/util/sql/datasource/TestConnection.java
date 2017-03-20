/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.util.sql.datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * <P>
 * <P>
 * <PRE>
 * AUTHOR    DATE     VERSION NO. REVISION HISTORY  
 * --------- -------- ----------- ------------------
 * Charlie.F          1.0
 * (limit to 5 latest entries) 
 * </PRE>
 *
 * @author Charlie Feng
 */
public class TestConnection {

    public static void main(String[] args) throws SQLException {
        Connection con = AbstractConnectionFactory.getConnection("jdbc:odbc:Unit", "", "", true);
        System.out.println("Connection got.");
        con.close();
        System.out.println("Connection closed.");

    }
}
