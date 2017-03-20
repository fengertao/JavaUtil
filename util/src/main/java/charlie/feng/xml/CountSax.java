/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

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
public class CountSax extends DefaultHandler {
    static private int elementCount = 0;

    public static void main(String[] args) throws Exception {
        String key = "javax.xml.parsers.SAXParserFactory";
        String value = "org.apache.xerces.jaxp.SAXParserFactoryImpl";
        System.setProperty(key, value);
        SAXParserFactory fact = SAXParserFactory.newInstance();
        fact.setValidating(true);
        SAXParser parser = fact.newSAXParser();
        parser.parse("file:" + new File("bin\\charlie\\feng\\xml\\DTDTest.xml").getAbsolutePath(), new CountSax());
    }

    public void startDocument() throws SAXException {
        elementCount = 0;
    }

    public void endDocument() throws SAXException {
        System.out.println("element count:" + elementCount);
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        elementCount++;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
     */
    public void error(SAXParseException e) throws SAXException {
        // TODO Auto-generated method stub
        System.out.println("Error!");
        e.printStackTrace();
        super.error(e);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
     */
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Warning!");
        e.printStackTrace();
        super.warning(e);
    }


}
