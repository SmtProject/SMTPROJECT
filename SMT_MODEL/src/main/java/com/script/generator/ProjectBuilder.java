package com.script.generator;

import javax.swing.JOptionPane;

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
					ProjectModelGenerator.generateModels(project,selectedPath);
					ProjectScriptGenerator.generateProjectScriptGenerator(project,selectedPath);
					ProjectModelRepositoriesGenerator.generateModels(project.getProjectEntitys(),selectedPath);
					SpringFileUpdator.Update(project,selectedPath);
					ValidationGenerator.generate(project, selectedPath);
					ServiceGenerator.generate(project,selectedPath);
					ServiceImplGenerator.generate(project, selectedPath);
					ServicesProviderGenerator.generate(project, selectedPath);
					ServicesProviderGeneratorXml.generate(project, selectedPath);
					EntitiesGridGenerator.generate(project, selectedPath);
					FormPanelsGenerator.generate(project, selectedPath);
					PanelsGenerator.generate(project, selectedPath);
					MainGuiGenarator.generate(project, selectedPath);
					ServiceApiGenerator.generate(project, selectedPath);
					JOptionPane.showMessageDialog(null, "Project Created");
				}
			}catch (Exception e) {
				System.out.println("Error in ProjectBuilder "+e.getMessage());
			}
		}
	}

}
