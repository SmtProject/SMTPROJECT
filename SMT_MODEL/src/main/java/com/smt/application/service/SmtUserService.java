package com.smt.application.service;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Admin;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Teacher;

public interface SmtUserService extends Serializable{
	//--------------------------------------Login----------------------------------------------------------------
	
	SmtUser login(String username,String password);
	
	//--------------------------------------Admin-API----------------------------------------------------------------
	
	long getAdminsCount();

	void saveAdmin(List<Admin> users) throws ValidationException;

	List<Admin> findAllAdmins();

	Admin saveAdmin(Admin admin) throws ValidationException;
	
	//--------------------------------------Techers-API----------------------------------------------------------------
	
	List<Teacher> findAllATeachers();
	
	Teacher saveTeacher(Teacher teacher) throws ValidationException;
}
