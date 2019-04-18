package com.script.generator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ServicesProviderGeneratorXml {
	
	private static final String MODEL_PATH = "/src/main/java/com/spring";

	public static File generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			String classAsString=generateServiceXmlAsText(project);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,"server-beans.xml");
			}
		}
		return null;
	}
	private static String generateServiceXmlAsText(Project project) {
		if(project!=null) {
			String ref="";
			String beans="";
			for (ProjectEntity ProjectEntity : project.getProjectEntitys()) {
				beans+=generateServiceXmlBeanAsText(ProjectEntity)+"\n";
				ref+=generateServiceXmlBeanRefAsText(ProjectEntity)+"\n";
			}
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.STRING_SERVICES, beans);
			valuesMap.put(ModelGenerationConstants.STRING_REF, ref);
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.XML_SERVICES_BEANS);
		}
		return null;
	}

	private static String generateServiceXmlBeanAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICE_BEAN);
		}
		return null;
	}
	private static String generateServiceXmlBeanRefAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.CLASS_NAME_START_LOWE, ModelGenerationConstants.decapitalize(projectEntity.getClassName()));
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICE_BEAN_REF);
		}
		return null;
	}
}
