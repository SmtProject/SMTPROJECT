package com.smt.web.client.userData;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Teacher;
import com.smt.web.client.loginPanel.MainUi;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
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
		this.setContent(layout);
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

}
