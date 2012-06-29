/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
@SuppressWarnings("unused")
public class CountDom {

	public static void main(String[] args) throws Exception{
		String key = "javax.xml.parsers.DocumentBuilderFactory";
		String value = "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl";
		System.setProperty(key, value);
//		System.out.println("element Count : " + getElementCount("bin\\charlie\\feng\\xml\\DTDTest.xml"));
		System.out.println("element Count : " + getElementCount("C:\\FJ\\ent\\civiv\\Assets\\XML\\Terrain\\CIV4BonusClassInfos.xml"));
	}
	
	public static int getElementCount(String fileName) throws Exception {
		Node node = readFile(new File(fileName));
		return getElementCount(node);
	}
	
	public static int getElementCount(Node node) throws Exception {
		if (null == node) {
			return 0; 
		}
		
		int sum = 0;
		boolean isElement = (node.getNodeType() == Node.ELEMENT_NODE);
		if (isElement) {
			sum = 1;
		}
		
		NodeList children = node.getChildNodes();
		if (null == children) {
			return sum;
		}
		for ( int i = 0; i< children.getLength(); i++) {
			sum +=getElementCount(children.item(i));
		}
		return sum;
	}
	
	public static Document readFile(File file) throws Exception {
		Document doc;
		try{
			DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
			DocumentBuilder builder = dbf.newDocumentBuilder();
//			builder.setErrorHandler(null);
			Document document = builder.parse(file);
			return document;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
}
