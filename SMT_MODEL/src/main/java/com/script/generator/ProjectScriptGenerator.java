package com.script.generator;

import java.io.File;

import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ProjectScriptGenerator {
	private static final String SCRIPT_PATH = "/SCRIPT";

	public static File generateProjectScriptGenerator(Project selectedProject , String modelPath) {
		if(selectedProject!=null) {
			String classAsString=generateProjectScript(selectedProject);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath+SCRIPT_PATH, selectedProject+".sql");
			}
		}
		return null;
	}

	public static String generateProjectScript(Project project) {
		if(project==null || project.getProjectEntitys()==null || project.getProjectEntitys().isEmpty())
			return "";
		String result="CREATE DATABASE "+project.getDataBaseName()+"; "+EntityScriptGenerator.NEW_LINE;
		for (ProjectEntity projectEntity : project.getProjectEntitys()) {
			result+=EntityScriptGenerator.generateScript(projectEntity);
			result+=EntityScriptGenerator.NEW_LINE+"----------------------------------------------------------------"+EntityScriptGenerator.NEW_LINE;
		}
		return result;
	}

}
