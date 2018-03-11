package com.smt.web.client.userData;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Teacher;
import com.smt.web.client.Change.year.ChangeYearWindows;
import com.smt.web.client.loginPanel.MainUi;
import com.vaadin.event.ContextClickEvent;
import com.vaadin.event.ContextClickEvent.ContextClickListener;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import smt.model.tools.ActionEnum;

public class LoggedInDataPanel extends Panel{
	private static final long serialVersionUID = -7253304854580709082L;

	private SmtUser user;
	private Label yearLbl;

	public LoggedInDataPanel() {
		user=((MainUi)UI.getCurrent()).getSmtUser();
		this.setSizeFull();
		initPanelCaption();
		initLayout();
		setBackGroundColor(user);
	}

	private void initLayout() {
		CssLayout layout = new CssLayout();
		Label nameLbl=new Label("- Name: "+user.getFormatedName()+" .");
		Label userNameLbl=new Label("- User Name: "+user.getUserName()+" .");
		nameLbl.setSizeUndefined();
		userNameLbl.setSizeUndefined();
		layout.addComponent(nameLbl);
		layout.addComponent(userNameLbl);
		if(((MainUi)UI.getCurrent()).getYear()!=null) {
			yearLbl=new Label("- Year: "+((MainUi)UI.getCurrent()).getYear().getName()+" .");
			layout.addComponent(yearLbl);
			initYearListener();
		}
		this.setContent(layout);
	}

	private void initYearListener() {
		if(((MainUi)UI.getCurrent()).hasAccess(ActionEnum.CHANGE_YEAR)) {
			yearLbl.addContextClickListener(new ContextClickListener() {
				private static final long serialVersionUID = 3851015117219825729L;

				@Override
				public void contextClick(ContextClickEvent event) {
					UI.getCurrent().addWindow(new ChangeYearWindows(LoggedInDataPanel.this));					
				}
			});
		}
		
	}

	private void initPanelCaption() {
		this.setCaption("Logged in as  "+user.getSmtRole().toString());	
	}

	private void setBackGroundColor(SmtUser user) {
		String colorName="grey";
		if(user instanceof Admin)
			colorName="#ffcccc";
		if(user instanceof Teacher)
			colorName="#e6f7ff";//teacher ==>#e6ffe6
		this.setStyleName("backColorCustom");		
		Styles styles = Page.getCurrent().getStyles();
		styles.add("div.v-panel.v-widget.backColorCustom.v-panel-backColorCustom.v-has-width{background-color: "+colorName+";}");		
	}
	public void refreshYear() {
		yearLbl.setCaption("- Year: "+((MainUi)UI.getCurrent()).getYear().getName()+" .");

	}

}
