/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table.sax;

import charlie.feng.xml.XML2Table.Column;
import charlie.feng.xml.XML2Table.ElementLevel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Stack;

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
@SuppressWarnings("unchecked")

public class ConfigTableHandler extends XML2TableSaxHandler {

    PlainXML2TableBySax main = null;
    int level = ElementLevel.OUT;
    LevelInfo currentLevelInfo = null;
    @SuppressWarnings("rawtypes")
    Stack elementStack = new Stack();

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes)
            throws SAXException {
        switch (level) {
            case ElementLevel.OUT:
                if (!(qName.equals(main.recordTag))) {
                } else {
                    level = ElementLevel.RECORD;
                }
                return;
            case ElementLevel.RECORD:
                level = ElementLevel.COLUMN;
                if (currentLevelInfo == null) {
                    currentLevelInfo = new LevelInfo();
                    currentLevelInfo.setLevel(level);
                    currentLevelInfo.setSibling(0);
                } else {
                    currentLevelInfo.setSibling(currentLevelInfo.getSibling() + 1);
                }
                elementStack.push(currentLevelInfo);
                return;
            case ElementLevel.COLUMN:
                level = ElementLevel.SUB_COLUMN;
                if (currentLevelInfo == null) {
                    currentLevelInfo = new LevelInfo();
                    currentLevelInfo.setLevel(level);
                    currentLevelInfo.setSibling(0);
                } else {
                    currentLevelInfo.setSibling(currentLevelInfo.getSibling() + 1);
                }
                elementStack.push(currentLevelInfo);
                return;
            case ElementLevel.SUB_COLUMN:
                level = ElementLevel.COLUMN_PART;
                if (currentLevelInfo == null) {
                    currentLevelInfo = new LevelInfo();
                    currentLevelInfo.setLevel(level);
                    currentLevelInfo.setSibling(0);
                } else {
                    currentLevelInfo.setSibling(currentLevelInfo.getSibling() + 1);
                }
                elementStack.push(currentLevelInfo);
                return;
            case ElementLevel.COLUMN_PART:
                return;
            default:
                break;

        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        switch (level) {
            case ElementLevel.OUT:
                return;
            case ElementLevel.RECORD:
                level = ElementLevel.OUT;
                currentLevelInfo = (LevelInfo) elementStack.pop();
                return;
            case ElementLevel.COLUMN:
                level = ElementLevel.SUB_COLUMN;
                currentLevelInfo = (LevelInfo) elementStack.pop();
                int count = currentLevelInfo.sibling;
                Column column = new Column(main.geneColSN(count), qName, count);
                main.columnMap.put(qName, column);
                return;
            case ElementLevel.SUB_COLUMN:
                level = ElementLevel.COLUMN_PART;
                currentLevelInfo = (LevelInfo) elementStack.pop();
//					int count = currentLevelInfo.sibling;


                if (currentLevelInfo == null) {
                    currentLevelInfo = new LevelInfo();
                    currentLevelInfo.setLevel(level);
                    currentLevelInfo.setSibling(0);
                } else {
                    currentLevelInfo.setSibling(currentLevelInfo.getSibling() + 1);
                }
                elementStack.push(qName);
                return;
            case ElementLevel.COLUMN_PART:
                return;
            default:
                break;

        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String s = new String(ch, start, length);
        System.out.println(s);
        System.out.println();
    }


    /**
     * @return
     */
    public PlainXML2TableBySax getMain() {
        return main;
    }

    /**
     * @param sax
     */
    public void setMain(PlainXML2TableBySax sax) {
        main = sax;
    }

}
