package com.smt.web.client.adminView;


import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

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
			TextField filterField = new TextField();
			filterField.setColumns(8);
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
}
