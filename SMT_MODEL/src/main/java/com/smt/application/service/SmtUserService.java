package com.smt.application.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser;

public interface SmtUserService extends Serializable{

	long getAdminsCount();

	void addAllAdmins(Collection<Admin> users);

	List<Admin> findAllAdmins();

	Admin saveAdmin(Admin admin) throws ValidationException;
	
	SmtUser login(String username,String password);
}
