package com.smt.web.client.userData;

import com.smt.data.entity.SmtUser;
import com.smt.web.client.loginPanel.MainUi;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

public class LoggedInDataPanel extends Panel{
	private static final long serialVersionUID = -7253304854580709082L;
	
	private SmtUser user;
	
	public LoggedInDataPanel() {
		user=((MainUi)UI.getCurrent()).getSmtUser();
		this.setSizeFull();
		initPanelCaption();
		initLayout();
	}

	private void initLayout() {
		CssLayout layout = new CssLayout();
		Label nameLbl=new Label("- Name: "+user.getFormatedName()+" .");
		Label userNameLbl=new Label("- User Name: "+user.getUserName()+" .");
		nameLbl.setSizeUndefined();
		userNameLbl.setSizeUndefined();
		layout.addComponent(nameLbl);
		layout.addComponent(userNameLbl);
		this.setContent(layout);
	}

	private void initPanelCaption() {
		this.setCaption("Logged in as  "+user.getSmtRole().toString());	
	}
	
}
