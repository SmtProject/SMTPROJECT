package com.smt.data.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.Role;
@Entity
@Table(name = "STUDENT")
public class Student extends SmtUser{
	
	private static final long serialVersionUID = 6839881742888319448L;
	protected String description;
	protected Date dateOfBirth;
	

	public Student() {
		super();
	}

	public Student(String firstName, String middleName, String lastName, String userName, String password,
			String email, String address, String phone,String description,SmtUserStatus status) {
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
	
	@Column(name = "DOB")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	@javax.persistence.Transient
	public Role getSmtRole() {
		return Role.Student;
	}
	

	
	

	

}
