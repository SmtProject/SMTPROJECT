package com.smt.web.client.studentsView;

import java.util.Collection;

import com.smt.data.entity.Student;
import com.smt.web.client.adminView.FilteredGrid;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.importExcel.StudentsImportState;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BaseManagementPanel;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.ui.UI;

public class StudentsManagementPanel extends  BaseManagementPanel<Student>{
	public StudentsManagementPanel() {
		super(TableName.StudentsManagement);
	}

	private static final long serialVersionUID = -4234381369066216276L;

	@Override
	public ImportState getImportState() {
		return new StudentsImportState(this);
	}

	@Override
	public void initGrid() {
		userGrid=new FilteredGrid<Student>(tableName,Student.class);		
	}

	@Override
	public Collection<Student> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllStudents();
	}

	@Override
	public void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new StudentsDataManagementWindow(null,StudentsManagementPanel.this));
	}

	@Override
	protected void onRowDoubleClicked(Student item) {
		UI.getCurrent().addWindow(new StudentsDataManagementWindow(item,StudentsManagementPanel.this));
	}

}
