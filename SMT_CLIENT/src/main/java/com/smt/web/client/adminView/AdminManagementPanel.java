package com.smt.web.client.adminView;

import java.util.Collection;
import java.util.Date;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Admin;
import com.smt.web.client.importExcel.AdminImportState;
import com.smt.web.client.importExcel.ImportState;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.UI;

public class AdminManagementPanel extends BaseManagementPanel<Admin> {

	public AdminManagementPanel() {
		super(TableName.AdminManagement);
	}

	private static final long serialVersionUID = -568509141630804558L;

	@Override
	public void onAddUserBtnClicked() {
		Admin user = new Admin("", "", "", "", "", "", "", "", "");
		container.addItemAt(0, user);
		userGrid.select(user);
		userGrid.editItem(user);
	}

	@Override
	public void onBtnSaveClicked(Admin smtUser) throws FieldGroup.CommitException {
		if (smtUser != null) {
			smtUser.setFollowedAttribute(((MainUi) UI.getCurrent()).getSmtUser().getUserName(), new Date());
			try {
				Admin savedUser = SmtServiceProvider.getInstance().getSmtUserService().saveAdmin(smtUser);
				if (smtUser.getId() == null)
					container.getItem(smtUser).setBean(savedUser);
			} catch (ValidationException e) {
				throw new FieldGroup.CommitException(e.getMessage());
			}
		}
	}

	@Override
	public void validation(Admin smtUser) throws FieldGroup.CommitException {
		if (smtUser != null) {
			if (smtUser.getFirstName() == null || smtUser.getFirstName().isEmpty()) {
				throw new FieldGroup.CommitException("Emty First Name");
			}
			if (smtUser.getMiddleName() == null || smtUser.getMiddleName().isEmpty()) {
				throw new FieldGroup.CommitException("Emty Middle Name");
			}
			if (smtUser.getLastName() == null || smtUser.getLastName().isEmpty()) {
				throw new FieldGroup.CommitException("Emty Last Name");
			}
			if (smtUser.getPassword() == null || smtUser.getPassword().isEmpty()) {
				throw new FieldGroup.CommitException("Emty password");
			}
			if (smtUser.getStatus() == null) {
				throw new FieldGroup.CommitException("Emty Status");
			}
			if (smtUser.getPassword() == null || smtUser.getPassword().isEmpty()
					|| smtUser.getPassword().length() < 6) {
				throw new FieldGroup.CommitException("Password Should be At least 6 characters");
			}

			if (smtUser.getRole() == null) {
				throw new FieldGroup.CommitException("Emty Admin Role");
			}

		} else {
			throw new FieldGroup.CommitException("Emty smtUser");
		}

	}

	@Override
	public ImportState getImportState() {
		return new AdminImportState();
	}

	@Override
	public Collection<Admin> getData() {
		return SmtServiceProvider.getInstance().getSmtUserService().findAllAdmins();
	}


}
