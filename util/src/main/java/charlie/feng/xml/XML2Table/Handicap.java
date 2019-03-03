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
public class Handicap extends PlainXML2TableByDom {


    public static void main(String[] args) throws Exception {
        Handicap proc = new Handicap();
        proc.compositTagMap.put("Goodies", 20);
        proc.compositTagMap.put("FreeTechs", 4);
        proc.compositTagMap.put("AIFreeTechs", 4);
//		proc.escapeTagSet.add("DiplomacyIntroMusicPeace");
        proc.recordTag = "HandicapInfo";
        proc.tableName = "Handicap";
        proc.xmlPath = "C:\\FJ\\ent\\civiv\\Assets\\XML\\GameInfo\\CIV4HandicapInfo.xml";
        proc.dbUrl = "jdbc:odbc:Handicap";
        proc.id = "";
        proc.pwd = "";


        proc.run();
    }

}
