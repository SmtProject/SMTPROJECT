package com.smt.web.client.Main;


import com.smt.web.client.ActionView.ActionManagementPanel;
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
import smt.model.tools.ActionEnum;

public class HomeMainView extends VerticalLayout{
	private static final long serialVersionUID = 7917194117050335193L;
	
	private LoggedInDataPanel loggedInDataPanel;

	private MenuBar mainMenuBar;
	private Layout content;
	private MenuItem smtUseManagement;
	private MenuItem teachersUsers;
	private MenuItem viewActions;

	private MainUi mainUi = (MainUi)UI.getCurrent();

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
		addViewActionsMenu();
		addSignOutMenu();
	}
	
	private void initLayout() {
		this.setMargin(true);
		this.setSpacing(true);
		this.addComponent(loggedInDataPanel);
		this.addComponent(mainMenuBar);
	//	this.setSizeFull();
	
	}
	
	private void addViewActionsMenu() {
		viewActions = mainMenuBar.addItem("view actions", FontAwesome.SIGN_OUT, null);
		viewActions.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				setContant(new ActionManagementPanel());
			}
		});

	}
	
	private void addSignOutMenu() {
		if(mainUi.hasAccess(ActionEnum.SIGN_OUT)){
		MenuItem siginOut=mainMenuBar.addItem(ActionEnum.SIGN_OUT.getName(), FontAwesome.SIGN_OUT,null);
		siginOut.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				mainUi.signOut();
			}
		});
		}
	}
	private void smtAdminsMenu() {
		if(mainUi.hasAccess(ActionEnum.SHOW_ADMIN_MANAGEMENT_PANEL)){
		if(smtUseManagement==null)
			smtUseManagement=mainMenuBar.addItem(ActionEnum.SHOW_ADMIN_MANAGEMENT_PANEL.getName(),  FontAwesome.USER_PLUS,null);
		MenuItem adminUsers=smtUseManagement.addItem("Admins", FontAwesome.USERS,null);
		adminUsers.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				setContant(new AdminManagementPanel());
			}
		});
	 }
}
	private void smtTeachersMenu() {
		if(mainUi.hasAccess(ActionEnum.SHOW_TEACHERS_MANAGEMENT_PANEL)){
		if(teachersUsers==null)
			teachersUsers = smtUseManagement.addItem(ActionEnum.SHOW_TEACHERS_MANAGEMENT_PANEL.getName(), FontAwesome.USERS,null);
		
		teachersUsers.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				setContant(new TeacherManagementPanel());
			}
		});
		}
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
