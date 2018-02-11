package com.smt.web.client.toolBox;

import smt.model.tools.Role;

public class TableColumnFactory {

	public static String [] getTableColumn(Role role)
	{
		switch (role) {
		case Admin:
			return getAdminColumn();
		case Student:
			return getStudentColumn();
		default:
			return null;
		}
	}

	private static String[] getStudentColumn() {
		return new String[]{};
	}

	private static String [] getAdminColumn() {
		return new String[]{"firstName","middleName","lastName","userName","email","password","address","phone"};
	}
}
