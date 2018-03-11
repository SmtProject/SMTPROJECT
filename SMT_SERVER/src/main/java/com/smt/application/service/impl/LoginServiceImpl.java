package com.smt.application.service.impl;

import javax.xml.bind.ValidationException;

import com.smt.application.service.LoginService;
import com.smt.application.service.SmtActionService;
import com.smt.application.service.SmtUserService;
import com.smt.application.service.YearService;

import smt.model.tools.LoggedInInfo;

public class LoginServiceImpl  implements LoginService {

	
	private SmtActionService smtActionService;
	private SmtUserService smtUserService;
	private YearService yearService;
	
	public LoginServiceImpl(SmtActionService smtActionService,SmtUserService smtUserService,YearService yearService) {
		this.smtActionService=smtActionService;
		this.smtUserService=smtUserService;
		this.yearService=yearService;
	}
	
	public LoginServiceImpl() {
		
	}

	@Override
	public LoggedInInfo getLoggedInInfo(String username, String password) throws ValidationException{
		LoggedInInfo loggedInInfo=new LoggedInInfo();
		loggedInInfo.setUser(smtUserService.login(username, password));
		loggedInInfo.setSelectedYear(yearService.getCurrentYear());
		if(loggedInInfo.getUser()==null)
			return null;
		loggedInInfo.setActions(smtActionService.findActionByRole(loggedInInfo.getUser().getSmtRole()));
		return loggedInInfo;
	}

	public SmtActionService getSmtActionService() {
		return smtActionService;
	}

	public void setSmtActionService(SmtActionService smtActionService) {
		this.smtActionService = smtActionService;
	}

	public SmtUserService getSmtUserService() {
		return smtUserService;
	}

	public void setSmtUserService(SmtUserService smtUserService) {
		this.smtUserService = smtUserService;
	}

	public YearService getYearService() {
		return yearService;
	}

	public void setYearService(YearService yearService) {
		this.yearService = yearService;
	}



}
