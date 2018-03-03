package com.smt.web.client.adminView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.smt.data.entity.Admin.AdminRole;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.smt.web.client.toolBox.ComboBoxFactory;
import com.smt.web.client.toolBox.GeneralItemController;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class AdminDataManagementWindow extends Window {
	private static final long serialVersionUID = -3330511576427585604L;

	private Admin admin;

	private TextField firstNameTxt;
	private TextField middleNameTxt;
	private TextField lastNameTxt;
	private TextField userNameTxt;
	private TextField emailTxt;
	private TextField passwordTxt;
	private TextField addressTxt;
	private TextField phoneTxt;
	private ComboBox<SmtUserStatus>  statusCbx;
	private ComboBox<AdminRole>  adminRoleCbx;

	private Button saveBtn;
	private GeneralItemController<Admin>controller;


	public AdminDataManagementWindow(Admin admin ,GeneralItemController<Admin>controller) {
		super("Admin Management");
		this.admin=admin;
		this.controller=controller;
		initCommponents();
		initLayout();
		initData();
		modifyWindow();
	}

	private void initCommponents() {
		firstNameTxt=new TextField("First Name");
		middleNameTxt=new TextField("Middle Name");
		lastNameTxt=new TextField("Last Name");
		userNameTxt=new TextField("User Name");
		emailTxt=new TextField("Email");
		passwordTxt=new TextField("Password");
		addressTxt=new TextField("Address");
		phoneTxt=new TextField("Phone Number");
		statusCbx=ComboBoxFactory.getStatusCombobox(); 
		adminRoleCbx=new ComboBox<Admin.AdminRole>( "AdminRole",new ArrayList<>(Arrays.asList(AdminRole.values())));
		saveBtn=BtnFactory.createSaveBtn();
		initListeners();
	}
	private void initLayout() {
		FormLayout mainLayout = new FormLayout();
		setContent(mainLayout);
		mainLayout.addStyleName("mypanelcontent");
		mainLayout.addComponent(firstNameTxt);
		mainLayout.addComponent(middleNameTxt);
		mainLayout.addComponent(lastNameTxt);
		mainLayout.addComponent(userNameTxt);
		mainLayout.addComponent(emailTxt);
		mainLayout.addComponent(passwordTxt);
		mainLayout.addComponent(addressTxt);
		mainLayout.addComponent(phoneTxt);
		mainLayout.addComponent(statusCbx);
		mainLayout.addComponent(adminRoleCbx);
		mainLayout.addComponent(saveBtn);
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
	}
	protected void onSaveBtnClicked() {
		try {
			commit(); 
			validation();
			Admin savedAdmin=	SmtServiceProvider.getInstance().getSmtUserService().saveAdmin(admin);
			if(controller!=null) {
				if(admin.getId()==null) {
					controller.onItemAddedTriggered(savedAdmin);
				}else {
					controller.onItemUpdatedriggered(savedAdmin);
				}
				this.close();
			}
			admin=savedAdmin;

		} catch (ValidationException e) {
			if(admin.getId()==null)
				admin=null;
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

	}
	
	public void validation() throws ValidationException{
		if (admin != null) {
			if (admin.getFirstName() == null || admin.getFirstName().isEmpty()) {
				throw new ValidationException("Empty First Name");
			}
			if (admin.getMiddleName() == null || admin.getMiddleName().isEmpty()) {
				throw new ValidationException("Empty Middle Name");
			}
			if (admin.getLastName() == null || admin.getLastName().isEmpty()) {
				throw new ValidationException("Empty Last Name");
			}
			if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
				throw new ValidationException("Empty password");
			}
			if (admin.getStatus() == null) {
				throw new ValidationException("Empty Status");
			}
			if (admin.getRole()== null) {
				throw new ValidationException("Empty Admin Role");
			}
			if (admin.getPassword() == null || admin.getPassword().isEmpty()
					|| admin.getPassword().length() < 6) {
				throw new ValidationException("Password Should be At least 6 characters");
			}
		}else {
			throw new ValidationException("Empty teacher");
		}
		}

	private void initData() {
		if(admin!=null) {
			firstNameTxt.setValue(admin.getFirstName());
			middleNameTxt.setValue(admin.getMiddleName());
			lastNameTxt.setValue(admin.getLastName());
			userNameTxt.setValue(admin.getUserName());
			emailTxt.setValue(admin.getEmail());
			passwordTxt.setValue(admin.getPassword());
			addressTxt.setValue(admin.getAddress());
			phoneTxt.setValue(admin.getPhone());
			statusCbx.setValue(admin.getStatus()); 
			adminRoleCbx.setValue(admin.getRole());
		
		}
	}
	private void commit() {
		if(admin==null) 
			admin=new Admin();
		admin.setFirstName(firstNameTxt.getValue());
		admin.setMiddleName(middleNameTxt.getValue());
		admin.setLastName(lastNameTxt.getValue());
		admin.setUserName(userNameTxt.getValue());
		admin.setPassword(passwordTxt.getValue());
		admin.setAddress(addressTxt.getValue());
		admin.setPhone(phoneTxt.getValue());
		admin.setEmail(emailTxt.getValue());
		admin.setRole(adminRoleCbx.getValue());
		admin.setStatus(statusCbx.getValue());
		admin.setFollowedAttribute(((MainUi) UI.getCurrent()).getSmtUser().getUserName(), new Date());

	}
	private void initListeners() {
		saveBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				onSaveBtnClicked();
			}
		});	
	}

	private void modifyWindow() {
		center();	
		this.setResizable(false);
	}
}
