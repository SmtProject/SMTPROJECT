package com.smt.web.client.teacherView;

import java.util.Collection;

import com.smt.data.entity.Teacher;
import com.smt.web.client.adminView.BaseManagementPanel;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.importExcel.TeacherImportState;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.GeneralItemController;
import com.smt.web.client.toolBox.RefreshGridController;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.UI;

public class TeacherManagementPanel extends BaseManagementPanel<Teacher> implements GeneralItemController<Teacher>,RefreshGridController{
	
	private final static TableName tableName=TableName.TeacherManagement;

	public TeacherManagementPanel() {
		super(tableName);
	}

	private static final long serialVersionUID = 6217533686005281163L;

	
	public void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new TeacherDataManagementWindow(null,this));
	}
	
	public Collection<Teacher> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllATeachers();
	}

	@Override
	public void onItemAddedTriggered(Teacher teacher) {
		container.addBean(teacher);
		userGrid.refreshAllRows(); 
	}

	@Override
	public void onItemUpdatedriggered(Teacher teacher) {
		userGrid.refreshAllRows(); 
	}

	@Override
	public void refreshGridData() {
		container.removeAllItems();
		container.addAll(getData());
		userGrid.refreshAllRows();
	}

	@Override
	public void onBtnSaveClicked(Teacher smtUser) throws CommitException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validation(Teacher smtUser) throws CommitException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ImportState getImportState() {
		return new TeacherImportState(this);
	}
	
	@Override
	public void onGridItemClickListener(ItemClickEvent event) {
		if (event.isDoubleClick()) {
			UI.getCurrent().addWindow(new TeacherDataManagementWindow(container.getItem(event.getItemId()).getBean(),TeacherManagementPanel.this));
		}
	}
	
}
