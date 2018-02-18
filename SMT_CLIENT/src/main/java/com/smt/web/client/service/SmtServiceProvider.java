package com.smt.web.client.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smt.application.service.SmtActionService;
import com.smt.application.service.SmtImportService;
import com.smt.application.service.SmtUserService;

public class SmtServiceProvider {
	private static SmtServiceProvider instance;
	private ApplicationContext context = new ClassPathXmlApplicationContext("spring/smt-client-services.xml");  
	
	public SmtUserService getSmtUserService() {
		return (SmtUserService) context.getBean("SmtUserServiceBean");
	}
	public SmtImportService getSmtImportService(){
		return (SmtImportService)context.getBean("SmtImportServiceBean");
	}
	public SmtActionService getSmtActionService(){
		return (SmtActionService)context.getBean("SmtActionServiceBean");
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
