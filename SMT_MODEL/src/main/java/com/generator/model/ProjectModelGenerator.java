package com.generator.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.generator.comon.ModelGenerationConstants;
import com.model.common.AttributeDataType;
import com.model.entity.Attribute;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ProjectModelGenerator {

	private static final String MODEL_PATH = "/src/main/java/com/model";
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
			generateEnum(projectEntity, modelPath);
			String classAsString=generateModelAsText(projectEntity);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getClassName()+".java");
			}
		}
		return null;
	}

	private static String generateModelAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.CLASS_BODY, getBody(projectEntity));
			valuesMap.put(ModelGenerationConstants.UPPER_CLASS_NAME, projectEntity.getEntityName().toUpperCase());
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MODEL_CLASS_TEMPLATE);
		}
		return null;
	}
	private static String getBody(ProjectEntity projectEntity) {
		if(projectEntity==null || projectEntity.getAttributes()==null || projectEntity.getAttributes().isEmpty()) 
			return null;
		String result="";
		for (Attribute attribute : projectEntity.getAttributes()) {
			if(attribute.getEntityName()!=null && attribute.getEntityType()!=null) {
				Map<String, String> valuesMap = new HashMap<String, String>();
				valuesMap.put(ModelGenerationConstants.ATTRIBUTE_UPPER,attribute.getEntityName().toUpperCase());
				valuesMap.put(ModelGenerationConstants.ATTRIBUTE_START_UPPER, StringUtils.capitalize(attribute.getEntityName().toLowerCase()));
				valuesMap.put(ModelGenerationConstants.ATTRIBUTE_START_LOWER, ModelGenerationConstants.decapitalize(attribute.getEntityName().toLowerCase()));
				valuesMap.put(ModelGenerationConstants.ATTRIBUTE_TYPE, attribute.getFormatedEntityType());
				result+= new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MODEL_CLASS_TEMPLATE_GETTER_SETTER)+"\n";
			}
		}
		return result;
	}
	private static void generateEnum(ProjectEntity projectEntity, String modelPath) {
		if(projectEntity!=null && projectEntity.getAttributes()!=null) {
			for(Attribute attribute: projectEntity.getAttributes()) {
				if(attribute!=null && AttributeDataType.ENUM.equals(attribute.getEntityType())) {
					String enumClass="package com.model;\n" + 
							"\n" + 
							"public enum "+attribute.getFormatedEntityName()+" {\n" ;
					enumClass+=attribute.getFormatedEnumValues();
					enumClass+="}";
					FileUtils.getFile(enumClass, modelPath,attribute.getFormatedEntityName()+".java");
				}
			}
		}
	}

}
