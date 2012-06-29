package charlie.feng.tools.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import charlie.feng.tools.excel.dom.Country;
import charlie.feng.tools.excel.dom.DenormRow;

public class CopyOfPoiConverter {
	public static String SRC_PATH = null;
	public static String TGT_PATH = null;
	public static int START_YEAR = 1980;
	public static int END_YEAR = 2009;
	public static int FDI_START_YEAR = 1984;
	public static int FDI_START_COLUMN = 6;
	public static int GDPS_START_COLUMN = 33;
	public static int GDPD_START_COLUMN = 64;
	public static int SKILLD_START_COLUMN = 96;
	public static int EM_START_YEAR = 1995;
	public static int EM_START_COLUMN = 152;
	public static int LIFE_START_YEAR = 1980;
	public static int LIFE_START_COLUMN = 169;
	public static int NET_START_COLUMN = 127;

	public static void main(String[] args) throws Exception {
		System.out.println(charlie.feng.tools.excel.CopyOfPoiConverter.class.getClassLoader().getResource("."));
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		prop.load(fis);
		fis.close();
		SRC_PATH = prop.getProperty("SRC_PATH");
		TGT_PATH = prop.getProperty("TGT_PATH");
		new CopyOfPoiConverter().yearToAttr();
	}

	public Object getCellValue(HSSFCell cell) {
		if (cell == null) {
			return null;
		}
		int type = cell.getCellType();
		Object value;
		switch (type) {

		case org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_NUMERIC:
			value = cell.getNumericCellValue();
			break;
		case org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_BLANK:
			value = null;
			break;
		case org.apache.poi.hssf.usermodel.HSSFCell.CELL_TYPE_FORMULA:
			try {
				value = cell.getNumericCellValue();

			} catch (Exception e) {
				try {
				  value = cell.getStringCellValue();
				} catch(RuntimeException e2) {
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

	public void generateDenormHeader(HSSFRow row) {
		row.createCell(0).setCellValue("Filter0");
		row.createCell(1).setCellValue("Filter1");
		row.createCell(2).setCellValue("Filter2");
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
	}

	public void generateDenormRow(HSSFRow row, DenormRow denormRow) {
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
		setCell(row.createCell(21), "No");
		setCell(row.createCell(22), denormRow.EM_Flow);
		setCell(row.createCell(23), denormRow.LifeD);
	}

	private void setCell(HSSFCell cell, Object value) {
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
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(SRC_PATH);
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheet("sum");
			boolean headerProcessed = false;
			double FDI_T = 0;
			int FDI_T_Count = 0;
			double GDPS_T = 0;
			int GDPS_T_Count = 0;
			double GDPD_T = 0;
			int GDPD_T_Count = 0;

			for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
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
				country.country_En = row.getCell(4).getStringCellValue();
				country.country_Zh = row.getCell(5).getStringCellValue();
				country.dist = getCellValue(row.getCell(141));
				// country.lang = getCellValue(row.getCell(137));
				for (int j = 0; j <= (END_YEAR - START_YEAR); j++) {
					country.rows[j].year = START_YEAR + j;

					// FDI
					if (j >= (FDI_START_YEAR - START_YEAR)) {
						country.rows[j].FDI = getCellValue(row.getCell(FDI_START_COLUMN - (FDI_START_YEAR - START_YEAR) + j));
						try {
							double value = row.getCell(FDI_START_COLUMN - (FDI_START_YEAR - START_YEAR) + j).getNumericCellValue();
							FDI_T += value;
							FDI_T_Count++;
						} catch (Exception e) {
							// do nothing for non numberic field
						}

					}

					// GDP
					country.rows[j].GDPS = getCellValue(row.getCell(33 + j));
					country.rows[j].GDPD = getCellValue(row.getCell(64 + j));

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
					} else {
						country.rows[j].Net = ".";
						country.rows[j].Net_Pri = ".";
						country.rows[j].Net_Sec = ".";
						country.rows[j].Net_Ter = ".";
						country.rows[j].Net_NT = ".";
					}
					if (j % 10 == 0) {
						country.rows[j].Net2 = getCellValue(row.getCell(NET_START_COLUMN + 10 + j / 10));
					} else {
						country.rows[j].Net2 = ".";
					}

					// EM
					if (j >= (EM_START_YEAR - START_YEAR)) {
						country.rows[j].EM_Flow = getCellValue(row.getCell(EM_START_COLUMN - (EM_START_YEAR - START_YEAR) + j));
					}
					//LifeD
					if (j >= (LIFE_START_YEAR - START_YEAR)) {
						country.rows[j].LifeD = getCellValue(row.getCell(LIFE_START_COLUMN - (LIFE_START_YEAR - START_YEAR) + j));
					}

				}
			}
			// for (Country country : countryList) {
			// System.out.println(country.country_En + " " + country.country_Zh + " "
			// + country.dist);
			// }

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
			}
		}

		System.out.println("read ended");
		try {
			int rowIndex = 0;
			// fos = new FileOutputStream(TGT_PATH);
			// POIFSFileSystem fs = new POIFSFileSystem(fos);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("denorm");
			HSSFRow row = sheet.createRow(rowIndex++);
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
