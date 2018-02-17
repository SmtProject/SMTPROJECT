package com.smt.data.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.Role;
@Entity
@Table(name = "TEACHER")
public class Teacher extends SmtUser{
	
	private static final long serialVersionUID = 6839881742888319448L;
	protected String description;

	public Teacher() {
		super();
	}

	public Teacher(String firstName, String middleName, String lastName, String userName, String password,
			String email, String address, String phone, String role,String description,SmtUserStatus status) {
		super(firstName, middleName, lastName, userName, password, email, address, phone,status);
		this.description=description;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	@javax.persistence.Transient
	public Role getSmtRole() {
		return Role.Teacher;
	}
	
	

	

}
