package com.smt.web.excelImportTable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 * SMT COPYRIGHT
 */
public class SmtExcelImportTableCore implements Serializable {

	private static final long serialVersionUID = -3927445838606118342L;

	protected HSSFWorkbook workbook;

	protected FileInputStream is = null;
	protected File file;

	/** The totals row. */
	protected Row titleRow, headerRow;

	/**
	 * Auxiliary storage
	 */
	private int titleLineNum = -1;
	private int headerLineNum = -1;
	private int dataLineNum = -1;

	private String titleExcel;
	private List<String> headerExcel;
	private List<List<Object>> tableExcel;

	public SmtExcelImportTableCore() {
	}

	public SmtExcelImportTableCore(File file) {
		this.file = file;
		excelToHSSFWorkbook(file);
	}

	public SmtExcelImportTableCore(File file, int titleLineNum, int headerLineNum, int dataLineNum) {
		this.file = file;
		this.titleLineNum = titleLineNum;
		this.headerLineNum = headerLineNum;
		this.dataLineNum = dataLineNum;
		excelToHSSFWorkbook(file);
	}

	public void setLineInfo(int titleLineNum, int headerLineNum, int dataLineNum) {
		this.titleLineNum = titleLineNum;
		this.headerLineNum = headerLineNum;
		this.dataLineNum = dataLineNum;
	}

	public void setExcel(File file) {
		this.file = file;
		excelToHSSFWorkbook(file);
	}

	public void excelToHSSFWorkbook(File file) {
		try {
			is = new FileInputStream(file);
			workbook = new HSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dealExcel() {
		HSSFSheet childSheet = workbook.getSheetAt(0);
		perfectLineData(childSheet);
		dealExcelTitle(childSheet);
		dealExcelHeader(childSheet);
		dealExcelData(childSheet);
	}

	private void perfectLineData(HSSFSheet childSheet) {
		if (titleLineNum < 0) {
			titleLineNum = 0;
		}
		if (headerLineNum < 0) {
			headerLineNum = 1;
		}
		if (dataLineNum < 0) {
			dataLineNum = 2;
		}
	}

	private void dealExcelTitle(HSSFSheet childSheet) {
		titleRow = childSheet.getRow(titleLineNum);
		titleExcel = titleRow.getCell(0).getStringCellValue();
	}

	private void dealExcelHeader(HSSFSheet childSheet) {
		headerExcel = new ArrayList<String>();
		int start = headerLineNum;
		int end = dataLineNum;
		List<String> lastHeaderExcel = null;// The provisional auxiliary storage
											// for header processing
		for (int rowNum = start; rowNum < end; rowNum++) {
			HSSFRow row = childSheet.getRow(rowNum);
			if (null != row) {
				for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
					HSSFCell cell = row.getCell(cellNum);
					if (rowNum == start) {
						headerExcel.add(cell.getStringCellValue());
					} else if (!(cell.getStringCellValue() == null) && !cell.getStringCellValue().equals("")) {

						String upperHeader = new String();
						for (int upper = cellNum; upper >= 0; upper--) {
							if (!lastHeaderExcel.get(upper).equals("")) {
								upperHeader = lastHeaderExcel.get(upper) + "->";
								break;
							}
						}

						headerExcel.set(cellNum, upperHeader + cell.getStringCellValue());
					}

				}
				lastHeaderExcel = new ArrayList<String>(headerExcel);
			}
		}
	}

	private void dealExcelData(HSSFSheet childSheet) {
		int start = dataLineNum;
		int end = childSheet.getPhysicalNumberOfRows();
		tableExcel = new ArrayList<List<Object>>();
		for (int rowNum = start; rowNum < end; rowNum++) {
			HSSFRow row = childSheet.getRow(rowNum);
			List<Object> rowData = new ArrayList<Object>();
			if (null != row) {
				for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
					HSSFCell cell = row.getCell(cellNum);
					rowData.add(ReadCellValue(cell));
				}
				tableExcel.add(rowData);
			}

		}
	}

	private static Object ReadCellValue(HSSFCell cell) {
		Object o = null;
		if (null != cell) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: // 数字
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// Date Type, Date value for the cell
					o = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				} else {
					// If Double / Integer type, the cell Gets Double / Integer
					// value
					double d = cell.getNumericCellValue();
					if (d - (int) d < Double.MIN_VALUE) {
						o = Integer.valueOf((int) d);
					} else {
						o = Double.valueOf(cell.getNumericCellValue());
					}
				}
				break;
			case HSSFCell.CELL_TYPE_STRING: // 字符串
				o = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				o = cell.getBooleanCellValue();
				break;
			case HSSFCell.CELL_TYPE_FORMULA: // 公式
				o = cell.getCellFormula();
				break;
			case HSSFCell.CELL_TYPE_BLANK: // 空值
				o = "";
				break;
			case HSSFCell.CELL_TYPE_ERROR: // 故障
				o = "";
				break;
			default:
				o = "";
				break;
			}
		} else {
			o = null;
		}
		return o;
	}

	public void setTitleLineNum(int titleLineNum) {
		this.titleLineNum = titleLineNum;
	}

	public void setHeaderLineNum(int headerLineNum) {
		this.headerLineNum = headerLineNum;
	}

	public void setDataLineNum(int dataLineNum) {
		this.dataLineNum = dataLineNum;
	}

	public String getTitleExcel() {
		return titleExcel;
	}

	public List<String> getHeaderExcel() {
		return headerExcel;
	}

	public List<List<Object>> getTableExcel() {
		return tableExcel;
	}
}