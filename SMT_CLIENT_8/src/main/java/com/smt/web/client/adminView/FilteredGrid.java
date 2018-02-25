package com.smt.web.client.adminView;


import java.util.Collection;

import org.apache.poi.ss.formula.functions.T;

import com.smt.data.entity.SmtUser;
import com.smt.web.client.toolBox.TableColumnFactory;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;

public class FilteredGrid <T>extends Grid<T>{

	private static final long serialVersionUID = 1L;
//	private BeanItemContainer<?> container;
	
	private TableName tableName;

	public FilteredGrid(TableName tableName,Class<T>areg) {
		super(areg);
		this.tableName=tableName;
		initColumns();
		initFilter();
	}

	private void initColumns() {
		this.setColumns(columns());	
	}

	private void initFilter() {
//		HeaderRow filterRow = this.appendHeaderRow();
//		for (String column:columns()) {
//			HeaderCell cell = filterRow.getCell(column);
//			TextField filterField =getColumnFilterField();
//			filterField.addValueChangeListener(e-> {
//			
//			});
//			cell.setComponent(filterField);
//		}
	}

	private String[] columns() {
		return TableColumnFactory.getTableColumn(tableName,ColumnsType.TableColumns);
	}

	public void setNonEditableColumns(String [] columns) {
		for (String string : columns) {
			getColumn(string).setEditable(false);
		}
	}
	public void setHiddenColumns(String [] columns) {
		for (String string : columns) {
			getColumn(string).setHidden(true);
		}
	}
	 private TextField getColumnFilterField() {
	        TextField filter = new TextField();
	        filter.setWidth("100%");
	        filter.setCaption("Filter");
	        filter.addStyleName(ValoTheme.TEXTFIELD_TINY);
	        return filter;
	    }
}
