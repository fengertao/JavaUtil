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
 *         <p>
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
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
     * @param connection
     */
    public void setCon(Connection connection) {
        con = connection;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param string
     */
    public void setId(String string) {
        id = string;
    }

    /**
     * @return
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param string
     */
    public void setPwd(String string) {
        pwd = string;
    }

    /**
     * @return
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param string
     */
    public void setSchema(String string) {
        schema = string;
    }

    /**
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param string
     */
    public void setUrl(String string) {
        url = string;
    }

}
