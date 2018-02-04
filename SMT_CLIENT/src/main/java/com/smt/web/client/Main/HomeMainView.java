package com.smt.web.client.Main;


import com.smt.web.client.adminView.AdminManagementPanel;
import com.smt.web.client.loginPanel.Global;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class HomeMainView extends VerticalLayout{
	private static final long serialVersionUID = 7917194117050335193L;

	private MenuBar mainMenuBar;
	private Layout content;
	
	public HomeMainView() {
		initComonent();
		initLayout();
	}
	private void initComonent() {
		mainMenuBar = new MenuBar();
		content=new VerticalLayout();
		smtUserMenu() ;
		addSinoutMenu();
	}
	
	private void initLayout() {
		this.setMargin(true);
		this.setSpacing(true);
		this.addComponent(mainMenuBar);
	//	this.setSizeFull();
	
	}
	
	private void addSinoutMenu() {
		MenuItem siginOut=mainMenuBar.addItem("Sign Out", FontAwesome.SIGN_OUT,null);
		siginOut.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				Global.mainUi.signOut();
			}
		});
	}
	private void smtUserMenu() {
		MenuItem smtUseManagement=mainMenuBar.addItem("Smt Users", FontAwesome.USER_SECRET,null);
		smtUseManagement.setCommand(new Command() {
			private static final long serialVersionUID = -6491765760561550525L;
			@Override
			public void menuSelected(MenuItem selectedItem) {
				setContant(new AdminManagementPanel());
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
