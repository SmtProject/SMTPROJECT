package com.smt.web.excelImportTable;

import java.util.List;
import java.util.Map;

import com.smt.web.client.importExcel.ImportState;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.Resource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

/**
 * SMT COPYRIGHT
 */
public class SmtExcelImportTable extends CustomComponent {

	private static final long serialVersionUID = -8650980069490287789L;

	@AutoGenerated
	private VerticalLayout mainLayout;

	private SmtUploadComponent upload;
	
	private ImportState state;
	
	public SmtExcelImportTable(Grid<Map<String,String>> table, ImportState state) {
		this.state = state;
		buildMainLayout();
		setCompositionRoot(mainLayout);
		upload = new SmtUploadComponent(table,state);
		upload.setExcelLineNum(0, 0, 1);
		mainLayout.addComponent(upload);
	}

	public SmtExcelImportTable(Grid<Map<String,String>> table, int titleLineNum, int headerLineNum, int dataLineNum) {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		upload = new SmtUploadComponent(table,state);
		upload.setExcelLineNum(titleLineNum - 1, headerLineNum - 1, dataLineNum - 1);
		mainLayout.addComponent(upload);
	}

	/** set upload button caption */
	public void setCaption(String caption) {
		this.upload.setCaption(caption);
	}

	/** set import button icon */
	public void setIcon(Resource icon) {
		this.upload.setIcon(icon);
	}

	/** Get Excel File Title */
	public String getExcelTitle() {
		return upload.getExcelTitle();
	}

	/** Get Excel File Header */
	public List<String> getExcelHeader() {
		return upload.getExcelHeader();
	}

	/** Get Excel File Data */
	public List<List<Object>> getExcelData() {
		return upload.getExcelData();
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");

		return mainLayout;
	}

}
