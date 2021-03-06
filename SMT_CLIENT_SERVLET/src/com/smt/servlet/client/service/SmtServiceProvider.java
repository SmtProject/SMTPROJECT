package com.smt.servlet.client.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smt.application.service.LoginService;
import com.smt.application.service.SmtActionService;
import com.smt.application.service.SmtPaymentService;
import com.smt.application.service.SmtUserService;
import com.smt.application.service.YearService;

public class SmtServiceProvider {
	private static SmtServiceProvider instance;
	private ApplicationContext context = new ClassPathXmlApplicationContext("spring/smt-client-services.xml");  
	
	public SmtUserService getSmtUserService() {
		return (SmtUserService) context.getBean("SmtUserServiceBean");
	}
	public YearService getSmtYearService(){
		return (YearService)context.getBean("SmtYearServiceBean");
	}
	public SmtActionService getSmtActionService(){
		return (SmtActionService)context.getBean("SmtActionServiceBean");
	}
	public LoginService getLoginService(){
		return (LoginService)context.getBean("SmtLoginServiceBean");
	}
	public SmtPaymentService getSmtPaymentService(){
		return (SmtPaymentService)context.getBean("SmtPaymentService");
	}
	
	public static SmtServiceProvider getInstance() {
		if (instance == null)
			return new SmtServiceProvider();
		return instance;
	}

	public void init() {
		instance=this;
	}

}
