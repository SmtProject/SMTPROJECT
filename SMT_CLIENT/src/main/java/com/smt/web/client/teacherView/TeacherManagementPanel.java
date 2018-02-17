package com.smt.web.client.teacherView;

import java.util.Collection;

import com.smt.data.entity.Teacher;
import com.smt.web.client.adminView.FilteredGrid;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.smt.web.client.toolBox.GeneralItemController;
import com.smt.web.client.toolBox.TableColumnFactory;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class TeacherManagementPanel extends VerticalLayout implements GeneralItemController<Teacher>{

	private static final long serialVersionUID = 6217533686005281163L;


	protected final TableName tableName=TableName.TeacherManagement;

	protected FilteredGrid teachersGrid;
	protected BeanItemContainer <Teacher>container;
	private Button addTeacherBtn;

	public TeacherManagementPanel() {
		initComponents();
		initLayout();
		initData();
		initListeners();
	}

	private void initComponents() {
		teachersGrid = new FilteredGrid();
		addTeacherBtn = new Button("Add New");
		addTeacherBtn.setIcon(FontAwesome.USER_PLUS);
	}
	private void initLayout() {
		HorizontalLayout btnsLayout = new HorizontalLayout(addTeacherBtn);
		btnsLayout.addComponent(BtnFactory.ExportGridBtn(teachersGrid,tableName,ColumnsType.ExportColumns));
		btnsLayout.setSpacing(true);
		addComponents(teachersGrid, btnsLayout);
		teachersGrid.setSizeFull();
		setMargin(true);
		setSizeFull();
	}
	private void initData() {
		container =new BeanItemContainer<Teacher>(Teacher.class, getData());
		teachersGrid.setBeanContainerDataSource(container);
		teachersGrid.setColumns(TableColumnFactory.getTableColumn(tableName,ColumnsType.TableColumns));
	}
	
	private void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(null,this));
	}
	
	public Collection<Teacher> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllATeachers();
	}
	@SuppressWarnings("deprecation")
	private void initListeners() {
		addTeacherBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				onAddUserBtnClicked();
			}
		});
		teachersGrid.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 2068314108919135281L;
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()) {
					UI.getCurrent().addWindow(new TeacherDataManagementWindow(container.getItem(event.getItemId()).getBean(),TeacherManagementPanel.this));
				}
			}
		});
	}

	@Override
	public void onItemAddedTriggered(Teacher teacher) {
		container.addBean(teacher);
		teachersGrid.refreshAllRows(); 
	}

	@Override
	public void onItemUpdatedriggered(Teacher teacher) {
		teachersGrid.refreshAllRows(); 
	}
	
}
