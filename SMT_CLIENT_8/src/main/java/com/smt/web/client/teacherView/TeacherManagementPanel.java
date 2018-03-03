package com.smt.web.client.teacherView;

import java.util.Collection;

import com.smt.data.entity.Teacher;
import com.smt.web.client.adminView.FilteredGrid;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.importExcel.TeacherImportState;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BaseManagementPanel;
import com.smt.web.client.toolBox.GeneralItemController;
import com.smt.web.client.toolBox.RefreshGridController;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.ui.UI;

public class TeacherManagementPanel extends BaseManagementPanel<Teacher> implements RefreshGridController{
	private static final long serialVersionUID = 6217533686005281163L;
	private final static TableName tableName=TableName.TeacherManagement;

	public TeacherManagementPanel() {
		super(tableName);
		
	}
	
	public void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(null,this));
	}
	
	public Collection<Teacher> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllATeachers();
	}

	@Override
	public ImportState getImportState() {
		return new TeacherImportState(this);
	}

	@Override
	public void initGrid() {
		userGrid=new FilteredGrid<Teacher>(tableName,Teacher.class);	
	}

	@Override
	protected void onRowDoubleClicked(Teacher item) {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(item,TeacherManagementPanel.this));
	}

}
