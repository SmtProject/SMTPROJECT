package com.script.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.Attribute;
import com.model.entity.EntityRelation;
import com.model.entity.EntityRelationType;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class EntitiesGridGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/gui";

	public static void generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				FileUtils.getFile(generateClassAsText(projectEntity), modelPath,projectEntity.getClassName()+"Grid.java");
				generateManyToOneGrids(modelPath,projectEntity,project.getProjectEntityEntityRelation(projectEntity));
			}
			if(project.getRelations()!=null) {
				for (EntityRelation entityRelation : project.getRelations()) {
					if(EntityRelationType.MANY.equals(entityRelation.getEntityRelationType1())&& EntityRelationType.MANY.equals(entityRelation.getEntityRelationType2())) {
						FileUtils.getFile(generateManyToManyClassAsText(entityRelation), modelPath,entityRelation.getManyToManyName()+"Grid.java");
					}
				}
			}
		}
	}
	private static String generateManyToManyClassAsText(EntityRelation entityRelation) {
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put(ModelGenerationConstants.CLASS_NAME,entityRelation.getManyToManyName());
		valuesMap.put(ModelGenerationConstants.FIRST_ENTITY_NAME,entityRelation.getEntity1().getClassName());
		valuesMap.put(ModelGenerationConstants.FIRST_ENTITY_NAME_START_LOWER,ModelGenerationConstants.decapitalize(entityRelation.getEntity1().getClassName()));
		valuesMap.put(ModelGenerationConstants.SEC_ENTITY_NAME,entityRelation.getEntity2().getClassName());
		valuesMap.put(ModelGenerationConstants.SEC_ENTITY_NAME_START_LOWER,ModelGenerationConstants.decapitalize(entityRelation.getEntity2().getClassName()));
		return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MANY_TO_MANY_RELATION_GRID);
	}
	private static String generateClassAsText(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			String columns="";
			for (Attribute attribute : projectEntity.getAttributes()) {
				columns+=generateColumn(projectEntity,attribute);
			}
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(GuiGeneratorConstants.CLASS_NAME,projectEntity.getClassName());
			valuesMap.put(GuiGeneratorConstants.GRID_COLUMN,columns);
			return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.GRID_CLASS);
		}
		return null;
	}

	private static String generateColumn(ProjectEntity projectEntity, Attribute attribute) {
		if(attribute!=null && projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(GuiGeneratorConstants.CLASS_NAME,projectEntity.getClassName());
			valuesMap.put(GuiGeneratorConstants.ENTITY,attribute.getFormatedEntityName());
			return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.COLUMN);
		}
		return null;
	}
	
	private static void generateManyToOneGrids(String modelPath, ProjectEntity projectEntity,List<EntityRelation> projectEntityEntityRelation) {
		if(projectEntity!=null && projectEntityEntityRelation!=null) {
			for (EntityRelation entityRelationEntry : projectEntityEntityRelation) {
				String modelRelationSetterAndGetter=entityRelationEntry.getManyToOneCustomGrid(projectEntity);
				if(modelRelationSetterAndGetter!=null)
					FileUtils.getFile(modelRelationSetterAndGetter, modelPath,entityRelationEntry.getManyToOneCustomGridName(projectEntity)+".java");
			}
		}	
	}
}
