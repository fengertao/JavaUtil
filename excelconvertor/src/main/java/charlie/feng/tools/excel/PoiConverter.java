package charlie.feng.tools.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import charlie.feng.tools.excel.dom.Country;
import charlie.feng.tools.excel.dom.DenormRow;

@SuppressWarnings("unused")
public class PoiConverter {
	public static String SRC_PATH = null;
	public static String TGT_PATH = null;
	//although the start year is 1980, but most data start from 1984, especially FDI
	public static int START_YEAR = 1980;
	public static int END_YEAR = 2010;
	public static int FDI_START_COLUMN = 7;
	public static int GDPS_START_COLUMN = 45;
	public static int GDPD_START_COLUMN = 83;
	public static int SKILLD_START_COLUMN = 122;
	public static int NET_START_COLUMN = 160;
	public static int NET2_START_COLUMN = NET_START_COLUMN + 5 * 3; //5 column/decade * 3 decade
	public static int EM_START_YEAR = 1995;
	public static int EM_START_COLUMN = 194;	//column name EM_1995, for oversea chinese database
	public static int LIFED_START_COLUMN = 218;
	public static int LIFE_START_COLUMN = 256;
	public static int NET3_START_COLUMN = 308;

	public static int DISTANCE_COLUMN = 180;
	public static int LANG_COLUMN = -1;		//Todo, lang is not used in china
	
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(charlie.feng.tools.excel.PoiConverter.class.getClassLoader().getResource("."));
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		prop.load(fis);
		fis.close();
		SRC_PATH = prop.getProperty("SRC_PATH");
		TGT_PATH = prop.getProperty("TGT_PATH");
		new PoiConverter().yearToAttr();
	}

	public Object getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}

		int type = cell.getCellType();
		Object value;
		switch (type) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = null;
			break;
		case Cell.CELL_TYPE_FORMULA:
			try {
				value = cell.getNumericCellValue();

			} catch (Exception e) {
				try {
					value = cell.getStringCellValue();
				} catch (RuntimeException e2) {
					System.out.println("error cell:" + cell);
					throw e2;
				}
			}
			break;
		default:
			throw new RuntimeException("cannot get value of cell(" + cell.getRowIndex() + "," + cell.getColumnIndex() + ")");
		}
		return value;

	}

	public void generateDenormHeader(Row row) {
		row.createCell(0).setCellValue("Filter0");
		row.createCell(1).setCellValue("Filter1");
		Cell filter2 = row.createCell(2);
		filter2.setCellValue("Filter2");
		row.createCell(3).setCellValue("Filter3");
		row.createCell(4).setCellValue("Country_En");
		row.createCell(5).setCellValue("Country_Zh");
		row.createCell(6).setCellValue("Year");
		row.createCell(7).setCellValue("FDI");
		row.createCell(8).setCellValue("FDI_T");
		row.createCell(9).setCellValue("GDPs");
		row.createCell(10).setCellValue("GDPs_T");
		row.createCell(11).setCellValue("GDPd");
		row.createCell(12).setCellValue("GDPd_T");
		row.createCell(13).setCellValue("SKd");
		row.createCell(14).setCellValue("Net");
		row.createCell(15).setCellValue("Net_Pri");
		row.createCell(16).setCellValue("Net_Sec");
		row.createCell(17).setCellValue("Net_Ter");
		row.createCell(18).setCellValue("Net_NT");
		row.createCell(19).setCellValue("Net2");
		row.createCell(20).setCellValue("Dist");
		row.createCell(21).setCellValue("Lang");
		row.createCell(22).setCellValue("EM_Flow");
		row.createCell(23).setCellValue("Lifed");
		row.createCell(24).setCellValue("Life");
		row.createCell(25).setCellValue("Net3");
	}

	public void generateDenormRow(Row row, DenormRow denormRow) {
		setCell(row.createCell(0), denormRow.parent.filter0);
		setCell(row.createCell(1), denormRow.parent.filter1);
		setCell(row.createCell(2), denormRow.parent.filter2);
		setCell(row.createCell(3), denormRow.parent.filter3);
		setCell(row.createCell(4), denormRow.parent.country_En);
		setCell(row.createCell(5), denormRow.parent.country_Zh);
		setCell(row.createCell(6), denormRow.year);
		setCell(row.createCell(7), denormRow.FDI);
		setCell(row.createCell(8), denormRow.FDI_T);
		setCell(row.createCell(9), denormRow.GDPS);
		setCell(row.createCell(10), denormRow.GDPS_T);
		setCell(row.createCell(11), denormRow.GDPD);
		setCell(row.createCell(12), denormRow.GDPD_T);
		setCell(row.createCell(13), denormRow.skillD);
		setCell(row.createCell(14), denormRow.Net);
		setCell(row.createCell(15), denormRow.Net_Pri);
		setCell(row.createCell(16), denormRow.Net_Sec);
		setCell(row.createCell(17), denormRow.Net_Ter);
		setCell(row.createCell(18), denormRow.Net_NT);
		setCell(row.createCell(19), denormRow.Net2);
		setCell(row.createCell(20), denormRow.parent.dist);
		// setCell(row.createCell(21), denormRow.parent.lang);
		setCell(row.createCell(21), "Not Implemented in China");
		setCell(row.createCell(22), denormRow.EM_Flow);
		setCell(row.createCell(23), denormRow.LifeD);
		setCell(row.createCell(24), denormRow.Life);
		setCell(row.createCell(25), denormRow.Net3);
	}

	private void setCell(Cell cell, Object value) {
		if (value == null) {
			cell.setCellValue(".");
			return;
		}
		if (value.getClass().equals(String.class)) {
			cell.setCellValue((String) value);
		} else if (value.getClass().equals(Boolean.class)) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((Double) value);
		}
	}

	public void yearToAttr() {
		List<Country> countryList = new ArrayList<Country>();
		try {

			Workbook wb = new XSSFWorkbook(SRC_PATH);
			Sheet sheet = wb.getSheet("sum");
			boolean headerProcessed = false;
			double FDI_T = 0;
			int FDI_T_Count = 0;
			double GDPS_T = 0;
			int GDPS_T_Count = 0;
			double GDPD_T = 0;
			int GDPD_T_Count = 0;

			for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (!headerProcessed) {
					if (row.getCell(1) != null && (row.getCell(1).getCellType() == 1) && (row.getCell(1).getStringCellValue().startsWith("Filter"))) {
						headerProcessed = true;
					}
					continue;
				}
				Country country = new Country();
				countryList.add(country);
				country.filter0 = getCellValue(row.getCell(0));
				country.filter1 = getCellValue(row.getCell(1));
				country.filter2 = getCellValue(row.getCell(2));
				country.filter3 = getCellValue(row.getCell(3));
				country.country_Zh = row.getCell(4).getStringCellValue();
				country.country_En = row.getCell(5).getStringCellValue();
				country.dist = getCellValue(row.getCell(DISTANCE_COLUMN));
				// country.lang = getCellValue(row.getCell(137));
				for (int j = 0; j <= (END_YEAR - START_YEAR); j++) {
					country.rows[j].year = START_YEAR + j;

					// FDI
					country.rows[j].FDI = getCellValue(row.getCell(FDI_START_COLUMN + j));
					try {
						double value = row.getCell(FDI_START_COLUMN + j).getNumericCellValue();
						FDI_T += value;
						FDI_T_Count++;
					} catch (Exception e) {
						// do nothing for non numberic field
					}

					// GDP
					country.rows[j].GDPS = getCellValue(row.getCell(GDPS_START_COLUMN + j));
					country.rows[j].GDPD = getCellValue(row.getCell(GDPD_START_COLUMN + j));

					// calculate India average FDI/GDPd/GDPS at 1991~2000 and 2001~2008
					try {
						double value = row.getCell(GDPS_START_COLUMN + j).getNumericCellValue();
						GDPS_T += value;
						GDPS_T_Count++;
					} catch (Exception e) {
						// do nothing for non numberic field
					}
					try {
						double value = row.getCell(GDPD_START_COLUMN + j).getNumericCellValue();
						GDPD_T += value;
						GDPD_T_Count++;
					} catch (Exception e) {
						// do nothing for non numberic field
					}

					// FDI, GDP total

					//Todo add 2000 logic
					if (j == (1990 - START_YEAR) || j == (2000 - START_YEAR) || j == (END_YEAR - START_YEAR)) {
						country.rows[j].FDI_T = (FDI_T_Count == 0) ? "." : FDI_T / FDI_T_Count;
						FDI_T = 0;
						FDI_T_Count = 0;
						country.rows[j].GDPS_T = (GDPS_T_Count == 0) ? "." : GDPS_T / GDPS_T_Count;
						GDPS_T = 0;
						GDPS_T_Count = 0;
						country.rows[j].GDPD_T = (GDPD_T_Count == 0) ? "." : GDPD_T / GDPD_T_Count;
						GDPD_T = 0;
						GDPD_T_Count = 0;
					}

					// SKILLD
					country.rows[j].skillD = getCellValue(row.getCell(SKILLD_START_COLUMN + j));

					// Net
					if (j == (1990 - 1980)) {
						country.rows[j].Net = getCellValue(row.getCell(NET_START_COLUMN));
						country.rows[j].Net_Pri = getCellValue(row.getCell(NET_START_COLUMN + 1));
						country.rows[j].Net_Sec = getCellValue(row.getCell(NET_START_COLUMN + 2));
						country.rows[j].Net_Ter = getCellValue(row.getCell(NET_START_COLUMN + 3));
						country.rows[j].Net_NT = getCellValue(row.getCell(NET_START_COLUMN + 4));
					} else if (j == (2000 - 1980)) {
						country.rows[j].Net = getCellValue(row.getCell(NET_START_COLUMN + 5));
						country.rows[j].Net_Pri = getCellValue(row.getCell(NET_START_COLUMN + 6));
						country.rows[j].Net_Sec = getCellValue(row.getCell(NET_START_COLUMN + 7));
						country.rows[j].Net_Ter = getCellValue(row.getCell(NET_START_COLUMN + 8));
						country.rows[j].Net_NT = getCellValue(row.getCell(NET_START_COLUMN + 9));
						//Todo add 2000 logic
					} else {
						country.rows[j].Net = ".";
						country.rows[j].Net_Pri = ".";
						country.rows[j].Net_Sec = ".";
						country.rows[j].Net_Ter = ".";
						country.rows[j].Net_NT = ".";
					}
					if (j % 10 == 0) {
						country.rows[j].Net2 = getCellValue(row.getCell(NET2_START_COLUMN + j / 10));
						country.rows[j].Net3 = getCellValue(row.getCell(NET3_START_COLUMN + j / 10));

					} else {
						country.rows[j].Net2 = ".";
						country.rows[j].Net3 = ".";
					}

					// EM
					if (j >= (EM_START_YEAR - START_YEAR)) {
						country.rows[j].EM_Flow = getCellValue(row.getCell(EM_START_COLUMN - (EM_START_YEAR - START_YEAR) + j));
					}
					// LifeD
					country.rows[j].LifeD = getCellValue(row.getCell(LIFED_START_COLUMN + j));
					country.rows[j].Life = getCellValue(row.getCell(LIFE_START_COLUMN + j));

				}
			}
			// for (Country country : countryList) {
			// System.out.println(country.country_En + " " + country.country_Zh + " "
			// + country.dist);
			// }

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("read ended");
		try {

			int rowIndex = 0;
			Workbook wb = new XSSFWorkbook();
			Sheet sheet = wb.createSheet("denorm");
      sheet.createFreezePane(7, 1);
      AutoFilter filter = sheet.setAutoFilter(CellRangeAddress.valueOf("A1:Y1"));

			Row row = sheet.createRow(rowIndex++);
			generateDenormHeader(row);
			for (Country country : countryList) {
				for (int i = 0; i < country.rows.length; i++) {
					DenormRow denormRow = country.rows[i];
					row = sheet.createRow(rowIndex++);
					generateDenormRow(row, denormRow);
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
