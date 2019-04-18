package com.script.generator;

import com.generator.model.ProjectModelGenerator;
import com.generator.model.ProjectModelRepositoriesGenerator;
import com.model.entity.Project;
import com.springupdator.SpringFileUpdator;

public class ProjectBuilder {

	public static void buildProject(Project project) {
		if(project!=null) {
			try {
				String selectedPath=CreateEmptyProject.createEmptyProject(project);
				if(selectedPath!=null) {
					ProjectModelGenerator.generateModels(project.getProjectEntitys(),selectedPath);
					ProjectScriptGenerator.generateProjectScriptGenerator(project,selectedPath);
					ProjectModelRepositoriesGenerator.generateModels(project.getProjectEntitys(),selectedPath);
					SpringFileUpdator.Update(project,selectedPath);
					ServiceGenerator.generate(project,selectedPath);
					ServiceImplGenerator.generate(project, selectedPath);
					ServicesProviderGenerator.generate(project, selectedPath);
					ServicesProviderGeneratorXml.generate(project, selectedPath);
				}
			}catch (Exception e) {
				System.out.println("Error in ProjectBuilder "+e.getMessage());
			}
		}
	}

}
