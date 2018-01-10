package com.smt.application.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.smt.data.entity.SmtUser;

public interface SmtUserService extends Serializable{

	long getCount();

	void addAll(Collection<SmtUser> users);

	List<SmtUser> findAll();

	void add(SmtUser smtUser);
	
	SmtUser login(String username,String password);
}
