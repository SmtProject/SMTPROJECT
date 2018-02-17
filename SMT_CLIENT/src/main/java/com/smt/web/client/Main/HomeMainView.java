package com.smt.web.client.Main;


import com.smt.web.client.adminView.AdminManagementPanel;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.teacherView.TeacherManagementPanel;
import com.smt.web.client.userData.LoggedInDataPanel;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class HomeMainView extends VerticalLayout{
	private static final long serialVersionUID = 7917194117050335193L;
	
	private LoggedInDataPanel loggedInDataPanel;

	private MenuBar mainMenuBar;
	private Layout content;
	private MenuItem smtUseManagement;

	
	public HomeMainView() {
		initComonent();
		initLayout();
	}
	private void initComonent() {
		mainMenuBar = new MenuBar();
		content=new VerticalLayout();
		loggedInDataPanel=new LoggedInDataPanel();
		
		smtAdminsMenu();
		smtTeachersMenu();
		addSinoutMenu();
	}
	
	private void initLayout() {
		this.setMargin(true);
		this.setSpacing(true);
		this.addComponent(loggedInDataPanel);
		this.addComponent(mainMenuBar);
	//	this.setSizeFull();
	
	}
	
	private void addSinoutMenu() {
		MenuItem siginOut=mainMenuBar.addItem("Sign Out", FontAwesome.SIGN_OUT,null);
		siginOut.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				((MainUi)UI.getCurrent()).signOut();
			}
		});
	}
	private void smtAdminsMenu() {
		if(smtUseManagement==null)
			smtUseManagement=mainMenuBar.addItem("SMT Users",  FontAwesome.USER_PLUS,null);
		MenuItem adminUsers=smtUseManagement.addItem("Admins", FontAwesome.USERS,null);
		adminUsers.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				setContant(new AdminManagementPanel());
			}
		});

	}
	private void smtTeachersMenu() {
		if(smtUseManagement==null)
			smtUseManagement=mainMenuBar.addItem("SMT Users",  FontAwesome.USER_PLUS,null);
		MenuItem teachersUsers=smtUseManagement.addItem("Teachers", FontAwesome.USERS,null);
		teachersUsers.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				setContant(new TeacherManagementPanel());
			}
		});

	}
	private void setContant(Layout layout) {
		if(layout!=null) {
			layout.setSizeFull();
			removeComponent(content);
			addComponent(layout);
			setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
			this.content=layout;
		}
	} 
	


}
