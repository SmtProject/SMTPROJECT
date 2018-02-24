package com.smt.web.client.ActionView;

import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import smt.model.tools.Role;

public class ActionManagementPanel extends VerticalLayout {

	private static final long serialVersionUID = 8856843837941346930L;

	private Grid<Role>userGrid;

	public ActionManagementPanel() {
		initComponents();
		intiLayout();
	}

	private void initComponents() {
		userGrid=new Grid<Role>();
		userGrid.setItems(Role.values());
		userGrid.addColumn(Role::getName).setCaption("Name");
		userGrid.addColumn(person -> "Permission",
				new ButtonRenderer<Role>(clickEvent -> {
					PermissionsManagementPanel permissionsManagementPanel = new PermissionsManagementPanel(clickEvent.getItem());
					UI.getCurrent().addWindow(permissionsManagementPanel);
					permissionsManagementPanel.center();
				}));

	}

	private void intiLayout() {
		addComponents(userGrid);
		userGrid.setSizeFull();
		setMargin(true);
		setSizeFull();
	}

}
