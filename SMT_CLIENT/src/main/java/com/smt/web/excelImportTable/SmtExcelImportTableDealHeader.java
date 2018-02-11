package com.smt.web.excelImportTable;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

/**
 * SMT COPYRIGHT
 */
@SuppressWarnings("rawtypes")
public class SmtExcelImportTableDealHeader implements Serializable {

	private static final long serialVersionUID = 4654359687485672593L;
	private Table table;
	private SmtExcelImportTableCore core;
	private List<String> headerTable;

	public SmtExcelImportTableDealHeader(File file, Table table) {
		this(file, table, 0, 1, 2);
	}

	public SmtExcelImportTableDealHeader(File file, Table table, int titleLineNum, int headerLineNum, int dataLineNum) {
		this.table = table;
		String[] headerTableInfo = table.getColumnHeaders();

		core = new SmtExcelImportTableCore(file, titleLineNum, headerLineNum, dataLineNum);
		core.dealExcel();

		if (headerTableInfo == null || headerTableInfo.length == 0) {
			addDataToTable(table, core.getHeaderExcel(), core.getTableExcel());
		} else {

			headerTable = new SmtTableHeadDeal(
					SmtTableHeadDeal.chooseDealString(headerTableInfo)).tableHeadBottomParams;

			if (judgeHeader(headerTable, core.getHeaderExcel())) {
				addDataToTable(table, null, core.getTableExcel());
			}
		}
	}

	// Judge whether header matching success
	public boolean judgeHeader(List<String> one, List<String> other) {
		if (one.equals(other)) {
			return true;
		} else {
			Notification
					.show("Import Wrong!",
							"<br/>Header information matching failure" + "<br/> Table:<br/>&nbsp;&nbsp;&nbsp;&nbsp;"
									+ one + "<br/> Excel:<br/>&nbsp;&nbsp;&nbsp;&nbsp;" + other,
							Notification.Type.WARNING_MESSAGE);
			return false;
		}
	}

	// set data to Table
	private void addDataToTable(Table table, List<String> header, List<List<Object>> data) {
		if (table != null) {
			int tableSize = table.size();

			if (header != null) {

				// The table first name repeat the error detection
				int oldSize;
				int newSize;
				Set<String> sets = new HashSet<String>();
				for (int headerNum = 0; headerNum < header.size(); headerNum++) {
					String item = header.get(headerNum);
					oldSize = sets.size();
					sets.add(item);
					newSize = sets.size();
					if (newSize == oldSize) {
						Notification.show("Import Error",
								"<br/>In header line number : " + (char) (headerNum + 65) + "" + "<br/>Field : [<b> "
										+ item + "</b>] " + "<br/><i>Header Field Repeat</i>",
								Notification.Type.WARNING_MESSAGE);
						return;
					}
				}

				for (int headerNum = 0; headerNum < header.size(); headerNum++) {
					table.addContainerProperty(header.get(headerNum), String.class, null);
				}
			}

			List<Class> tableHeaderClass = getHeaderClass(table.getColumnHeaders());
			for (int i = 0; i < data.size(); i++) {
				List<Object> lo = data.get(i);
				List<String> stringList = lo.stream().filter(e->e!=null).map(e->String.valueOf(e)).collect(Collectors.toList());
				if (header == null) {
					if (detectionDataType(lo, tableHeaderClass) != -1) {
						Notification.show("Import Error", "<br/>Excel File No : " + i + "Line Date Error",
								Notification.Type.WARNING_MESSAGE);
					}
				}
				table.addItem(stringList.toArray(), new Integer(++tableSize));
			}
		} else {
			throw new NullPointerException("Table is null! Please checked.");
		}
	}

	// Header type
	private List<Class> getHeaderClass(Object[] objects) {
		List<Class> clazzes = new ArrayList<Class>();
		for (Object object : objects) {
			clazzes.add(table.getType(object));
		}
		return clazzes;
	}

	// Detects the data type
	private int detectionDataType(List<Object> dataCells, List<Class> headerClasses) {
		for (int i = 0; i < headerClasses.size(); i++) {
			if (dataCells.get(i).getClass() != headerClasses.get(i)) {

				// Special type conversion 1.Integer->Double
				if (dataCells.get(i).getClass().equals(Integer.class) && headerClasses.get(i).equals(Double.class)) {
					continue;
				}
				if (headerClasses.get(i).equals(String.class)) {
					continue;
				}
				Notification.show("Import Error",
						"<br/>Error：No : " + i + "Row Row Date Error(Excel,Table) : " + "<br/>["
								+ dataCells.get(i).getClass() + " , " + headerClasses.get(i) + "]" + "</b>] ",
						Notification.Type.WARNING_MESSAGE);
				return i;
			}
		}
		return -1;
	}

	public String getExcelTitle() {
		return core.getTitleExcel();
	}

	public List<String> getExcelHeader() {
		return core.getHeaderExcel();
	}

	public List<List<Object>> getExcelData() {
		return core.getTableExcel();
	}
}
