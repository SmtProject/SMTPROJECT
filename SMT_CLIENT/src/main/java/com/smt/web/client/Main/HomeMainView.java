package com.smt.web.client.Main;


import com.smt.web.client.loginPanel.Global;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

public class HomeMainView extends VerticalLayout{
	private static final long serialVersionUID = 7917194117050335193L;

	private MenuBar mainMenuBar;
	
	public HomeMainView() {
		initComonent();
		initLayout();
	}
	private void initComonent() {
		mainMenuBar = new MenuBar();
		addSinoutMenu();
	}
	
	private void initLayout() {
		this.setMargin(true);
		this.setSpacing(true);
		this.addComponent(mainMenuBar);
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
	


}
