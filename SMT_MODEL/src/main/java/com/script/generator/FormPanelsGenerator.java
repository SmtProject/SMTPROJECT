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

public class FormPanelsGenerator {

	private static final String MODEL_PATH = "/src/main/java/com/gui";

	public static void generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				FileUtils.getFile(generateClassAsText(projectEntity,project.getProjectEntityEntityRelation(projectEntity)), modelPath,projectEntity.getClassName()+"Form.java");
			}
			if(project.getRelations()!=null) {
				for (EntityRelation entityRelation : project.getRelations()) {
					if(EntityRelationType.MANY.equals(entityRelation.getEntityRelationType1())&& EntityRelationType.MANY.equals(entityRelation.getEntityRelationType2())) {
						FileUtils.getFile(generateManyToManyClassAsText(entityRelation), modelPath,entityRelation.getManyToManyName()+"Form.java");
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
		return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MANY_TO_MANY_RELATION_FORM);
	}
	private static String generateClassAsText(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		if(projectEntity!=null) {
			String components="";
			String bindings="";
			String captions="";
			String addCompoenents="addComponents(";
			for (Attribute attribute : projectEntity.getAttributes()) {
				components+="\n"+attribute.getFromatedComponent();
				String bind=attribute.getEntityType().getBinder(projectEntity.getClassName(), attribute.getEntityName().toLowerCase(),attribute.getFormatedEntityName());
				if(bind!=null)
					bindings+="\n"+bind;
				captions+="\n"+attribute.getFromatedComponentCaption();
				addCompoenents+=attribute.getEntityName().toLowerCase()+",";
			}
			if(entityRelations!=null) {
				for (EntityRelation entityRelation : entityRelations) {
					String comp=entityRelation.getManyToOneFormComponent(projectEntity);
					String compName=entityRelation.getManyToOneFormComponentName(projectEntity);
					String cap=entityRelation.getManyToOneFormComponentCaption(projectEntity);
					if(comp!=null) {
						components+="\n"+comp;
					}
					if(compName!=null) {
						addCompoenents+=compName.toLowerCase()+",";
					}
					if(cap!=null) {
						captions+="\n"+cap;
					}
				}
			}
			addCompoenents+="buttons);";
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(GuiGeneratorConstants.CLASS_NAME,projectEntity.getClassName());
			valuesMap.put(GuiGeneratorConstants.COMPONENTS,components);
			valuesMap.put(GuiGeneratorConstants.BINDINGS,bindings);
			valuesMap.put(GuiGeneratorConstants.ADD_COMPONENTS,addCompoenents);
			valuesMap.put(GuiGeneratorConstants.SET_CAPTIONS,captions);
			valuesMap.put(GuiGeneratorConstants.CUS_MANY_TO_ONE,getConstructorManyToOne(projectEntity,entityRelations));
			valuesMap.put(GuiGeneratorConstants.CUSTOM_BTS,getManyToOneCustomBtns(projectEntity,entityRelations));

			return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.FORM_PANEL);
		}
		return null;
	}
	private static String getManyToOneCustomBtns(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		String result="";
		if(projectEntity!=null && entityRelations!=null) {
			for (EntityRelation entityRelation : entityRelations) {
				String toAdd=entityRelation.getManyToOneFormCustomBtns(projectEntity);
				if(toAdd!=null)
					result+=toAdd;
			}

		}
		return result;
	}	
	private static String getConstructorManyToOne(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		String result="";
		if(projectEntity!=null && entityRelations!=null) {
			for (EntityRelation entityRelation : entityRelations) {
				String toAdd=entityRelation.getManyToOneFormComponentData(projectEntity);
				if(toAdd!=null)
					result+=toAdd;
			}

		}
		return result;
	}
}
