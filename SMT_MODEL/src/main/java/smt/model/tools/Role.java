package smt.model.tools;

public enum Role {
	Admin("Admin"),Student("Student"),Teacher("Teacher");
	
	private String name;

	Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
}
