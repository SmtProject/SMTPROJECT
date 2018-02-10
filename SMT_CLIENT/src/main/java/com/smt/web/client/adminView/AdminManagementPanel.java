package com.smt.web.client.adminView;

import java.util.List;

import javax.xml.bind.ValidationException;
import com.smt.data.entity.Admin;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class AdminManagementPanel extends VerticalLayout {
	private static final long serialVersionUID = 8856843837941346930L;
	private List<Admin> adminUsers;
	private FilteredGrid userGrid ;
	private BeanItemContainer<Admin>container;
	private Button addUserBtn;
	
	public AdminManagementPanel() {
		initComponents();
		intiLayout();
		initData();
	}
	
	private void initComponents() {
		userGrid=new FilteredGrid();
		addUserBtn=new Button("Add New");
		addUserBtn.setIcon(FontAwesome.USER_PLUS);
		initListeners();
	}

	private void intiLayout() {
		HorizontalLayout btnsLayout=new HorizontalLayout(addUserBtn);
		btnsLayout.addComponent(BtnFactory.ExportGridBtn(userGrid));
		btnsLayout.setSpacing(true);
		addComponents(userGrid,btnsLayout);
		userGrid.setSizeFull();
		setMargin(true);
		setSizeFull();
	}
	private void initData() {
		adminUsers=SmtServiceProvider.getInstance().getSmtUserService().findAllAdmins();
		container=new BeanItemContainer<>(Admin.class, adminUsers);
		userGrid.setBeanContainerDataSource(container);
		modifyGrid();

	}

	protected void onAddUserBtnClicked() {
		Admin user=new Admin(0, "", "", "", "", "", "", "", 0, "");
		container.addItemAt(0,user);
		userGrid.select(user);
		userGrid.editItem(user);
	}

	private void onBtnSaveClicked(Admin smtUser) throws  FieldGroup.CommitException{
		if(smtUser!=null) {
		try {
			SmtServiceProvider.getInstance().getSmtUserService().saveAdmin(smtUser);
		} catch (ValidationException e) {
			throw new FieldGroup.CommitException( e.getMessage());
		}
		}
	}
	private void validation (Admin smtUser)throws FieldGroup.CommitException{
		if(smtUser!=null) {
			if(smtUser.getFirstName()==null || smtUser.getFirstName().isEmpty()) {
				throw new FieldGroup.CommitException("Emty First Name");
			}
			if(smtUser.getMiddleName()==null || smtUser.getMiddleName().isEmpty()) {
				throw new FieldGroup.CommitException("Emty Middle Name");
			}
			if(smtUser.getLastName()==null || smtUser.getLastName().isEmpty()) {
				throw new FieldGroup.CommitException("Emty Last Name");
			}
			if(smtUser.getPassword()==null || smtUser.getPassword().isEmpty()) {
				throw new FieldGroup.CommitException("Emty password");
			}
			if(smtUser.getPassword()==null || smtUser.getPassword().isEmpty() || smtUser.getPassword().length()<6) {
				throw new FieldGroup.CommitException("Password Should be At least 6 characters");
			}
			if(smtUser.getRole()==null) {
				throw new FieldGroup.CommitException("Emty Admin Role");
			}
			
		}else {
			throw new FieldGroup.CommitException("Emty smtUser");
		}
		
	}
	private void modifyGrid() {	
		userGrid.setColumnOrder("firstName","middleName","lastName","userName","email","password","address","phone");
		userGrid.getColumn("id").setHidden(true);
		userGrid.setEditorEnabled(true);
		userGrid.getEditorFieldGroup().addCommitHandler(new FieldGroup.CommitHandler() {
			private static final long serialVersionUID = 1L;
			@Override
			public void preCommit(FieldGroup.CommitEvent commitEvent) throws     FieldGroup.CommitException {
	
			}
			@Override
			public void postCommit(FieldGroup.CommitEvent commitEvent) throws     FieldGroup.CommitException {
				try{
				validation(container.getItem(userGrid.getEditedItemId()).getBean());
				onBtnSaveClicked(container.getItem(userGrid.getEditedItemId()).getBean());
				Notification.show("Done");
				}catch (Exception e) {
					Notification.show(e.getMessage(),Notification.Type.ERROR_MESSAGE);		
					throw new FieldGroup.CommitException(e.getMessage());
				}
			}

		});
	}
	private void initListeners() {
		addUserBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;
			public void buttonClick(ClickEvent event) {
				onAddUserBtnClicked();
			}
		});	
	
	}
}


