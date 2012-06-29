/*
 * Created on Jul 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package charlie.feng.util.sql.datasource;

import java.sql.Connection;

/**
 * @author jf25468
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DataBase {
	protected String url;
	protected String id;
	protected String pwd;
	protected String schema;
	protected Connection con;
	
	/**
	 * @return
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @return
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param connection
	 */
	public void setCon(Connection connection) {
		con = connection;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

	/**
	 * @param string
	 */
	public void setPwd(String string) {
		pwd = string;
	}

	/**
	 * @param string
	 */
	public void setSchema(String string) {
		schema = string;
	}

	/**
	 * @param string
	 */
	public void setUrl(String string) {
		url = string;
	}

}
