/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXParseException;

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
public class XMLUtil {

    public static void main(String[] args) {
    }

    @SuppressWarnings("unused")
    public static void printDocument(Document document) throws SAXParseException {

        DocumentType documentType = document.getDoctype();
        Element element = document.getDocumentElement();
        printNodeNameAndValue(element);
        NamedNodeMap attributes = element.getAttributes();
//		printNameNodeMap(attributes);
    }

    public static void printNodeNameAndValue(Node node) throws DOMException {
        System.out.println("Name : " + node.getNodeName());
        System.out.println("Value : " + node.getNodeValue());
        if (node.hasChildNodes()) {

            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
//				System.out.println("Name : " + item.getNodeName());
//				System.out.println("Value : " + item.getNodeValue());
                printNodeNameAndValue(item);
            }

        }
    }

}
