/*
 * Created on Apr 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package charlie.feng.util.sql.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author jf25468
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SQLUtil {
	public static String getMetaInfo(Connection con) throws SQLException{
		StringBuffer sb = new StringBuffer();
		DatabaseMetaData meta = con.getMetaData();
		sb.append("\nmeta.getDatabaseProductName():" + meta.getDatabaseProductName());
		sb.append("\nmeta.getDatabaseProductVersion():" + meta.getDatabaseProductVersion());
		sb.append("\nmeta.getDefaultTransactionIsolation():" + meta.getDefaultTransactionIsolation());
		sb.append("\nmeta.getDriverMajorVersion():" + meta.getDriverMajorVersion());
		sb.append("\nmeta.getDriverMinorVersion():" + meta.getDriverMinorVersion());
		sb.append("\nmeta.getDriverName():" + meta.getDriverName());
		sb.append("\nmeta.getDriverVersion():" + meta.getDriverVersion());
		sb.append("\nmeta.getDriverMajorVersion():" + meta.getDriverMajorVersion());
		sb.append("\nmeta.getDriverMinorVersion():" + meta.getDriverMinorVersion());
		sb.append("\nmeta.getMaxRowSize():" + meta.getMaxRowSize());
		sb.append("\nmeta.getMaxStatementLength():" + meta.getMaxStatementLength());
		return sb.toString();
	}
}
