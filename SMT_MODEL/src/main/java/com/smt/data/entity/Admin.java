package com.smt.data.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.Role;
@Entity
@Table(name = "ADMIN")
public class Admin extends SmtUser{
	
	private static final long serialVersionUID = 6839881742888319448L;
	public enum AdminRole{SuperAdmin,Normal}
	
	protected AdminRole role;
	
	protected Admin() {
		super();
	}

	public Admin(String firstName, String middleName, String lastName, String userName, String password,
			String email, String address, String phone, AdminRole role,SmtUserStatus status) {
		super(firstName, middleName, lastName, userName, password, email, address, phone,status);
		this.role=role;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "ADMINROLE")
	public AdminRole getRole() {
		return role;
	}

	public void setRole(AdminRole role) {
		this.role = role;
	}

	@Override
	@javax.persistence.Transient
	public Role getSmtRole() {
		return Role.Admin;
	}
	

}
