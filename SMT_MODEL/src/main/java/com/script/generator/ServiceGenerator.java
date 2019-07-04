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

public class ServiceGenerator {
	
	private static final String MODEL_PATH = "/src/main/java/com/service";
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
				return FileUtils.getFile(classAsString, modelPath,projectEntity.getServiceName()+".java");
			}
		}
		return null;
	}

	private static String generateServiceAsText(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		if(projectEntity!=null) {
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(ModelGenerationConstants.CLASS_NAME, projectEntity.getClassName());
			valuesMap.put(ModelGenerationConstants.ADDED_SERVICES, getAddedServices(projectEntity,entityRelations));

			return new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.SERVICE_TEMPLATE);
		}
		return null;
	}
	private static String getAddedServices(ProjectEntity projectEntity, List<EntityRelation> entityRelations) {
		String result="";
		if(projectEntity!=null && entityRelations!=null) {
			for (EntityRelation entityRelationEntry : entityRelations) {
				String modelRelationSetterAndGetter=entityRelationEntry.getManyToOneService(projectEntity);
				if(modelRelationSetterAndGetter!=null)
					result+="\n"+modelRelationSetterAndGetter+"\n";
				String manyToManyServiceAdded=entityRelationEntry.getManyToManyService(projectEntity);
				if(manyToManyServiceAdded!=null)
					result+="\n"+manyToManyServiceAdded+"\n";
			}
		}
		return result;
	}
}
