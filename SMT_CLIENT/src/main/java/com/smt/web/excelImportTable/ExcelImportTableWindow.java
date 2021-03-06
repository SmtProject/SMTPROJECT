package com.smt.web.excelImportTable;

import com.smt.web.client.importExcel.ImportState;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ExcelImportTableWindow extends Window {

	private static final long serialVersionUID = 1051406262161575126L;
	private VerticalLayout layout = new VerticalLayout();
	private ImportState state;
	
	public ExcelImportTableWindow(ImportState state) {
		this.state = state;
		setContent(layout);
		initUI();
	}

	public void initUI() {
		Label label = new Label("Excel Import Table");
		layout.addComponent(label);

		GridLayout gridLayout = new GridLayout(2, 1);
		
		Table table = new Table();
		table.setHeight("500px");
		table.setWidth("600px");
		
		gridLayout.addComponent(table);
		layout.addComponent(gridLayout);
		
		SmtExcelImportTable smtExcelImportTable = new SmtExcelImportTable(table,state);
		layout.addComponent(smtExcelImportTable);
		

	}
}
