package com.smt.web.client.loginPanel;

import java.io.File;
import com.smt.data.entity.SmtUser;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.ProgressWindow;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class SignInUi extends VerticalLayout{

	private static final long serialVersionUID = -6665288220966642392L;
	private TextField userNameTxt;
	private PasswordField passwordTxt;
	private Button loginButton;
	private Image image ;
	
	protected SignInUi() {
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
//		userNameTxt.setRequired(true);
//		userNameTxt.setInputPrompt("Your username");

		passwordTxt = new PasswordField("Password:");
		passwordTxt.setWidth("300px");
		passwordTxt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		passwordTxt.setIcon(FontAwesome.LOCK);
//		passwordTxt.setRequired(true);
		loginButton= new Button("Login");
		loginButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		loginButton.setClickShortcut(KeyCode.ENTER);
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
	
		addComponent(image);
		setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		addComponent(fields);
		setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		setSizeFull();
	}
	public void onLoginButtonClick() {
		if(!userNameTxt.isEmpty() && !passwordTxt.isEmpty()) {
			ProgressWindow progressbar= new ProgressWindow();
			progressbar.show();
			SmtUser smtUser = SmtServiceProvider.getInstance().getSmtUserService().login(userNameTxt.getValue(), passwordTxt.getValue());
			progressbar.close();
			if(smtUser == null)
				Notification.show("Validation","incorrect UserName or Password",Notification.Type.ERROR_MESSAGE);
			else {
				((MainUi)UI.getCurrent()).setActions(smtUser.getSmtRole());
				((MainUi)UI.getCurrent()).signIn(smtUser);
			}
		}else {
			Notification.show("Validation","UserName and Password should be field",Notification.Type.ERROR_MESSAGE);
		}

	}

}