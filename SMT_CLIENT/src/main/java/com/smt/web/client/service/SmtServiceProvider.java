package com.smt.web.client.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.smt.application.service.TestService;

public class SmtServiceProvider implements ApplicationContextAware,BeanFactoryPostProcessor{
	private static SmtServiceProvider instance;
	private TestService testService;
	private ApplicationContext applicationContext;
	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public static SmtServiceProvider getInstance() {
		if (instance == null)
			return new SmtServiceProvider();
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext =applicationContext;		
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		
	}
	public void init() {
		instance=this;
	}

}
