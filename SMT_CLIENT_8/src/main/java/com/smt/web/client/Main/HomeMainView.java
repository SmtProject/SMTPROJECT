package com.smt.web.client.Main;


import com.smt.web.client.ActionView.ActionManagementPanel;
import com.smt.web.client.Change.year.YearTablePanel;
import com.smt.web.client.adminView.AdminManagementPanel;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.studentsView.StudentsManagementPanel;
import com.smt.web.client.teacherView.TeacherManagementPanel;
import com.smt.web.client.userData.LoggedInDataPanel;
import com.vaadin.icons.VaadinIcons;
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
	private MenuItem settingsManagement;
	

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
		smtStudentsMenu();
		addViewActionsMenu();
		smtYearManagementMenu();
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
		if(mainUi.isUserSuperAdmin()) {
			MenuItem	viewActions = mainMenuBar.addItem("view actions", VaadinIcons.WRENCH, null);
			viewActions.setCommand(new Command() {
				private static final long serialVersionUID = -6491765760561550525L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					setContant(new ActionManagementPanel());
				}
			});
		}
	}
	
	private void addSignOutMenu() {
		if(mainUi.hasAccess(ActionEnum.SIGN_OUT)){
		MenuItem siginOut=mainMenuBar.addItem(ActionEnum.SIGN_OUT.getName(), VaadinIcons.SIGN_OUT,null);
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
			smtUseManagement=mainMenuBar.addItem("SMT USERS",  VaadinIcons.GROUP,null);
		MenuItem adminUsers=smtUseManagement.addItem(ActionEnum.SHOW_ADMIN_MANAGEMENT_PANEL.getName(), VaadinIcons.SPECIALIST,null);
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
			if(smtUseManagement==null)
				smtUseManagement=mainMenuBar.addItem("SMT USERS",  VaadinIcons.GROUP,null);
			MenuItem teachersUsers = smtUseManagement.addItem(ActionEnum.SHOW_TEACHERS_MANAGEMENT_PANEL.getName(), VaadinIcons.USER,null);
			teachersUsers.setCommand(new Command() {
				private static final long serialVersionUID = -6491765760561550525L;
				@Override
				public void menuSelected(MenuItem selectedItem) {
					setContant(new TeacherManagementPanel());
				}
			});
		}
	}
	private void smtStudentsMenu() {
		if(mainUi.hasAccess(ActionEnum.SHOW_STUDENTS_MANAGEMENT_PANEL)){
			if(smtUseManagement==null)
				smtUseManagement=mainMenuBar.addItem("SMT USERS",  VaadinIcons.GROUP,null);
			MenuItem studentsUsers = smtUseManagement.addItem(ActionEnum.SHOW_STUDENTS_MANAGEMENT_PANEL.getName(), VaadinIcons.MALE,null);
			studentsUsers.setCommand(new Command() {
				private static final long serialVersionUID = -6491765760561550525L;
				@Override
				public void menuSelected(MenuItem selectedItem) {
					setContant(new StudentsManagementPanel());
				}
			});
		}
	}
	private void smtYearManagementMenu() {
		if(mainUi.isUserSuperAdmin()){
			if(settingsManagement==null)
				settingsManagement=mainMenuBar.addItem("Settings USERS",  VaadinIcons.SCREWDRIVER,null);
			MenuItem studentsUsers = settingsManagement.addItem("School Year", VaadinIcons.CALENDAR,null);
			studentsUsers.setCommand(new Command() {
				private static final long serialVersionUID = -6491765760561550525L;
				@Override
				public void menuSelected(MenuItem selectedItem) {
					setContant(new YearTablePanel());
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
