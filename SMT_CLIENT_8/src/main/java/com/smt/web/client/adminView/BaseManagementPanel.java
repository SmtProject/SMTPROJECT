package com.smt.web.client.adminView;

import java.util.Collection;

import com.smt.data.entity.SmtUser;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.toolBox.BtnFactory;
import com.smt.web.client.toolBox.RefreshGridController;
import com.smt.web.client.toolBox.TableColumnFactory;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseManagementPanel<T extends SmtUser> extends VerticalLayout implements RefreshGridController {
	
	private static final long serialVersionUID = 8856843837941346930L;
	
	protected FilteredGrid userGrid;
	protected BeanItemContainer<T> container;
	private Button addUserBtn;
	private Button importExcelButton;
	protected TableName tableName;

	public abstract void onBtnSaveClicked(T smtUser) throws FieldGroup.CommitException;

	public abstract void validation(T smtUser) throws FieldGroup.CommitException;

	public abstract ImportState getImportState();
	
	public BaseManagementPanel(TableName tableName) {
		this.tableName=tableName;
		initComponents();
		intiLayout();
		initData();
	}

	private void initComponents() {
		userGrid = new FilteredGrid();
		addUserBtn = new Button("Add New");
		addUserBtn.setIcon(FontAwesome.USER_PLUS);
		importExcelButton = BtnFactory.createImportBtn(getImportState());
		initListeners();
	}

	private void intiLayout() {
		HorizontalLayout btnsLayout = new HorizontalLayout(addUserBtn);
		btnsLayout.addComponent(BtnFactory.ExportGridBtn(userGrid,tableName,ColumnsType.ExportColumns));
		btnsLayout.setSpacing(true);
		btnsLayout.addComponent(importExcelButton);
		addComponents(userGrid, btnsLayout);
		userGrid.setSizeFull();
		setMargin(true);
		setSizeFull();
	}

	public abstract Collection<T> getData();

	@SuppressWarnings("deprecation")
	private void initData() {
		container = new BeanItemContainer<>(getData());
		userGrid.setBeanContainerDataSource(container);
		userGrid.setColumns((Object[])getColumns());
		userGrid.setNonEditableColumns(getNonEditbaleColumns());
		modifyGrid();

	}
	public String[] getColumns() {
		return TableColumnFactory.getTableColumn(tableName,ColumnsType.TableColumns);
	}
	public String[] getNonEditbaleColumns() {
		return TableColumnFactory.getTableColumn(tableName,ColumnsType.NonEditableColumns);
	}

	public abstract void onAddUserBtnClicked();

	private void modifyGrid() {
		userGrid.setEditorEnabled(true);
		userGrid.getEditorFieldGroup().addCommitHandler(new FieldGroup.CommitHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {

			}

			@Override
			public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
				try {
					validation(container.getItem(userGrid.getEditedItemId()).getBean());
					onBtnSaveClicked(container.getItem(userGrid.getEditedItemId()).getBean());
					Notification.show("Done");
				} catch (Exception e) {
					Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
					throw new FieldGroup.CommitException(e.getMessage());
				}
			}

		});
	}

	@SuppressWarnings("deprecation")
	private void initListeners() {
		addUserBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				onAddUserBtnClicked();
			}
		});
		userGrid.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 2068314108919135281L;
			public void itemClick(ItemClickEvent event) {
				onGridItemClickListener(event);
			}
		
		});
	}
	public void onGridItemClickListener(ItemClickEvent event) {
		
	}
}
