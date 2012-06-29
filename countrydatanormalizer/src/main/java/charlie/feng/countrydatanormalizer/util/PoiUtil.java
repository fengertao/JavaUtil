package charlie.feng.countrydatanormalizer.util;

import org.apache.poi.ss.usermodel.Cell;

public class PoiUtil {

	public static Object getCellValue(Cell cell) {
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
				} catch (Exception e2) {
					System.out.printf("Warning: Error formula in cell Column£º %s Row: %s, replaced to double . in target excel\n", cell.getColumnIndex(), cell.getRowIndex());
					value = ".";
					
				}
			}
			break;
		default:
			throw new RuntimeException("cannot get value of cell(" + cell.getRowIndex() + "," + cell.getColumnIndex() + ")");
		}
		return value;

	}
	
	public static void setCell(Cell cell, Object value) {
		if (value == null) {
			cell.setCellValue(".");
			return;
		}
		if (value.getClass().equals(String.class)) {
			String strValue = (String) value;
			cell.setCellValue(strValue.equals("..")?".":strValue); //Some data source use ".." rep missing data. stat use "."
		} else if (value.getClass().equals(Boolean.class)) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((Double) value);
		}
	}


}
