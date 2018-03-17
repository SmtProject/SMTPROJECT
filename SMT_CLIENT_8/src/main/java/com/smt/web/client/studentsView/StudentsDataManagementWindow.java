package com.smt.web.client.studentsView;

import java.time.ZoneId;
import java.util.Date;

import javax.xml.bind.ValidationException;


import com.smt.data.entity.Student;
import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.smt.web.client.toolBox.ComboBoxFactory;
import com.smt.web.client.toolBox.GeneralItemController;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

public class StudentsDataManagementWindow extends Window {
	private static final long serialVersionUID = -3330511576427585604L;

	private Student student;

	private TextField firstNameTxt;
	private TextField middleNameTxt;
	private TextField lastNameTxt;
	private TextField userNameTxt;
	private TextField emailTxt;
	private TextField passwordTxt;
	private TextField addressTxt;
	private TextField phoneTxt;
	private ComboBox<SmtUserStatus>  statusCbx;
	private DateField dateOfBirth;
	private TextArea  descriptiontxt;

	private Button saveBtn;
	private GeneralItemController<Student>controller;


	public StudentsDataManagementWindow(Student student ,GeneralItemController<Student>controller) {
		super("Student Management");
		this.student=student;
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
		dateOfBirth=new DateField("Date Of Birth");
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
		mainLayout.addComponent(dateOfBirth);
		mainLayout.addComponent(descriptiontxt);
		mainLayout.addComponent(saveBtn);
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
	}
	protected void onSaveBtnClicked() {
		try {
			commit(); 
			validation();
			Student savedStudent=	SmtServiceProvider.getInstance().getSmtUserService().saveStudent(student);
			if(controller!=null) {
				if(student.getId()==null) {
					controller.onItemAddedTriggered(savedStudent);
				}else {
					controller.onItemUpdatedriggered(savedStudent);
				}
				this.close();
			}
			student=savedStudent;

		} catch (ValidationException e) {
			if(student.getId()==null)
				student=null;
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

	}
	
	public void validation() throws ValidationException{
		if (student != null) {
			if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
				throw new ValidationException("Empty First Name");
			}
			if (student.getMiddleName() == null || student.getMiddleName().isEmpty()) {
				throw new ValidationException("Empty Middle Name");
			}
			if (student.getLastName() == null || student.getLastName().isEmpty()) {
				throw new ValidationException("Empty Last Name");
			}
			if (student.getPassword() == null || student.getPassword().isEmpty()) {
				throw new ValidationException("Empty password");
			}
			if (student.getStatus() == null) {
				throw new ValidationException("Empty Status");
			}
			if (student.getPassword() == null || student.getPassword().isEmpty()
					|| student.getPassword().length() < 6) {
				throw new ValidationException("Password Should be At least 6 characters");
			}
		}else {
			throw new ValidationException("Empty student");
		}
		}

	private void initData() {
		if(student!=null) {
			firstNameTxt.setValue(student.getFirstName());
			middleNameTxt.setValue(student.getMiddleName());
			lastNameTxt.setValue(student.getLastName());
			userNameTxt.setValue(student.getUserName());
			emailTxt.setValue(student.getEmail());
			passwordTxt.setValue(student.getPassword());
			addressTxt.setValue(student.getAddress());
			phoneTxt.setValue(student.getPhone());
			statusCbx.setValue(student.getStatus()); 
			if(student.getDateOfBirth()!=null)
			dateOfBirth.setValue(student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			descriptiontxt.setValue(student.getDescription());
		
		}
	}
	private void commit() {
		if(student==null) 
			student=new Student();
		student.setFirstName(firstNameTxt.getValue());
		student.setMiddleName(middleNameTxt.getValue());
		student.setLastName(lastNameTxt.getValue());
		student.setUserName(userNameTxt.getValue());
		student.setPassword(passwordTxt.getValue());
		student.setAddress(addressTxt.getValue());
		student.setPhone(phoneTxt.getValue());
		student.setEmail(emailTxt.getValue());
		student.setDateOfBirth(Date.from(dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		student.setDescription(descriptiontxt.getValue());
		student.setStatus(statusCbx.getValue());
		student.setFollowedAttribute(((MainUi) UI.getCurrent()).getSmtUser().getUserName(), new Date());

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
