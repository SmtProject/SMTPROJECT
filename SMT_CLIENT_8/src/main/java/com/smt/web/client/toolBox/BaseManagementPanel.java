package com.smt.web.client.toolBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseManagementPanel<T> extends VerticalLayout implements RefreshGridController,GeneralItemController<T> {
	
	private static final long serialVersionUID = 8856843837941346930L;
	
	protected Grid<T> userGrid;
	protected List<T> data;
	private Button addUserBtn;
	private Button importExcelButton;
	protected TableName tableName;
	protected Label titleLbl;

	public abstract ImportState getImportState();
	
	public BaseManagementPanel(TableName tableName) {
		this.tableName=tableName;
		initComponents();
		intiLayout();
		this.setMargin(false);
		this.setSpacing(false);
	}

	private void initComponents() {
		initGrid();
		data=new ArrayList<>(getData());
		titleLbl=new Label(tableName.toString());
		userGrid.setItems(data);
		addUserBtn = new Button("Add New");
		addUserBtn.setIcon(FontAwesome.USER_PLUS);
		importExcelButton = BtnFactory.createImportBtn(getImportState());
		initListeners();
	}

	public abstract void initGrid();

	private void intiLayout() {
		HorizontalLayout btnsLayout = new HorizontalLayout(addUserBtn);
		btnsLayout.addComponent(BtnFactory.ExportGridBtn(userGrid,tableName,ColumnsType.ExportColumns));
		btnsLayout.setSpacing(true);
		btnsLayout.addComponent(importExcelButton);
		addComponents(titleLbl,userGrid, btnsLayout);
		setComponentAlignment(titleLbl,Alignment.MIDDLE_CENTER);
		userGrid.setSizeFull();
		setSizeFull();
	}

	public abstract Collection<T> getData();

	public String[] getColumns() {
		return TableColumnFactory.getTableColumn(tableName,ColumnsType.TableColumns);
	}
	public String[] getNonEditbaleColumns() {
		return TableColumnFactory.getTableColumn(tableName,ColumnsType.NonEditableColumns);
	}

	public abstract void onAddUserBtnClicked();


	@SuppressWarnings({ "deprecation", "serial" })
	private void initListeners() {
		addUserBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				onAddUserBtnClicked();
			}
		});
	
		userGrid.addItemClickListener(event -> {
		   
		    if (event.getMouseEventDetails().isDoubleClick()) {
		    onRowDoubleClicked(event.getItem());
		    }
		});

	}

	protected abstract void onRowDoubleClicked(T item);
	
	@Override
	public void onItemAddedTriggered(T item) {
		data.add(item);
		userGrid.getDataProvider().refreshAll();
	}

	@Override
	public void onItemUpdatedriggered(T item) {
		userGrid.getDataProvider().refreshAll();
	}
	@Override
	public void refreshGridData() {
		data=new ArrayList<>(getData());
		userGrid.setItems(data);
	}
}
