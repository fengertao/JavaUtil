package charlie.feng.countrydatanormalizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import charlie.feng.countrydatanormalizer.dom.Country;
import charlie.feng.countrydatanormalizer.dom.CountryYearData;
import charlie.feng.countrydatanormalizer.meta.Attribute;
import charlie.feng.countrydatanormalizer.meta.CountryMeta;
import charlie.feng.countrydatanormalizer.util.PoiUtil;

public class App {
	private static final String CONFIG_COUNTRY_ATTRIBUTE_NAME_PREFIX = "ATTR_";
	private static final String CONFIG_COUNTRY_YEAR_ATTRIBUTE_NAME_PREFIX = "YEAR_ATTR_";
	public static String SRC_PATH = null;
	public static String TGT_PATH = null;
	public static String SRC_SHEET = null;
	public static int START_YEAR;
	public static int END_YEAR;
	public static int START_ROW;
	public static int TARGET_FREEZE_COLUMNID;
	
	public static List<Country> countryList ;

	public static void main(String[] args) throws Exception {
		readConfigFile();
		readSourceExcel();
		writeTargetExcel();
		
		// new App().yearToAttr();
	}

	private static void readConfigFile() throws FileNotFoundException, IOException {
//		System.out.println(App.class.getClassLoader().getResource("."));
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		prop.load(fis);
		fis.close();
		SRC_PATH = prop.getProperty("SRC_PATH");
		TGT_PATH = prop.getProperty("TGT_PATH");
		SRC_SHEET = prop.getProperty("SRC_SHEET");
		START_YEAR = Integer.parseInt(prop.getProperty("START_YEAR"));
		END_YEAR = Integer.parseInt(prop.getProperty("END_YEAR"));
		START_ROW = Integer.parseInt(prop.getProperty("START_ROW"));
		TARGET_FREEZE_COLUMNID = Integer.parseInt(prop.getProperty("TARGET_FREEZE_COLUMNID", "0"));
		
		Iterator<Entry<Object, Object>> iterator = prop.entrySet().iterator();
		System.out.println("SRC_PATH:" + SRC_PATH);
		// initial Country Meta
		while (iterator.hasNext()) {
			Entry<Object, Object> entry = iterator.next();
			String configKey = entry.getKey().toString();
			if (configKey.startsWith(CONFIG_COUNTRY_ATTRIBUTE_NAME_PREFIX)) {
				String attrName = configKey.substring(CONFIG_COUNTRY_ATTRIBUTE_NAME_PREFIX.length());
				int position = Integer.parseInt(entry.getValue().toString());
				Attribute attribute = new Attribute(attrName, position, false);
				CountryMeta.AttributeNameMap.put(attrName, attribute);
				CountryMeta.positionMap.put(position, attribute);
			} else if (configKey.startsWith(CONFIG_COUNTRY_YEAR_ATTRIBUTE_NAME_PREFIX)) {
				String attrName = configKey.substring(CONFIG_COUNTRY_YEAR_ATTRIBUTE_NAME_PREFIX.length());
				int position = Integer.parseInt(entry.getValue().toString());
				Attribute attribute = new Attribute(attrName, position, true);
				CountryMeta.AttributeNameMap.put(attrName, attribute);
				CountryMeta.positionMap.put(position, attribute);
			} else {
				// do nothing for non attribute property;
			}
		}

		System.out.println("readConfig finished");
	}

	public static void pupulateNormalizeRow(Row row, CountryYearData countryYearData) {
		row.createCell(0).setCellValue(countryYearData.year);
		int columnId = 1;
		for (Iterator<Object> iterator = countryYearData.values.iterator(); iterator.hasNext(); columnId++) {
			Object value = (Object) iterator.next();
			PoiUtil.setCell(row.createCell(columnId), value);
		}
	}
	
	public static void populateNormalizedHeader(Row row) {
		row.createCell(0).setCellValue("Year");
		int columnId=1;
		for (Iterator<Entry<Integer, Attribute>> iterator = CountryMeta.positionMap.entrySet().iterator(); iterator.hasNext(); columnId++) {
			Entry<Integer, Attribute> entry = (Entry<Integer, Attribute>) iterator.next();
			row.createCell(columnId).setCellValue(entry.getValue().name);
		}
	}
	
	
	public static void readSourceExcel() {
		countryList = new ArrayList<Country>();
		try {

			Workbook wb = new XSSFWorkbook(SRC_PATH);
			Sheet sheet = wb.getSheet(SRC_SHEET);
			for (int rowId = START_ROW; rowId < sheet.getLastRowNum(); rowId++) {
				Row row = sheet.getRow(rowId);
				Country country = new Country();
				countryList.add(country);
				int normalizedPosition = 1;
				for (Iterator<Entry<Integer, Attribute>> iterator = CountryMeta.positionMap.entrySet().iterator(); iterator.hasNext(); normalizedPosition++) {
					Entry<Integer, Attribute> entry = (Entry<Integer, Attribute>) iterator.next();
					int position = entry.getKey();
					boolean yearbased = entry.getValue().yearBased;
					for (int year = START_YEAR; year <= END_YEAR; year++){
						if (normalizedPosition == 1) {
							country.rows[year - START_YEAR].year = year;
						}
						if (!yearbased) {
							country.rows[year - START_YEAR].values.add( PoiUtil.getCellValue(row.getCell(position)) );
						} else {
							country.rows[year - START_YEAR].values.add( PoiUtil.getCellValue(row.getCell(position + year - START_YEAR)) );
						}
					}	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("read ended");
	}
	
	public static void writeTargetExcel()	{
		try {

			Workbook wb = new XSSFWorkbook();
			int rowIndex = 0;
			Sheet sheet = wb.createSheet("normalized");
      if (TARGET_FREEZE_COLUMNID !=0 ) {sheet.createFreezePane(TARGET_FREEZE_COLUMNID, 1);}
      
      //todo the filter end field
      sheet.setAutoFilter(CellRangeAddress.valueOf("A1:Y1"));

			Row row = sheet.createRow(rowIndex++);
			populateNormalizedHeader(row);
			
			for (Country country : countryList) {
				for (int i = 0; i < country.rows.length; i++) {
					CountryYearData countryYearData = country.rows[i];
					row = sheet.createRow(rowIndex++);
					pupulateNormalizeRow(row, countryYearData);
				}
			}
			FileOutputStream fileOut = new FileOutputStream(TGT_PATH);
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Write ended");
	}
}
