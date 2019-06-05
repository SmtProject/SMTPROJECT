package com.script.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.model.entity.Attribute;
import com.model.entity.EntityRelation;
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
		}
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
