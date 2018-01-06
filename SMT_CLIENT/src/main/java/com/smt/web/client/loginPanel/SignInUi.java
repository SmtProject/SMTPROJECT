package com.smt.web.client.loginPanel;

import java.io.File;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
@Title("LoginPage")
@Theme("valo")
public class SignInUi extends UI{

	private static final long serialVersionUID = -6665288220966642392L;
	private TextField userNameTxt;
	private PasswordField passwordTxt;
	private Button loginButton;
	private Image image ;

	@Override
	protected void init(VaadinRequest request) {
		initComponents();
		initLayout();
	}

	private void initComponents() {
		FileResource resource = new FileResource(new File(getClass().getClassLoader().getResource("image/smtLogo.png").getFile()));
		image = new Image("",resource);

		userNameTxt = new TextField("User:");
		userNameTxt.setWidth("300px");
		userNameTxt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		userNameTxt.setIcon(FontAwesome.USER);
		userNameTxt.setRequired(true);
		userNameTxt.setInputPrompt("Your username");

		passwordTxt = new PasswordField("Password:");
		passwordTxt.setWidth("300px");
		passwordTxt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		passwordTxt.setIcon(FontAwesome.LOCK);
		passwordTxt.setRequired(true);
		loginButton= new Button("Login");
		initListeners();
	}
	private void initListeners() {
		loginButton.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;
			public void buttonClick(ClickEvent event) {
				onLoginButtonClick();
			}
		});
	}

	private void initLayout() {
		setSizeFull();
		VerticalLayout fields = new VerticalLayout(userNameTxt, passwordTxt, loginButton);
		fields.setSpacing(true);
		fields.setMargin(new MarginInfo(true, true, true, false));
		fields.setSizeUndefined();
		VerticalLayout uiLayout = new VerticalLayout();
		uiLayout.addComponent(image);
		uiLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		uiLayout.addComponent(fields);
		uiLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		uiLayout.setSizeFull();
		setStyleName(Reindeer.LAYOUT_BLUE);
		setFocusedComponent(userNameTxt);
		setContent(uiLayout);
	}
	public void onLoginButtonClick() {
		if(!userNameTxt.isEmpty() && !passwordTxt.isEmpty()) {

		}else {
			Notification.show("Validation","UserName and Password should be field",Notification.Type.ERROR_MESSAGE);
		}

	}
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = SignInUi.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;

	}
}