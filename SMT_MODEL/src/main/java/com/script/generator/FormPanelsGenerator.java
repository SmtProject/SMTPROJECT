package com.script.generator;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.model.entity.Attribute;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class FormPanelsGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/gui";

	public static void generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				FileUtils.getFile(generateClassAsText(projectEntity), modelPath,projectEntity.getClassName()+"Form.java");
			}
		}
	}
	private static String generateClassAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			String components="";
			String bindings="";
			String captions="";
			String addCompoenents="addComponents(";
			for (Attribute attribute : projectEntity.getAttributes()) {
				components+="\n"+attribute.getFromatedComponent();
				String bind=attribute.getEntityType().getBinder(projectEntity.getClassName(), attribute.getEntityName().toLowerCase(),attribute.getFormatedEntityName());
				if(bind!=null)
					bindings+="\n"+bind;
				captions+="\n"+attribute.getFromatedComponentCaption();
				addCompoenents+=attribute.getEntityName().toLowerCase()+",";
			}
			addCompoenents+="buttons);";
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(GuiGeneratorConstants.CLASS_NAME,projectEntity.getClassName());
			valuesMap.put(GuiGeneratorConstants.COMPONENTS,components);
			valuesMap.put(GuiGeneratorConstants.BINDINGS,bindings);
			valuesMap.put(GuiGeneratorConstants.ADD_COMPONENTS,addCompoenents);
			valuesMap.put(GuiGeneratorConstants.SET_CAPTIONS,captions);

			return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.FORM_PANEL);
		}
		return null;
	}
}
