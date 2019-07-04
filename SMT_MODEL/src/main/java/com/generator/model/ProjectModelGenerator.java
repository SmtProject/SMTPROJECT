package com.generator.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.common.AttributeDataType;
import com.model.entity.Attribute;
import com.model.entity.EntityRelation;
import com.model.entity.EntityRelationType;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ProjectModelGenerator {

	private static final String MODEL_PATH = "/src/main/java/com/model";
	public static List<File> generateModels(Project project,String selectedPath) {
		List<File>result=new ArrayList<File>();
		if(project.getProjectEntitys()!=null) {
			String modelPath=selectedPath+MODEL_PATH;
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				File file=generateModel(projectEntity,modelPath,project.getProjectEntityEntityRelation(projectEntity));
				if(file!=null)
					result.add(file);	
			}
			List<File> manyToManyModels=generateManyToManyModel(modelPath,project.getRelations());
			if(manyToManyModels!=null)
				result.addAll(manyToManyModels);
		}
		return result;
	}
	private static List<File>  generateManyToManyModel(String modelPath,List<EntityRelation> projectEntityEntityRelation) {
		if(projectEntityEntityRelation!=null && !projectEntityEntityRelation.isEmpty()) {
			List<File> result=new ArrayList<>();
			for (EntityRelation entityRelation : projectEntityEntityRelation) {
				if( EntityRelationType.MANY.equals(entityRelation.entityRelationType1) && EntityRelationType.MANY.equals( entityRelation.entityRelationType2)) {
					String classAsString=generateManyToManyModelAsText(entityRelation);
					if(classAsString!=null) {
						result.add(FileUtils.getFile(classAsString, modelPath,entityRelation.entity1.getClassName()+entityRelation.entity2.getClassName()+"Relation.java"));
					}
				}
			}
			return result;
		}
		return null;
	}
	private static String generateManyToManyModelAsText(EntityRelation entityRelation) {
		if(entityRelation!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.FIRST_ENTITY_NAME, entityRelation.getEntity1().getClassName());
			valuesMap.put(ModelGenerationConstants.FIRST_ENTITY_NAME_UPPER, entityRelation.getEntity1().getClassName().toUpperCase());
			valuesMap.put(ModelGenerationConstants.FIRST_ENTITY_NAME_START_LOWER, ModelGenerationConstants.decapitalize(entityRelation.getEntity1().getEntityName().toLowerCase()));
			valuesMap.put(ModelGenerationConstants.SEC_ENTITY_NAME, entityRelation.getEntity2().getClassName());
			valuesMap.put(ModelGenerationConstants.SEC_ENTITY_NAME_UPPER,  entityRelation.getEntity2().getClassName().toUpperCase());
			valuesMap.put(ModelGenerationConstants.SEC_ENTITY_NAME_START_LOWER, ModelGenerationConstants.decapitalize(entityRelation.getEntity2().getEntityName().toLowerCase()));
			
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MANY_TO_MANY_MODEL);
		}
		return null;
	}
	private static File generateModel(ProjectEntity projectEntity, String modelPath, List<EntityRelation> entityRelation) {
		if(projectEntity!=null) {
			generateEnum(projectEntity, modelPath);
			String classAsString=generateModelAsText(projectEntity,entityRelation);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getClassName()+".java");
			}
		}
		return null;
	}

	private static String generateModelAsText(ProjectEntity projectEntity, List<EntityRelation> entityRelation) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.CLASS_BODY, getBody(projectEntity));
			valuesMap.put(ModelGenerationConstants.RELATION, getRelation(projectEntity,entityRelation));
			valuesMap.put(ModelGenerationConstants.UPPER_CLASS_NAME, projectEntity.getEntityName().toUpperCase());
			valuesMap.put(ModelGenerationConstants.TO_STRING, generateToString(projectEntity.getAttributes()));
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MODEL_CLASS_TEMPLATE);
		}
		return null;
	}
	private static String generateToString(List<Attribute> attributes) {
		String result="@Override\n" + 
				"		public String toString() { "
				+ "return";
		if(attributes!=null) {
			for(int i=0;i<attributes.size();i++) {
				if(i==0)
					result+=" \" \"+" +ModelGenerationConstants.decapitalize(attributes.get(i).getEntityName().toLowerCase());
				else
					result+="+ \" \"+" +ModelGenerationConstants.decapitalize(attributes.get(i).getEntityName().toLowerCase());
			}
		}
		result+=";}";
		return result;
	}
	private static String getRelation(ProjectEntity projectEntity, List<EntityRelation> entityRelation) {
		String result="";
		if(projectEntity!=null && entityRelation!=null) {
			for (EntityRelation entityRelationEntry : entityRelation) {
				String modelRelationSetterAndGetter=entityRelationEntry.getModel(projectEntity);
				if(modelRelationSetterAndGetter!=null)
					result+="\n"+modelRelationSetterAndGetter+"\n";
			}

		}
		return result;
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
