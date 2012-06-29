/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
@SuppressWarnings("rawtypes")
public abstract class PlainXML2Table {
	//blow are hard coded constant
	public static int COLUMN_LENGTH = 50;				//hard code column length to 50
	public static int COMPOSIT_COLUMN_LENGTH = 100;	//acturely useless because I hard to all to 50
	public static int NULL_COLUMN_LENGTH = 5;			//acturely useless because I hard to all to 50
	public static String COMPOSIT_VALUE = "**";		//if I don't want display some composit column, display as "**" 
	public static String EMPTY_VALUE = "";				//value for EMPTY column
	public static String DILIMITER = ":";				//dilimiter for composit column.

	//below are config info, define in child class.	
	public String xmlPath;
	public String dbUrl;
	public String id;
	public String pwd;
	public String recordTag;						//the Eye-catcher of record start.
	public String tableName;						//the table name of excel (i.e. the sheet name)
	public Map compositTagMap = new HashMap();		//Key:tags contains composit tag which need display.
											//Value: maximam number. 
	public Set escapeTagSet = new HashSet();		//tags needn't display
	
	public int colSn = 0;
	public Map columnMap = new HashMap();
	public String insertHeaderSql = "";
	public Connection con;
	
	//debug only variable
	protected boolean[] columnFilled = new boolean[1000]; // no table more than 1000 column 


	public int geneColSN( int snUsed) {
		int firstSn = colSn + 1;
		colSn += snUsed;
		return firstSn;
	}
	
	public void markColumnFilled(int columnSn) {
		boolean filled = columnFilled[columnSn];
		if (filled == true) {
			System.err.println("column " + (columnSn) + " already filled" );
		} else {
			columnFilled[columnSn] = true;
		}
	}
	
	public void fillOptionalColumn(PreparedStatement ps) throws SQLException{
		for (int i = 1; i <= colSn; i++) {
			if (!columnFilled[i]) {
				ps.setString(i, EMPTY_VALUE);
			}
			
		}
	}


}
