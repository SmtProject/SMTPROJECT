package com.script.generator;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class PanelsGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/gui";

	public static void generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				FileUtils.getFile(generateClassAsText(projectEntity), modelPath,projectEntity.getClassName()+"Panel.java");
			}
		}
	}
	private static String generateClassAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(GuiGeneratorConstants.CLASS_NAME,projectEntity.getClassName());

			return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.PANEL);
		}
		return null;
	}
}
