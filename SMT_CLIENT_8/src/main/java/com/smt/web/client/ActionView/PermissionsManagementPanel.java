package com.smt.web.client.ActionView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.smt.data.entity.Action;
import com.smt.web.client.service.SmtServiceProvider;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Window;

import smt.model.tools.ActionEnum;
import smt.model.tools.Role;

public class PermissionsManagementPanel extends Window {

	private static final long serialVersionUID = 1L;
	private Role role;
	private OptionGroup optionGroup;
	private Button savePermissionBtn;
	private Map<ActionEnum, Boolean> actionsToSaveMap = new HashMap<>();

	public PermissionsManagementPanel(Role role) {
		this.role = role;
		setWidth("600");
		setHeight("300");
		initComponent();
		initListener();
		initLayout();
	}

	private void initComponent() {
		optionGroup = new OptionGroup("", Arrays.asList(ActionEnum.values()));
		optionGroup.setMultiSelect(true);
		savePermissionBtn = new Button("save");
		List<Action> actions = SmtServiceProvider.getInstance().getSmtActionService().findActionByRole(role);
		List<ActionEnum> collect = actions.stream().map(e -> e.getName()).collect(Collectors.toList());
		for (ActionEnum action : collect)
			optionGroup.select(action);
	}

	private void initLayout() {
		FormLayout mainLayout = new FormLayout();
		mainLayout.addComponent(optionGroup);
		mainLayout.addComponent(savePermissionBtn);
		setContent(mainLayout);
		mainLayout.addStyleName("mypanelcontent");
		mainLayout.setSizeUndefined();
		mainLayout.setMargin(true);
	}

	private void initListener() {
		savePermissionBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 7842749587704454705L;

			public void buttonClick(ClickEvent event) {
				SmtServiceProvider.getInstance().getSmtActionService().updateActions(actionsToSaveMap, role);
				Notification.show("Permissions updated Successfully");
				close();
			}
		});
		optionGroup.addValueChangeListener(valueChangeEvent);

	}

	ValueChangeListener valueChangeEvent = new ValueChangeListener() {

		private static final long serialVersionUID = 1L;

		@Override
		public void valueChange(ValueChangeEvent event) {
			Property<?> property = event.getProperty();
			@SuppressWarnings("unchecked")
			Set<String> selectedValues = (Set<String>)property.getValue();
			for(Object val : selectedValues){
				ActionEnum action = ActionEnum.valueOf(val.toString());
				boolean selected = optionGroup.isSelected(val);
				actionsToSaveMap.put(action, selected);
			}
		}
	};

}
