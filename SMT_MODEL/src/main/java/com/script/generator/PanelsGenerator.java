package com.script.generator;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.EntityRelation;
import com.model.entity.EntityRelationType;
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
			if(project.getRelations()!=null) {
				for (EntityRelation entityRelation : project.getRelations()) {
					if(EntityRelationType.MANY.equals(entityRelation.getEntityRelationType1())&& EntityRelationType.MANY.equals(entityRelation.getEntityRelationType2())) {
						FileUtils.getFile(generateManyToManyClassAsText(entityRelation), modelPath,entityRelation.getManyToManyName()+"Panel.java");
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
		return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MANY_TO_MANY_RELATION_PANEl);
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
