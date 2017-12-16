package com.smt.server.startup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmtServerContext {
@SuppressWarnings("resource")
public static void main(String[] args) {
	
	new ClassPathXmlApplicationContext("spring/smt-services.xml");  
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	System.out.println("server start in:"+dateFormat.format(date));  

}

}
