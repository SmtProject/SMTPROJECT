package com.smt.web.client.ActionView;

import com.smt.web.client.adminView.FilteredGrid;
import com.vaadin.data.Item;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

import smt.model.tools.Role;

public class ActionManagementPanel extends VerticalLayout {

	private static final long serialVersionUID = 8856843837941346930L;

	private FilteredGrid userGrid;

	public ActionManagementPanel() {
		initComponents();
		intiLayout();
	}

	private void initComponents() {
		userGrid = new FilteredGrid();
		userGrid.addColumn("Role name", String.class);
		userGrid.addColumn("permissions", String.class);
		for (Role role : Role.values())
			userGrid.addRow(role.toString(), "View Permissions");

		userGrid.getColumn("permissions").setRenderer(new ButtonRenderer(event -> {
			Item item = userGrid.getContainerDataSource().getItem(event.getItemId());
			Object value = item.getItemProperty("Role name").getValue();
			PermissionsManagementPanel permissionsManagementPanel = new PermissionsManagementPanel(Role.valueOf(value.toString()));
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
