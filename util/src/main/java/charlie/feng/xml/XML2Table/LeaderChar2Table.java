/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 *
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table;

import charlie.feng.xml.XML2Table.sax.PlainXML2TableBySax;

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
public class LeaderChar2Table extends PlainXML2TableBySax {


    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        LeaderChar2Table lct = new LeaderChar2Table();
        lct.compositTagMap.put("Traits", 2);
        lct.compositTagMap.put("Flavors", 2);
        lct.compositTagMap.put("ContactRands", 14);
        lct.compositTagMap.put("ContactDelays", 14);
        lct.compositTagMap.put("MemoryDecays", 17);
        lct.compositTagMap.put("MemoryAttitudePercents", 23);
        lct.compositTagMap.put("NoWarAttitudeProbs", 4);
        lct.compositTagMap.put("UnitAIWeightModifiers", 1);
        lct.compositTagMap.put("ImprovementWeightModifiers", 2);
        lct.escapeTagSet.add("DiplomacyIntroMusicPeace");
        lct.escapeTagSet.add("DiplomacyMusicPeace");
        lct.escapeTagSet.add("DiplomacyIntroMusicWar");
        lct.escapeTagSet.add("DiplomacyMusicWar");
        lct.recordTag = "LeaderHeadInfo";
        lct.tableName = "LeaderChar";
        lct.xmlPath = "C:\\FJ\\ent\\civiv\\Assets\\XML\\Civilizations\\CIV4LeaderHeadInfos.xml";
        lct.dbUrl = "jdbc:odbc:LeaderChar";
        lct.id = "";
        lct.pwd = "";


        lct.run();
    }

}
