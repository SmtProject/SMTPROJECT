package com.smt.web.client.importExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smt.data.entity.Admin;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.TableColumnFactory;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.smt.web.excelImportTable.SmtExcelContext;
import com.vaadin.ui.Notification;

import smt.model.tools.Role;

public class AdminImportState implements ImportState {

	public void doAction(SmtExcelContext context) {
		context.setState(this);
		List<List<Object>> excelData = context.getExcelData();
		List<String> excelHeader = context.getExcelHeader();
		
		if(!matchHeader(excelHeader,getImportTemplateColumns()))
		{
			Notification.show("import column not matched with smt model",Notification.TYPE_ERROR_MESSAGE);
			return;
		}
		List<Admin> adminList = new ArrayList<>();

		for (List<Object> list : excelData) {

			String firstName = String.valueOf(list.get(0));
			String middleName = String.valueOf(list.get(1));
			String lastName = String.valueOf(list.get(2));
			String userName = String.valueOf(list.get(3));
			String password = String.valueOf(list.get(4));
			String email = String.valueOf(list.get(5));
			String address = String.valueOf(list.get(6));
			String phone = String.valueOf(list.get(7).toString());
			adminList.add(new Admin(firstName, middleName, lastName, userName, password, email, address, phone, Role.Admin.name()));
		}
		try {
			SmtServiceProvider.getInstance().getSmtImportService().importAdmin(adminList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean matchHeader(List<String> excelHeader, String[] importTemplateColumns) {
		return excelHeader.equals(Arrays.asList(importTemplateColumns));
	}

	@Override
	public String[] getImportTemplateColumns() {
		return TableColumnFactory.getTableColumn(TableName.AdminManagement,ColumnsType.TemplateColumns);
	}

}
