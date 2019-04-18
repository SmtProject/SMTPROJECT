package com.springupdator;


import java.io.File;

import com.model.entity.Project;
import com.script.generator.utils.Replace;

public class SpringFileUpdator {

	private static final String JPA_BEANS_XML = "jpa-beans.xml";
	private static final String DATA_BASE_NAME_TO_CHANGE = "DATA_BASE_NAME_TO_CHANGE";
	private static final String SPRING_PATH = "/src/main/java/com/spring/";

	public static void Update(Project project,String selectedPath) {
		if(project!=null && selectedPath!=null) {
			setDataBaseName(project,selectedPath);
		}
	}

	private static void setDataBaseName(Project project, String selectedPath) {
		String filePathpath=selectedPath+SPRING_PATH+JPA_BEANS_XML;
		Replace.replaceText(new File(filePathpath) , DATA_BASE_NAME_TO_CHANGE, project.getDataBaseName());
	}
}
