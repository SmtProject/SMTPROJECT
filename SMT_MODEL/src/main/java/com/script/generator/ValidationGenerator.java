package com.script.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.ModelGenerationConstants;
import com.model.common.AttributeDataType;
import com.model.entity.Attribute;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ValidationGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/validation";
	public static List<File> generate(Project project ,String selectedPath) {
		List<File>result=new ArrayList<File>();
		if(project!=null) {
			if(project.getProjectEntitys()!=null) {
				String modelPath=selectedPath+MODEL_PATH;
				for (ProjectEntity projectEntity : project.getProjectEntitys()) {
					File file=generateValidation(projectEntity,modelPath);
					if(file!=null)
						result.add(file);	
				}
			}
		}
		return result;
	}
	private static File generateValidation(ProjectEntity projectEntity, String modelPath) {
		if(projectEntity!=null) {
			String classAsString=generateValidationAsText(projectEntity);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getClassName()+"Validation.java");
			}
		}
		return null;
	}

	private static String generateValidationAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.CLASS_NAME_START_LOWE, ModelGenerationConstants.decapitalize(projectEntity.getClassName()));
			String mandatory="";
			for (Attribute attribute : projectEntity.getAttributes()) {
				String valadateAttribute=getMandatoryValidation(attribute,ModelGenerationConstants.decapitalize(projectEntity.getClassName()));
				if(valadateAttribute!=null)
					mandatory+=valadateAttribute+"\n";
			}
			valuesMap.put(ModelGenerationConstants.VALIDATION_MANDATORY, mandatory);
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.VALIDATION);
		}
		return null;
	}
	private static String getMandatoryValidation(Attribute attribute,String classVariable) {
		if(attribute==null)
			return null;
		if(attribute.getIsMandatory()!=null && attribute.getIsMandatory()==true) {
			if(!AttributeDataType.STRING.equals(attribute.getEntityType()))
			return "if("+classVariable+".get"+attribute.getFormatedEntityName()+"()==null)\n" + 
					"	throw new CustomException(\"Empty "+attribute.getFormatedEntityName()+"\");";
			else 
				return "if("+classVariable+".get"+attribute.getFormatedEntityName()+"()==null ||  "+classVariable+".get"+attribute.getFormatedEntityName()+"().isEmpty())\n" + 
				"	throw new CustomException(\"Empty "+attribute.getFormatedEntityName()+"\");";
		}
		return null;
	}
}
