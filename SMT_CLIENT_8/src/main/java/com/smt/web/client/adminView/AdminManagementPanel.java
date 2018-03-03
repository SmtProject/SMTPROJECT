package com.smt.web.client.adminView;

import java.util.Collection;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.smt.web.client.importExcel.AdminImportState;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.teacherView.TeacherDataManagementWindow;
import com.smt.web.client.teacherView.TeacherManagementPanel;
import com.smt.web.client.toolBox.BaseManagementPanel;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.UI;

public class AdminManagementPanel extends BaseManagementPanel<Admin> {

	public AdminManagementPanel() {
		super(TableName.AdminManagement);
	}

	private static final long serialVersionUID = -568509141630804558L;

	@Override
	public void onAddUserBtnClicked() {
		UI.getCurrent().addWindow(new AdminDataManagementWindow(null,this));
	}

	@Override
	public ImportState getImportState() {
		return new AdminImportState(this);
	}

	@Override
	public Collection<Admin> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllAdmins();
	}

	@Override
	public void initGrid() {
		userGrid=new FilteredGrid<Admin>(tableName,Admin.class);
	}

	@Override
	protected void onRowDoubleClicked(Admin item) {
		UI.getCurrent().addWindow(new AdminDataManagementWindow(item,AdminManagementPanel.this));
	}


}
