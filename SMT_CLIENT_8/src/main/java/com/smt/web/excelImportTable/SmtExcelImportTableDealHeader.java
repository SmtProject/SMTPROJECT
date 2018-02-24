package com.smt.web.excelImportTable;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;

/**
 * SMT COPYRIGHT
 */
@SuppressWarnings("rawtypes")
public class SmtExcelImportTableDealHeader implements Serializable {

	private static final long serialVersionUID = 4654359687485672593L;
	private Grid<String> table;
	private SmtExcelImportTableCore core;
	private List<String> headerTable;

	public SmtExcelImportTableDealHeader(File file, Grid<String> table) {
		this(file, table, 0, 1, 2);
	}

	public SmtExcelImportTableDealHeader(File file, Grid<String> table, int titleLineNum, int headerLineNum, int dataLineNum) {
		this.table = table;
		String[] headerTableInfo = table.getDefaultHeaderRow().getComponents().toArray(new String[0]);

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
	private void addDataToTable(Grid<String> table, List<String> header, List<List<Object>> data) {
		if (table != null) {
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
					table.addColumn(header.get(headerNum));
				}
			}
			for (int headerNum = 0; headerNum < header.size(); headerNum++) {
				table.addColumn(header.get(headerNum));
			}
			for (int i = 0; i < data.size(); i++) {
				List<Object> lo = data.get(i);
				List<String> stringList = lo.stream().filter(e->e!=null).map(e->String.valueOf(e)).collect(Collectors.toList());
				table.setItems(stringList);
				
			}
		} else {
			throw new NullPointerException("Table is null! Please checked.");
		}
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
