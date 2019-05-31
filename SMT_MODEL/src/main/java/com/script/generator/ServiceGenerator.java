package com.script.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ServiceGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/service";
	public static List<File> generate(Project project ,String selectedPath) {
		List<File>result=new ArrayList<File>();
		if(project!=null) {
			if(project.getProjectEntitys()!=null) {
				String modelPath=selectedPath+MODEL_PATH;
				for (ProjectEntity projectEntity : project.getProjectEntitys()) {
					File file=generateService(projectEntity,modelPath);
					if(file!=null)
						result.add(file);	
				}
			}
		}
		return result;
	}
	private static File generateService(ProjectEntity projectEntity, String modelPath) {
		if(projectEntity!=null) {
			String classAsString=generateServiceAsText(projectEntity);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getServiceName()+".java");
			}
		}
		return null;
	}

	private static String generateServiceAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICE_TEMPLATE);
		}
		return null;
	}
}