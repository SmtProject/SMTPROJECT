package com.smt.web.client.toolBox;

public class TableColumnFactory {
	
	public enum TableName{AdminManagement,TeacherManagement}
	public enum ColumnsType{TableColumns,ExportColumns,TemplateColumns,NonEditableColumns} 

	public static String [] getTableColumn(TableName tableName,ColumnsType columnsType)
	{
		switch (tableName) {
		case AdminManagement:
			return getAdminManagementColumn(columnsType);
		case TeacherManagement:
			return getTeacherManagementColumn(columnsType);
		default:
			return null;
		}
	}

	private static String [] getAdminManagementColumn(ColumnsType columnsType) {
		switch (columnsType) {
		case TableColumns:
		case ExportColumns:
			return new String[]{"firstName","middleName","lastName","userName","password","email","address","phone","role","status","createdBy","createdDate","updatedBy","updatedDate"};
		case TemplateColumns:
			return new String[]{"firstName","middleName","lastName","userName","password","email","address","phone","role"};
		case NonEditableColumns:
			return new String[]{	"createdBy","createdDate","updatedBy","updatedDate"};
		default:
			return null;
		}
	}
	private static String[] getTeacherManagementColumn(ColumnsType columnsType) {
		switch (columnsType) {
		case TableColumns:
		case ExportColumns:
			return new String[]{"firstName","middleName","lastName","userName","password","email","address","phone","status","description","createdBy","createdDate","updatedBy","updatedDate"};
		case TemplateColumns:
			return new String[]{"firstName","middleName","lastName","userName","password","email","address","phone","description"};
		case NonEditableColumns:
			return new String[]{	};
		default:
			return null;
		}
	}
}
