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
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

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
			userGrid.addComponentColumn(teacher -> {
			      Button button = new Button("Can Teach");
			      button.setIcon(VaadinIcons.BOOK);
			      button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
			      button.addClickListener(click ->{
			    	  TeachingGradesManagementPanel permissionsManagementPanel = new TeachingGradesManagementPanel(teacher);
			    	  UI.getCurrent().addWindow(permissionsManagementPanel);
			    	  permissionsManagementPanel.center();
			      });
			      return button;
			});

	}

	@Override
	protected void onRowDoubleClicked(Teacher item) {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(item,TeacherManagementPanel.this));
	}

}
