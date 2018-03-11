package com.smt.web.client.toolBox;

public class TableColumnFactory {
	
	public enum TableName{AdminManagement,TeacherManagement,StudentsManagement,YearManagement;
		@Override
		public String toString() {
			switch (this) {
			case AdminManagement:
				return "Admins Management";
				
			case TeacherManagement:
				return "Teachers Management";
				
			case StudentsManagement:
				return "Students Management";
		
			case YearManagement:
					return "Year Management";

			default:
				break;
			}
			return null;
		}
	}
	public enum ColumnsType{TableColumns,ExportColumns,TemplateColumns,NonEditableColumns} 

	public static String [] getTableColumn(TableName tableName,ColumnsType columnsType)
	{
		switch (tableName) {
		case AdminManagement:
			return getAdminManagementColumn(columnsType);
		case TeacherManagement:
			return getTeacherManagementColumn(columnsType);
		case StudentsManagement:
			return getStudentManagementColumn(columnsType);
		case YearManagement:
			return getYearManagementColumn(columnsType);
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

	private static String[] getStudentManagementColumn(ColumnsType columnsType) {
		switch (columnsType) {
		case TableColumns:
		case ExportColumns:
			return new String[]{"firstName","middleName","lastName","userName","password","email","address","phone","status","dateOfBirth","description","createdBy","createdDate","updatedBy","updatedDate"};
		case TemplateColumns:
			return new String[]{"firstName","middleName","lastName","userName","password","email","address","phone","dateOfBirth","description"};
		case NonEditableColumns:
			return new String[]{	};
		default:
			return null;
		}
	}
	private static String[] getYearManagementColumn(ColumnsType columnsType) {
		switch (columnsType) {
		case TableColumns:
		case ExportColumns:
			return new String[]{"name","startDate","endDate","description","yearStatus"};
		case TemplateColumns:
			return new String[]{};
		case NonEditableColumns:
			return new String[]{	};
		default:
			return null;
		}
	}
}
