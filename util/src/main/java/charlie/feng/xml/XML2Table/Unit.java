/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
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
		proc.compositTagMap.put("UnitAIs", new Integer(6));
		proc.compositTagMap.put("TerrainNatives", new Integer(4));
		proc.compositTagMap.put("ReligionSpreads", new Integer(1));
		proc.compositTagMap.put("GreatPeoples", new Integer(1));
		proc.compositTagMap.put("NotUnitAIs", new Integer(2));
		proc.compositTagMap.put("UnitCombatMods", new Integer(4));
		proc.compositTagMap.put("PrereqBonuses", new Integer(4));
		proc.compositTagMap.put("FreePromotions", new Integer(2));
		proc.compositTagMap.put("TechTypes", new Integer(3));
		proc.compositTagMap.put("Flavors", new Integer(1));
		proc.compositTagMap.put("TerrainImpassables", new Integer(1));
		proc.compositTagMap.put("FeatureDefenses", new Integer(1));
		proc.compositTagMap.put("UnitClassAttackMods", new Integer(2));
		proc.compositTagMap.put("UnitCombatMods", new Integer(2));
		proc.compositTagMap.put("DomainMods", new Integer(1));
		proc.compositTagMap.put("UnitClassUpgrades", new Integer(3));
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
