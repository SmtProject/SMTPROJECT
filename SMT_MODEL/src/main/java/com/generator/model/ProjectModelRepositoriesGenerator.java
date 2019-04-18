package com.generator.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ProjectModelRepositoriesGenerator {

	private static final String MODEL_PATH = "/src/main/java/com/repository";
	public static List<File> generateModels(List<ProjectEntity>projectEntitys,String selectedPath) {
		List<File>result=new ArrayList<File>();
		if(projectEntitys!=null) {
			String modelPath=selectedPath+MODEL_PATH;
			for (ProjectEntity projectEntity : projectEntitys) {
				File file=generateModel(projectEntity,modelPath);
				if(file!=null)
					result.add(file);	
			}
		}
		return result;
	}
	private static File generateModel(ProjectEntity projectEntity, String modelPath) {
		if(projectEntity!=null) {
			String classAsString=generateModelAsText(projectEntity);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getRepositoryName()+".java");
			}
		}
		return null;
	}

	private static String generateModelAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, StringUtils.capitalize(projectEntity.getEntityName()));
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.REPOSITORY_CLASS_TEMPLATE);
		}
		return null;
	}
}
