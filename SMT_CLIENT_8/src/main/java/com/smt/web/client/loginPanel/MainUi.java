package com.smt.web.client.loginPanel;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.WebServlet;

import com.smt.data.entity.Action;
import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Year;
import com.smt.data.entity.Admin.AdminRole;
import com.smt.web.client.Main.HomeMainView;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.ProgressWindow;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import smt.model.tools.ActionEnum;
import smt.model.tools.Role;
@Title("LoginPage")
@Theme("valo")
public class MainUi extends UI{
	private static final long serialVersionUID = -4125237708871024800L;
	
	private SmtUser smtUser;
	private List<Action> actions;
	private Year year;

	@Override
	protected void init(VaadinRequest request) {
//		setStyleName(Reindeer.LAYOUT_BLUE);
		setContent(new SignInUi());
	}

	
	public void changeView(Component component) {
		if(component!=null)
		this.setContent(component);
	}
	public void signIn(SmtUser smtUser) {
		if(smtUser!=null) {
			setSmtUser(smtUser);
			ProgressWindow progressbar= new ProgressWindow();
			progressbar.show();
			changeView(new HomeMainView());
			progressbar.close();
		}
	}
	public void signOut() {
		setSmtUser(null);
		changeView(new SignInUi());
	}
	
	public SmtUser getSmtUser() {
		return smtUser;
	}
	public void setSmtUser(SmtUser smtUser) {
		this.smtUser = smtUser;
	}
	
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions =actions;
	}
	
	public Year getYear() {
		return year;
	}


	public void setYear(Year year) {
		this.year = year;
	}


	public boolean hasAccess(ActionEnum actionName)
	{
		List<ActionEnum> actionNames = actions.stream().map(e->e.getName()).collect(Collectors.toList());
		return actionNames.contains(actionName);
	}

@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MainUi.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;

	}
public boolean isUserSuperAdmin() {
	if(!(smtUser instanceof Admin))
		return false;
	Admin admin =(Admin)smtUser;
	if(AdminRole.SuperAdmin.equals(admin.getRole()))
		return true;
	return false;

}
}
