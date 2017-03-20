/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table;


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
public class Technology extends PlainXML2TableByDom {


    public static void main(String[] args) throws Exception {
        Technology proc = new Technology();
        proc.compositTagMap.put("Flavors", new Integer(7));
        proc.compositTagMap.put("OrPreReqs", new Integer(3));
        proc.compositTagMap.put("AndPreReqs", new Integer(2));
        proc.escapeTagSet.add("Help");
        proc.escapeTagSet.add("DomainExtraMoves");
        proc.escapeTagSet.add("CommerceFlexible");
        proc.escapeTagSet.add("TerrainTrades");
        proc.escapeTagSet.add("Button");
        proc.recordTag = "TechInfo";
        proc.tableName = "TechTree";
        proc.xmlPath = "C:\\FJ\\ent\\civiv\\Assets\\XML\\Technologies\\CIV4TechInfos.xml";
        proc.dbUrl = "jdbc:odbc:Technology";
        proc.id = "";
        proc.pwd = "";


        proc.run();
    }

}
