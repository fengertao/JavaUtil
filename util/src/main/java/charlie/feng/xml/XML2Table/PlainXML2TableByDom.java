/**
 * This source code is a component of Charlie Feng's source library,
 * All copyright reverved.
 * @author Charlie Feng (Fengertao@hotmail.com)
 */
package charlie.feng.xml.XML2Table;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import charlie.feng.util.sql.datasource.AbstractConnectionFactory;



/**
 * Some Concept Define by me
 * Simple Column: like below
 * 			<Type>LEADER_BARBARIAN</Type>
 * In DB the column "Type" will have value "LEADER_BARBARIAN"
 * 
 * 
 * Composit Column type 1 like blow
 * 			<Traits>
 *				<Trait>
 *					<TraitType>TRAIT_PHILOSOPHICAL</TraitType>
 *					<bTrait>1</bTrait>
 *				</Trait>
 *				<Trait>
 *					<TraitType>TRAIT_AGGRESSIVE</TraitType>
 *					<bTrait>1</bTrait>
 *				</Trait>
 *			</Traits>
 *
 * 
 * Composit Column type 2 like below
 * 
 * 			<Goodies>
 *				<GoodyType>GOODY_HIGH_GOLD</GoodyType>
 * 			</Goodies>
 * 
 * In DB there will have 2 "Trait" column, first column have value "TRAIT_PHILOSOPHICAL:1"
 * second column have value "TRAIT_AGGRESSIVE:1"
 * 
 * @author Charlie Feng
 */
@SuppressWarnings("rawtypes")
public abstract class PlainXML2TableByDom extends PlainXML2Table {

	public void run() throws Exception{
		try{
			con =AbstractConnectionFactory.getConnection(dbUrl, id, pwd, true);
			
			DocumentBuilderFactory dbf =  DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			dbf.setValidating(true);
			Document document = builder.parse(xmlPath);
			Element element = document.getDocumentElement();
			buildColumnMap(element);
			createTable();
			insertRecords(element);
			System.out.println("Ended.");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
	}
	
	@SuppressWarnings("static-access")
	public void buildColumnMap(Node node) {
		if (node == null) {
			return;
		}
		
		if (node.getNodeType() != node.ELEMENT_NODE) {
			return;
		}
		 
		if (!(node.getNodeName().equals(recordTag))) {
			NodeList children = node.getChildNodes();
			if (null == children) {
				return;
			}
			for ( int i = 0; i< children.getLength(); i++) {
				buildColumnMap(children.item(i));
			}
			return;
		} else {
			renewColumnMap(node);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void renewColumnMap(Node node) {
		NodeList children = node.getChildNodes();
		if (null == children) {
			return;
		}
columnConfigLoop:
		for ( int i = 0; i< children.getLength(); i++) {
			Node columnNode = children.item(i);
			
			if (columnNode.getNodeType() == Node.ELEMENT_NODE) {
				String columnDesc = columnNode.getNodeName();
				int columnLength = 0;
				if (escapeTagSet.contains(columnDesc)) {
					continue columnConfigLoop;
				}
				if (compositTagMap.get(columnDesc) != null) {
					configCompositColumn(columnNode);
					continue columnConfigLoop;
				}
				
				NodeList columnChildren = columnNode.getChildNodes();
				if (columnChildren.getLength() == 0) {
					columnLength = 0;
				} else if (columnChildren.getLength() == 1){
					String value = columnChildren.item(0).getNodeValue();
					if (value == null) {
						columnLength = NULL_COLUMN_LENGTH;
					} else {
						columnLength = value.length();
					}
				} else {
					columnLength = COMPOSIT_COLUMN_LENGTH;
				}
				Column column = (Column)columnMap.get(columnDesc); 
				if (column == null) {
					column = new Column(geneColSN(1), columnDesc, Math.max(columnDesc.length(), columnLength));
				} else {
					column.setLength(Math.max(column.getLength(), columnLength));
					
				}
				columnMap.put(columnDesc,column);
			}
		}
		return;
	}
	
	
	@SuppressWarnings("unchecked")
	public void configCompositColumn(Node node) {
		String columnsDesc = node.getNodeName();
		if (compositTagMap.get(columnsDesc) != null) {
			int columnCount = ((Integer)compositTagMap.get(columnsDesc)).intValue();
			Column column = (Column)columnMap.get(columnsDesc); 
			if (column == null) {
				column = new Column(geneColSN(columnCount), columnsDesc, Math.max(columnsDesc.length()+2, COLUMN_LENGTH), columnCount);
			} else {
				column.setLength(Math.max(column.getLength(), COLUMN_LENGTH));
			}
			columnMap.put(columnsDesc,column);
		}
	}
	
	public void insertCompositColumn(Node node, PreparedStatement ps) throws SQLException{
		String columnsDesc = node.getNodeName();
		if (compositTagMap.get(columnsDesc) != null) {
			Column column = (Column)columnMap.get(columnsDesc);
			int maxCount = column.getCount();
			int leadSn = column.getSn();
			NodeList nodelist = node.getChildNodes();
			int innerSn = 0;
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node singleColumn = nodelist.item(i);
				String singleColumnValue = EMPTY_VALUE;
				if (singleColumn.getNodeType() == Node.ELEMENT_NODE) {
					NodeList columnPartNodeList = singleColumn.getChildNodes();
					
					
					if (columnPartNodeList.getLength() ==1)  //Composit Type 2
					{
						singleColumnValue = columnPartNodeList.item(0).getNodeValue();
					} else {		//Composit Type 2
						for (int j = 0; j < columnPartNodeList.getLength(); j++) {
							Node columnPartNode = columnPartNodeList.item(j);
							if (columnPartNode.getNodeType() == Node.ELEMENT_NODE) {
								NodeList columnPartTextNodeList = columnPartNode.getChildNodes();
								for (int k = 0; k < columnPartTextNodeList.getLength(); k++){
									Node columnPartTextNode = columnPartTextNodeList.item(k);
									if (columnPartTextNode.getNodeType() == Node.TEXT_NODE) {
										singleColumnValue += columnPartTextNode.getNodeValue() + DILIMITER;
									}
								}
							}
						}
						if (singleColumnValue.length() > 0) {
							singleColumnValue = singleColumnValue.substring(0, singleColumnValue.length() - 1);
						}
					}
					
					markColumnFilled(leadSn + innerSn);
					ps.setString(leadSn + innerSn, singleColumnValue);
					innerSn ++;
				}
			}
			
			for (int j = innerSn; j < maxCount; j++) {
				markColumnFilled(leadSn + j);
				ps.setString(leadSn + j, EMPTY_VALUE);
			}
			 
		}
	}
	
	
	@SuppressWarnings("unused")
	public void createTable() throws SQLException{
		String createTableSql = "create table " + tableName + "( ";
		for (int i = 1; i <= colSn; i++) {
			Integer columnSn = new Integer(i);
//			String columnDesc = (String)columnSnMap.get(columnSn);
//			Column column = (Column)columnMap.get(columnDesc);
			createTableSql += "COL" + i;
			createTableSql += " varchar(";
//			createTableSql += column.getLength();
			createTableSql += COLUMN_LENGTH;
			createTableSql += ")";
			if (i < colSn) {
				createTableSql += ",";
			}
		}
		createTableSql += ")";
		System.out.println("createTableSql:" + createTableSql);
		Statement stat = con.createStatement();
		stat.executeUpdate(createTableSql);
		stat.close();
		
		String insertHeaderSql1 = "insert into " + tableName + "( ";
		String insertHeaderSql2 = ") values (";
		String insertHeaderSql3 = ")";
		
		for (int i = 1; i<=colSn; i++) {
			insertHeaderSql1 += "COL" + i;
			insertHeaderSql2 += "?";
			
			if (i < colSn) {
				insertHeaderSql1 += ",";
				insertHeaderSql2 += ",";
			}
		}
		insertHeaderSql = insertHeaderSql1 + insertHeaderSql2 + insertHeaderSql3;
		System.out.println("insertHeaderSql:" + insertHeaderSql);
		
		PreparedStatement ps = con.prepareStatement(insertHeaderSql) ;
		for (Iterator iter = columnMap.entrySet().iterator(); iter.hasNext(); ) {
			Map.Entry entry = (Map.Entry)iter.next();
			String columnDesc = (String)entry.getKey();
			Column column = (Column)entry.getValue();
			int leadSn = column.getSn();
			int count = column.getCount();
			String desc = column.getDesc();
			for (int i = 0; i < count; i++) {
				ps.setString(leadSn + i, desc);
							
			}
			
		}
		ps.executeUpdate();
		ps.close();
	}
	
	public void insertRecords(Node node) throws SQLException{
		if (node == null) {
			return;
		}
		
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			return;
		}
		
		if (!(node.getNodeName().equals(recordTag))) {
			NodeList children = node.getChildNodes();
			if (null == children) {
				return;
			}
			for ( int i = 0; i< children.getLength(); i++) {
				insertRecords(children.item(i));
			}
			return;
		} else {
			insertRecord(node);
		}
	}
	
	public void insertRecord(Node node) throws SQLException{
		for (int i = 0; i < 1000; i++) {
			columnFilled[i] = false;
			
		}
		
		PreparedStatement ps = con.prepareStatement(insertHeaderSql) ;
		NodeList children = node.getChildNodes();
		if (null == children) {
			return;
		}
columnInsertLoop:
			for ( int i = 0; i< children.getLength(); i++) {
			Node columnNode = children.item(i);
			if (columnNode.getNodeType() == Node.ELEMENT_NODE) {
				String columnDesc = columnNode.getNodeName();
				String columnValue = EMPTY_VALUE;
				if (escapeTagSet.contains(columnDesc)) {
					continue columnInsertLoop;
				}
				if (compositTagMap.get(columnDesc) != null) {
					insertCompositColumn(columnNode, ps);
					continue columnInsertLoop;
				}
				
				NodeList columnChildren = columnNode.getChildNodes();
				if (columnChildren.getLength() == 0) {
					columnValue = EMPTY_VALUE;
				} else if (columnChildren.getLength() == 1){
					columnValue = columnChildren.item(0).getNodeValue();
				} else {
					columnValue = COMPOSIT_VALUE;
				}
				int columnSn = ((Column)columnMap.get(columnDesc)).getSn();
				markColumnFilled(columnSn);
				ps.setString(columnSn, columnValue);
			}
			
		}
		fillOptionalColumn(ps);
		ps.executeUpdate();
		ps.close();
		return;
	}
	
}
