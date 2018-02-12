package com.smt.web.client.adminView;


import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class FilteredGrid extends Grid{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<?> container;

	public FilteredGrid() {
		super();
	}

	private void initFilter() {
		HeaderRow filterRow = this.appendHeaderRow();
		for (Object pid: this.getContainerDataSource()
				.getContainerPropertyIds()) {
			HeaderCell cell = filterRow.getCell(pid);
			TextField filterField =getColumnFilterField();
			filterField.addTextChangeListener(change -> {
				if(container!=null) {
					container.removeContainerFilters(pid);
					if (! change.getText().isEmpty())
						container.addContainerFilter(
								new SimpleStringFilter(pid,change.getText(), true, false));
				}});
			cell.setComponent(filterField);
		}
	}
	public void setBeanContainerDataSource(BeanItemContainer<?> beanItemContainer) {
		if(beanItemContainer!=null) {
			this.container=beanItemContainer;
			super.setContainerDataSource(beanItemContainer);
			initFilter();
		}
	
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
	        filter.addStyleName(ValoTheme.TEXTFIELD_TINY);
	        return filter;
	    }
}
