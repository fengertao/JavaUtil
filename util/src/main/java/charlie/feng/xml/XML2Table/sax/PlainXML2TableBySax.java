/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table.sax;

import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import charlie.feng.util.sql.datasource.AbstractConnectionFactory;
import charlie.feng.xml.XML2Table.PlainXML2Table;

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
public abstract class PlainXML2TableBySax extends PlainXML2Table {

	SAXParserFactory factory = null;
	SAXParser parser = null;
	
	public void run() throws Exception{
		try{
			con =AbstractConnectionFactory.getConnection(dbUrl, id, pwd, true);
			
			System.setProperty("javax.xml.parsers.SAXParserFactory", "org.apache.xerces.jaxp.SAXParserFactoryImpl");
			factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			parser = factory.newSAXParser();
			buildColumnMap();
//			createTable();
//			insertRecords();
			System.out.println("Ended.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
		
	public void buildColumnMap() throws SAXException, IOException{
		parser.parse(xmlPath, new ConfigTableHandler());
	}
		
}
