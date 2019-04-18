package com.context;

import java.time.Duration;
import java.time.Instant;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gui.ApplicationMainFrame;

public class MainContext {
	
	@SuppressWarnings("resource")
	public static void main(String [] args) {
		System.out.println("Application Will star soon ..");
		Instant before = Instant.now();
		new ClassPathXmlApplicationContext("spring/spring.xml");  
		Instant after = Instant.now();
		System.out.println("server start in:"+Duration.between(before, after).toMillis());  
		
		 ApplicationMainFrame.getinstance().showFrame();
	} 

}
