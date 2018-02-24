package com.smt.web.client.toolBox;

import java.util.ArrayList;
import java.util.Arrays;
import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.vaadin.ui.ComboBox;

public class ComboBoxFactory {
	
	public static ComboBox getStatusCombobox() {
		ComboBox statusCbx=new ComboBox("Status", new ArrayList<>(Arrays.asList(SmtUserStatus.values())));
//		statusCbx.setNullSelectionAllowed(false);
		return statusCbx;
	}
	

}
