package com.smt.web.client.loginPanel;

import javax.servlet.annotation.WebServlet;

import com.smt.data.entity.SmtUser;
import com.smt.web.client.Main.HomeMainView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Reindeer;
@Title("LoginPage")
@Theme("valo")
public class MainUi extends UI{
	private static final long serialVersionUID = -4125237708871024800L;

	@Override
	protected void init(VaadinRequest request) {
		Global.mainUi=this;
		setStyleName(Reindeer.LAYOUT_BLUE);
		setContent(new SignInUi());
	}

	
	public void changeView(Component component) {
		if(component!=null)
		this.setContent(component);
	}
	public void signIn(SmtUser smtUser) {
		if(smtUser!=null) {
			Global.smtUser=smtUser;
		changeView(new HomeMainView());
		}
	}
	public void signOut() {
		Global.smtUser=null;
		changeView(new SignInUi());
	}

@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUi.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;

	}
}
