package com.smt.web.client.Change.year;

import java.util.Collection;

import com.smt.data.entity.Year;
import com.smt.web.client.adminView.FilteredGrid;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BaseManagementPanel;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.ui.UI;

public class YearTablePanel extends  BaseManagementPanel<Year>{

	public YearTablePanel() {
		super(TableName.YearManagement,false,false);
	}

	private static final long serialVersionUID = -3500769590479299321L;

	@Override
	public ImportState getImportState() {
		return null;
	}

	@Override
	public void initGrid() {
		userGrid=new FilteredGrid<>(tableName, Year.class);
	}

	@Override
	public Collection<Year> getData() {
		return SmtServiceProvider.getInstance().getSmtYearService().getYears();
	}

	@Override
	public void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new YearDataManagementWindows(null,this));		
	}

	@Override
	protected void onRowDoubleClicked(Year item) {
		UI.getCurrent().addWindow(new YearDataManagementWindows(item,YearTablePanel.this));		
	}
	
}
