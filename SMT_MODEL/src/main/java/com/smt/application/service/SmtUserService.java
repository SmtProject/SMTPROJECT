package com.smt.application.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.SmtUser;

public interface SmtUserService extends Serializable{

	long getCount();

	void addAll(Collection<SmtUser> users);

	List<SmtUser> findAll();

	void save(SmtUser smtUser) throws ValidationException;
	
	SmtUser login(String username,String password);
}
