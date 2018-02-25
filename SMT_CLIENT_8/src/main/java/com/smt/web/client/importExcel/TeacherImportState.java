package com.smt.web.client.importExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.smt.data.entity.Teacher;
import com.smt.web.client.loginPanel.MainUi;
import com.smt.web.client.service.SmtServiceProvider;
import com.smt.web.client.toolBox.RefreshGridController;
import com.smt.web.client.toolBox.TableColumnFactory;
import com.smt.web.client.toolBox.TableColumnFactory.ColumnsType;
import com.smt.web.client.toolBox.TableColumnFactory.TableName;
import com.smt.web.excelImportTable.SmtExcelContext;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;


public class TeacherImportState implements ImportState {
	
	private RefreshGridController controller;
	
	public TeacherImportState(RefreshGridController controller) {
		this.controller=controller;
		
	}

	public void doAction(SmtExcelContext context) {
		context.setState(this);
		List<List<Object>> excelData = context.getExcelData();
		List<String> excelHeader = context.getExcelHeader();
		
		if(!matchHeader(excelHeader,getImportTemplateColumns()))
		{
			Notification.show("import column not matched with smt model",Notification.TYPE_ERROR_MESSAGE);
			return;
		}
		List<Teacher> teacherList = new ArrayList<>();
		Teacher teacherToImport;
		for (List<Object> list : excelData) {
			teacherToImport=null;
			String firstName = String.valueOf(list.get(0));
			String middleName = String.valueOf(list.get(1));
			String lastName = String.valueOf(list.get(2));
			String userName = String.valueOf(list.get(3));
			String password = String.valueOf(list.get(4));
			String email = String.valueOf(list.get(5));
			String address = String.valueOf(list.get(6));
			String phone = String.valueOf(list.get(7).toString());
			String description = String.valueOf(list.get(8).toString());
		
			
			teacherToImport=new Teacher(firstName, middleName, lastName, userName, password, email, address, phone, description, SmtUserStatus.ACTIVE);
			teacherToImport.setFollowedAttribute(((MainUi) UI.getCurrent()).getSmtUser().getUserName(), new Date());
			teacherList.add(teacherToImport);
		}
		try {
			SmtServiceProvider.getInstance().getSmtUserService().saveTeachers(teacherList);
		} catch (ValidationException e) {
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}
		if(controller!=null)
			controller.refreshGridData();

	}

	private boolean matchHeader(List<String> excelHeader, String[] importTemplateColumns) {
		return excelHeader.equals(Arrays.asList(importTemplateColumns));
	}

	@Override
	public String[] getImportTemplateColumns() {
		return TableColumnFactory.getTableColumn(TableName.TeacherManagement,ColumnsType.TemplateColumns);
	}

	@Override
	public Class<?> getGridClass() {
		return Teacher.class;
	}

}
