package com.smt.web.client.teacherView;

import java.util.Date;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.smt.data.entity.Teacher;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.smt.web.client.toolBox.ComboBoxFactory;
import com.smt.web.client.toolBox.GeneralItemController;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class TeacherDataManagementWindow extends Window {
	private static final long serialVersionUID = -3330511576427585604L;

	private Teacher teacher;

	private TextField firstNameTxt;
	private TextField middleNameTxt;
	private TextField lastNameTxt;
	private TextField userNameTxt;
	private TextField emailTxt;
	private TextField passwordTxt;
	private TextField addressTxt;
	private TextField phoneTxt;
	private ComboBox  statusCbx;
	private TextArea  descriptiontxt;

	private Button saveBtn;
	private GeneralItemController<Teacher>controller;


	public TeacherDataManagementWindow(Teacher teacher ,GeneralItemController<Teacher>controller) {
		super("Teacher Management");
		this.teacher=teacher;
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

		descriptiontxt=new TextArea("Description");
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
		mainLayout.addComponent(descriptiontxt);
		mainLayout.addComponent(saveBtn);
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
	}
	protected void onSaveBtnClicked() {
		try {
			commit(); 
			validation();
			Teacher savedTecher=	SmtServiceProvider.getInstance().getSmtUserService().saveTeacher(teacher);
			if(controller!=null) {
				if(teacher.getId()==null) {
					controller.onItemAddedTriggered(savedTecher);
				}else {
					controller.onItemUpdatedriggered(savedTecher);
				}
				this.close();
			}
			teacher=savedTecher;

		} catch (ValidationException e) {
			if(teacher.getId()==null)
				teacher=null;
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

	}
	
	public void validation() throws ValidationException{
		if (teacher != null) {
			if (teacher.getFirstName() == null || teacher.getFirstName().isEmpty()) {
				throw new ValidationException("Empty First Name");
			}
			if (teacher.getMiddleName() == null || teacher.getMiddleName().isEmpty()) {
				throw new ValidationException("Empty Middle Name");
			}
			if (teacher.getLastName() == null || teacher.getLastName().isEmpty()) {
				throw new ValidationException("Empty Last Name");
			}
			if (teacher.getPassword() == null || teacher.getPassword().isEmpty()) {
				throw new ValidationException("Empty password");
			}
			if (teacher.getStatus() == null) {
				throw new ValidationException("Empty Status");
			}
			if (teacher.getPassword() == null || teacher.getPassword().isEmpty()
					|| teacher.getPassword().length() < 6) {
				throw new ValidationException("Password Should be At least 6 characters");
			}
		}else {
			throw new ValidationException("Empty teacher");
		}
		}

	private void initData() {
		if(teacher!=null) {
			firstNameTxt.setValue(teacher.getFirstName());
			middleNameTxt.setValue(teacher.getMiddleName());
			lastNameTxt.setValue(teacher.getLastName());
			userNameTxt.setValue(teacher.getUserName());
			emailTxt.setValue(teacher.getEmail());
			passwordTxt.setValue(teacher.getPassword());
			addressTxt.setValue(teacher.getAddress());
			phoneTxt.setValue(teacher.getPhone());
			statusCbx.setValue(teacher.getStatus()); 
			descriptiontxt.setValue(teacher.getDescription());
		
		}
	}
	private void commit() {
		if(teacher==null) 
			teacher=new Teacher();
		teacher.setFirstName(firstNameTxt.getValue());
		teacher.setMiddleName(middleNameTxt.getValue());
		teacher.setLastName(lastNameTxt.getValue());
		teacher.setUserName(userNameTxt.getValue());
		teacher.setPassword(passwordTxt.getValue());
		teacher.setAddress(addressTxt.getValue());
		teacher.setPhone(phoneTxt.getValue());
		teacher.setEmail(emailTxt.getValue());
		teacher.setDescription(descriptiontxt.getValue());
		teacher.setStatus((SmtUserStatus)statusCbx.getValue());
		teacher.setFollowedAttribute(((MainUi) UI.getCurrent()).getSmtUser().getUserName(), new Date());

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
