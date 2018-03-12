package com.smt.web.client.Change.year;

import java.util.Date;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Year;
import com.smt.data.entity.Year.YearStatus;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.BtnFactory;
import com.smt.web.client.toolBox.GeneralItemController;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import smt.model.tools.Followed.FollowedStatus;

public class YearDataManagementWindows extends Window{
	private static final long serialVersionUID = -1211795957837888243L;

	protected Year year;

	private TextField name;
	private DateTimeField endDate;
	private DateTimeField startDate;
	private TextArea  descriptiontxt;
	private Button saveBtn;

	private GeneralItemController<Year>controller;

	public YearDataManagementWindows(Year year,GeneralItemController<Year>controller) {
		super("Years Management");
		this.controller=controller;
		this.year=year;
		initCommponents();
		initLayout();
		initData();
		modifyWindow();

	}
	private void initCommponents() {
		name=new TextField("Name");
		startDate=new DateTimeField("Start Date");
		endDate=new DateTimeField("End Date");
		descriptiontxt=new TextArea("Description");
		saveBtn=BtnFactory.createSaveBtn();
		initListeners();

	}

	private void initLayout() {

		FormLayout mainLayout = new FormLayout();
		setContent(mainLayout);
		mainLayout.addStyleName("mypanelcontent");
		mainLayout.addComponent(name);
		mainLayout.addComponent(startDate);
		mainLayout.addComponent(endDate);
		mainLayout.addComponent(descriptiontxt);
		mainLayout.addComponent(saveBtn);
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);


	}
	private void initData() {
		if(year!=null) {
			name.setValue(year.getName());
			startDate.setData(year.getStartDate());
			endDate.setData(year.getEndDate());
			descriptiontxt.setValue(year.getDescription());
		}
	}
	protected void onSaveBtnClicked() {
		try {
			commit(); 
			validation();
			Year savedYear=	SmtServiceProvider.getInstance().getSmtYearService().saveYear(year);
			if(controller!=null) {
				if(year.getId()==null) {
					controller.onItemAddedTriggered(savedYear);
				}else {
					controller.onItemUpdatedriggered(savedYear);
				}
				this.close();
			}
			year=savedYear;

		} catch (ValidationException e) {
			if(year.getId()==null)
				year=null;
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

	}
	private void commit() {
		if(year==null) 
			year=new Year();
		year.setName(name.getValue());
				year.setStartDate(new Date());
				year.setEndDate(new Date());
		year.setDescription(descriptiontxt.getValue());
		year.setStatus(FollowedStatus.ACTIVE);
		year.setYearStatus(YearStatus.OPENED);
		year.setFollowedAttribute(((MainUi) UI.getCurrent()).getSmtUser().getUserName(), new Date());

	}
	public void validation() throws ValidationException{
		if (year != null) {
			if (year.getName() == null || year.getName().isEmpty()) {
				throw new ValidationException("Empty Name");
			}
			if (year.getStartDate() == null ) {
				throw new ValidationException("Empty Start Date");
			}
			if (year.getEndDate()== null ) {
				throw new ValidationException("Empty Ends Date");
			}

		}else {
			throw new ValidationException("Empty year");
		}
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
