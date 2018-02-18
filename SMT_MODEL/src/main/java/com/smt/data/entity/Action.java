package com.smt.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.ActionEnum;
import smt.model.tools.Role;

@Entity
@Table(name = "ACTION")
public class Action implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ActionEnum name;
	private Role smtRole;
	private Boolean isEnable;
	
	public Action() {
	}
	
	public Action(ActionEnum name, Role smtRole,Boolean isEnabled) {
		this.name = name;
		this.smtRole = smtRole;
		this.isEnable = isEnabled;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "NAME")
	@Enumerated(EnumType.STRING)
	public ActionEnum getName() {
		return name;
	}

	public void setName(ActionEnum name) {
		this.name = name;
	}
	
	@Column(name = "SMT_ROLE")
	@Enumerated(EnumType.STRING)
	public Role getSmtRole() {
		return smtRole;
	}
	public void setSmtRole(Role smtRole) {
		this.smtRole = smtRole;
	}
	
	@Column(name = "IS_ENABLED")
	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	
	

}
