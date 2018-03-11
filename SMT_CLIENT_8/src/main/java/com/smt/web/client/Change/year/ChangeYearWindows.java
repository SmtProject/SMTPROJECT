package com.smt.web.client.Change.year;

import com.smt.data.entity.Year;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.ProgressWindow;
import com.smt.web.client.userData.LoggedInDataPanel;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ChangeYearWindows extends Window{

	private static final long serialVersionUID = 5701143968978609192L;

	private ComboBox<Year>yearsCbx;
	private Button applyBtn;
	private LoggedInDataPanel loggedInDataPanel;


	public ChangeYearWindows(LoggedInDataPanel loggedInDataPanel) {
		this.loggedInDataPanel=loggedInDataPanel;
		initComponents();
		initLayout();
		setResizable(false);
	}

	private void initComponents() {
		yearsCbx=new ComboBox<Year>("Year");
		ProgressWindow progress=new ProgressWindow();
		progress.show();
		yearsCbx.setItems(SmtServiceProvider.getInstance().getSmtYearService().getYears());
		progress.close();
		applyBtn=new Button("Apply");
		initListeners();
	}

	private void initListeners() {
		applyBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				if(loggedInDataPanel!=null && yearsCbx.getValue()!=null) {
					((MainUi)UI.getCurrent()).setYear(yearsCbx.getValue());
					loggedInDataPanel.refreshYear();
				}
			}
		});		
	}

	private void initLayout() {
		FormLayout mainLayout = new FormLayout();
		setContent(mainLayout);
		mainLayout.addComponent(yearsCbx);
		mainLayout.addComponent(applyBtn);
	}
}
