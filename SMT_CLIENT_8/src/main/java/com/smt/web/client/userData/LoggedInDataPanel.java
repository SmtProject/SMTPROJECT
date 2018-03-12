package com.smt.web.client.userData;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Teacher;
import com.smt.web.client.Change.year.ChangeYearWindows;
import com.smt.web.client.loginPanel.MainUi;
import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
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
		HorizontalLayout yearpanel=getYearPanel();
		if(yearpanel!=null)
			layout.addComponent(yearpanel);
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
	public void refreshYear() {
		yearLbl.setValue("- Year: "+((MainUi)UI.getCurrent()).getYear().getName()+" .");

	}
	private HorizontalLayout getYearPanel() {
		if(((MainUi)UI.getCurrent()).getYear()!=null) {
			HorizontalLayout labelLayout = new HorizontalLayout();
			yearLbl=new Label("- Year: "+((MainUi)UI.getCurrent()).getYear().getName()+" .");
			yearLbl.setStyleName("blueUnderlinedLabel");
			labelLayout.addComponent(yearLbl);
			if(((MainUi)UI.getCurrent()).hasAccess(ActionEnum.CHANGE_YEAR)) {
				labelLayout.addLayoutClickListener( e ->{UI.getCurrent().addWindow(new ChangeYearWindows(LoggedInDataPanel.this));	});
				 Page.getCurrent().getStyles().add("div.v-label.v-widget.blueUnderlinedLabel.v-label-blueUnderlinedLabel.v-label-undef-w{color: blue; text-decoration: underline;}");	
			}
			return labelLayout;
		}
		return null;
	}

}
