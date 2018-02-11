package com.smt.web.excelImportTable;

import java.util.List;

import com.smt.web.client.importExcel.ImportState;

public class SmtExcelContext {
	private ImportState state;
	private List<List<Object>> excelData;
	private List<String> excelHeader;

	public SmtExcelContext(ImportState state, List<List<Object>> excelData, List<String> excelHeader) {
		super();
		this.state = state;
		this.excelData = excelData;
		this.excelHeader = excelHeader;
	}

	public void doAction() {
		state.doAction(this);
	}

	public ImportState getState() {
		return state;
	}

	public void setState(ImportState state) {
		this.state = state;
	}

	public List<List<Object>> getExcelData() {
		return excelData;
	}

	public void setExcelData(List<List<Object>> excelData) {
		this.excelData = excelData;
	}

	public List<String> getExcelHeader() {
		return excelHeader;
	}

	public void setExcelHeader(List<String> excelHeader) {
		this.excelHeader = excelHeader;
	}

}
