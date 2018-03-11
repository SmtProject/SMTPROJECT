package com.smt.web.client.teacherView;

import java.util.Collection;

import com.smt.data.entity.Teacher;
import com.smt.web.client.adminView.FilteredGrid;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.importExcel.TeacherImportState;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BaseManagementPanel;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.ui.UI;
import com.vaadin.ui.renderers.ButtonRenderer;

import smt.model.tools.Role;

public class TeacherManagementPanel extends BaseManagementPanel<Teacher>{
	private static final long serialVersionUID = 6217533686005281163L;
	private final static TableName tableName=TableName.TeacherManagement;

	public TeacherManagementPanel() {
		super(tableName,true,true);
		
	}
	
	public void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(null,this));
	}
	
	public Collection<Teacher> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllTeachers();
	}

	@Override
	public ImportState getImportState() {
		return new TeacherImportState(this);
	}

	@Override
	public void initGrid() {
		userGrid=new FilteredGrid<Teacher>(tableName,Teacher.class);	
		MainUi current = (MainUi)UI.getCurrent();
		if(current.getSmtUser().getSmtRole().equals(Role.Admin))
		userGrid.addColumn(person -> "View Teaching grades",
				new ButtonRenderer<Teacher>(clickEvent -> {
					TeachingGradesManagementPanel permissionsManagementPanel = new TeachingGradesManagementPanel(clickEvent.getItem());
					UI.getCurrent().addWindow(permissionsManagementPanel);
					permissionsManagementPanel.center();
				})).setCaption("Teaching grades");

	}

	@Override
	protected void onRowDoubleClicked(Teacher item) {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(item,TeacherManagementPanel.this));
	}

}
