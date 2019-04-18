package com.script.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ServicesProviderGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/service";

	public static File generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			String classAsString=generateServiceAsText(project);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,"Services.java");
			}
		}
		return null;
	}
	private static String generateServiceAsText(Project project) {
		if(project!=null) {
			String body="";
			for (ProjectEntity ProjectEntity : project.getProjectEntitys()) {
				body+=generateServiceGetterAndSetterAsText(ProjectEntity);
			}
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_BODY, body);
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICES);
		}
		return null;
	}

	private static String generateServiceGetterAndSetterAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.CLASS_NAME_START_LOWE, ModelGenerationConstants.decapitalize(projectEntity.getClassName()));
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICES_GETTER_SETTER);
		}
		return null;
	}
}
