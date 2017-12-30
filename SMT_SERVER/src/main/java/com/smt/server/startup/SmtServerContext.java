package com.smt.server.startup;

import java.time.Duration;
import java.time.Instant;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SmtServerContext {
@SuppressWarnings("resource")
public static void main(String[] args) {
	Instant before = Instant.now();
	new ClassPathXmlApplicationContext("spring/smt-spring.xml");  
	Instant after = Instant.now();
	System.out.println("server start in:"+Duration.between(before, after).toMillis());  

}

}
