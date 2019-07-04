package com.script.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;
import com.generator.comon.ModelGenerationConstants;
import com.model.entity.EntityRelation;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class ServiceApiGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/service/api";
	public static List<File> generate(Project project ,String selectedPath) {
		List<File>result=new ArrayList<File>();
		if(project!=null) {
			if(project.getProjectEntitys()!=null) {
				String modelPath=selectedPath+MODEL_PATH;
				for (ProjectEntity projectEntity : project.getProjectEntitys()) {
					File file=generateService(projectEntity,modelPath,project.getProjectEntityEntityRelation(projectEntity));
					if(file!=null)
						result.add(file);	
				}
			}
		}
		return result;
	}
	private static File generateService(ProjectEntity projectEntity, String modelPath, List<EntityRelation> entityRelations) {
		if(projectEntity!=null) {
			String classAsString=generateServiceAsText(projectEntity,entityRelations);
			if(classAsString!=null) {
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getClassName()+"ApiService.java");
			}
		}
		return null;
	}

	private static String generateServiceAsText(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.CLASS_NAME_START_LOWE, ModelGenerationConstants.decapitalize(projectEntity.getClassName()));
			valuesMap.put(ModelGenerationConstants.SERVICE_API_ATTRIBUTES,projectEntity.getAttributesAsStringApiParam());
			valuesMap.put(ModelGenerationConstants.ADDED_SERVICES, getAddedServices(projectEntity, entityRelations));
			valuesMap.put(ModelGenerationConstants.SERVICE_API_OBJECT_FILL, projectEntity.getFillObject());
			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICE_API_TEMPLATE);
		}
		return null;
	}
	private static String getAddedServices(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		String result="";
		if(projectEntity!=null && entityRelations!=null) {
			for (EntityRelation entityRelationEntry : entityRelations) {
				String modelRelationSetterAndGetter=entityRelationEntry.getManyToOneApiServiceImpl(projectEntity);
				if(modelRelationSetterAndGetter!=null)
					result+="\n"+modelRelationSetterAndGetter+"\n";
				
				String manyToManyServiceAdded=entityRelationEntry.getManyToManyServiceApi(projectEntity);
				if(manyToManyServiceAdded!=null)
					result+="\n"+manyToManyServiceAdded+"\n";
			}
		}
		return result;
	}
}
