package smt.model.tools;

public enum ActionEnum {
	SHOW_ADMIN_MANAGEMENT_PANEL("SMT admins"), SHOW_TEACHERS_MANAGEMENT_PANEL("Teachers"), SHOW_STUDENTS_MANAGEMENT_PANEL("Stdudent Management"),SIGN_OUT("Sign Out");

	private String name;

	ActionEnum(String name) {
		this.name = name;

	}
	public String getName() {
		return name;
	}
}