package com.smt.application.service;

import javax.xml.bind.ValidationException;

import smt.model.tools.LoggedInInfo;

public interface LoginService {

	public LoggedInInfo getLoggedInInfo(String username,String password) throws ValidationException;
}
