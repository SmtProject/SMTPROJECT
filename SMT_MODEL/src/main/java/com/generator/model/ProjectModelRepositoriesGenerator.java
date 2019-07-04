package com.generator.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.EntityRelation;
import com.model.entity.EntityRelationType;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ProjectModelRepositoriesGenerator {

	private static final String MODEL_PATH = "/src/main/java/com/repository";
	public static List<File> generateModels(Project project,String selectedPath) {
		List<File>result=new ArrayList<File>();
		if(project.getProjectEntitys()!=null) {
			String modelPath=selectedPath+MODEL_PATH;
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				File file=generateModel(projectEntity,modelPath);
				if(file!=null)
					result.add(file);	
			}
			List<File> manyToManyModels=generateManyToManyRepositories(modelPath,project.getRelations());
			if(manyToManyModels!=null)
				result.addAll(manyToManyModels);
		}
		return result;
	}
	private static List<File> generateManyToManyRepositories(String modelPath, List<EntityRelation> projectEntityEntityRelation) {
		if(projectEntityEntityRelation!=null && !projectEntityEntityRelation.isEmpty()) {
			List<File> result=new ArrayList<>();
			for (EntityRelation entityRelation : projectEntityEntityRelation) {
				if( EntityRelationType.MANY.equals(entityRelation.entityRelationType1) && EntityRelationType.MANY.equals( entityRelation.entityRelationType2)) {
					String classAsString=generateModelAsText(entityRelation);
					if(classAsString!=null) {
						result.add(FileUtils.getFile(classAsString, modelPath,entityRelation.getManyToManyRepositoryName()+".java"));
					}
				}
			}
			return result;
		}
		return null;
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
	private static String generateModelAsText(EntityRelation entityRelation) {
		if(entityRelation!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, StringUtils.capitalize(entityRelation.getManyToManyName()));
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.REPOSITORY_CLASS_TEMPLATE);
		}
		return null;
	}
}
