package com.smt.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SmtUser implements Serializable{
	private static final long serialVersionUID = 2555049373808343194L;
	
	public enum SmtUserStatus{ACTIVE,INACTIVE}
	
	protected Integer id;
	protected String firstName;
	protected String middleName;
	protected String LastName;
	protected String userName;
	protected String password;
	protected String email;
	protected String address;
	protected String phone;
	protected SmtUserStatus status;
	protected String session;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	
	
	public SmtUser() {
		
	}

	public SmtUser(String firstName, String middleName, String lastName, String userName, String password,
			String email, String address, String phone) {
		this.firstName = firstName;
		this.middleName = middleName;
		LastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	@Column (name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	@Column(name = "PASSWORD")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING) 
	public SmtUserStatus getStatus() {
		return status;
	}

	public void setStatus(SmtUserStatus status) {
		this.status = status;
	}
	@Column(name = "SESSION")
	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	@Column(name = "CREATEDBY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "CREATEDATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "UPDATEDBY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "UPDATEDATE")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public void setFollowedAttribute(String userName,Date nowDate) {
		if(createdBy==null && createdDate==null) {
			this.createdBy=userName;
			this.createdDate=nowDate;
		}else {
			this.updatedBy=userName;
			this.updatedDate=nowDate;
		}
	}

}
