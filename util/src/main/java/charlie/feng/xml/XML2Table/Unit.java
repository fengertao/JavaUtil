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
public class Unit extends PlainXML2TableByDom {


    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Unit proc = new Unit();
        proc.compositTagMap.put("UnitAIs", 6);
        proc.compositTagMap.put("TerrainNatives", 4);
        proc.compositTagMap.put("ReligionSpreads", 1);
        proc.compositTagMap.put("GreatPeoples", 1);
        proc.compositTagMap.put("NotUnitAIs", 2);
        proc.compositTagMap.put("UnitCombatMods", 4);
        proc.compositTagMap.put("PrereqBonuses", 4);
        proc.compositTagMap.put("FreePromotions", 2);
        proc.compositTagMap.put("TechTypes", 3);
        proc.compositTagMap.put("Flavors", 1);
        proc.compositTagMap.put("TerrainImpassables", 1);
        proc.compositTagMap.put("FeatureDefenses", 1);
        proc.compositTagMap.put("UnitClassAttackMods", 2);
        proc.compositTagMap.put("UnitCombatMods", 2);
        proc.compositTagMap.put("DomainMods", 1);
        proc.compositTagMap.put("UnitClassUpgrades", 3);
        proc.escapeTagSet.add("UnitMeshGroups");
        proc.escapeTagSet.add("TerrainNatives");
        proc.escapeTagSet.add("FeatureNatives");
        proc.escapeTagSet.add("Button");
        proc.escapeTagSet.add("Builds");
        proc.escapeTagSet.add("Buildings");
        proc.escapeTagSet.add("UnitMeshGroups");
        proc.escapeTagSet.add("UniqueNames");
        proc.escapeTagSet.add("ForceBuildings");
        proc.recordTag = "UnitInfo";
        proc.tableName = "Unit";
        proc.xmlPath = "C:\\FJ\\ent\\civiv\\Assets\\XML\\Units\\CIV4UnitInfos.xml";
        proc.dbUrl = "jdbc:odbc:Unit";
        proc.id = "";
        proc.pwd = "";


        proc.run();
    }

}
