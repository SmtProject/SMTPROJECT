package com.smt.web.client.adminView;

import com.smt.web.client.toolBox.TableColumnFactory;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;

public class FilteredGrid<T> extends Grid<T> {

	private static final long serialVersionUID = 1L;

	private TableName tableName;

	public FilteredGrid(TableName tableName, Class<T> areg) {
		super(areg);
		this.tableName = tableName;
		initColumns();
		initFilter();
	}

	private void initColumns() {
		this.setColumns(columns());
	}

	private void initFilter() {
		HeaderRow filterRow = this.appendHeaderRow();
		for (Column<T, ?> column : this.getColumns()) {
			HeaderCell cell = filterRow.getCell(column);
			TextField filterField = createFilterTextField(column);
			cell.setComponent(filterField);
		}
	}

	private String[] columns() {
		return TableColumnFactory.getTableColumn(tableName, ColumnsType.TableColumns);
	}

	public void setNonEditableColumns(String[] columns) {
		for (String string : columns) {
			getColumn(string).setEditable(false);
		}
	}

	public void setHiddenColumns(String[] columns) {
		for (String string : columns) {
			getColumn(string).setHidden(true);
		}
	}

	@SuppressWarnings("unchecked")
	private TextField createFilterTextField(final Column<T, ?> column) {
		final TextField tfFilter = new TextField();
		tfFilter.setCaption("Filter");
		tfFilter.setValueChangeMode(ValueChangeMode.LAZY);
		tfFilter.addStyleName(ValoTheme.TEXTFIELD_TINY);
		tfFilter.setWidth(100, Unit.PERCENTAGE);
		tfFilter.addValueChangeListener(event -> {
			ListDataProvider<T> dataProvider = (ListDataProvider<T>) this.getDataProvider();
			final ValueProvider<T, String> valueProvider = (ValueProvider<T, String>) column.getValueProvider();
			dataProvider.clearFilters();
			if (event == null || event.getValue() == null || event.getValue().isEmpty()) {
				return;
			}
			dataProvider.addFilter(valueProvider, columnFilter -> {
			return columnFilter.toLowerCase().contains(event.getValue().toLowerCase());
			});
			dataProvider.refreshAll();
		});
		return tfFilter;
	}
}
