package com.script.generator;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.model.entity.EntityRelation;
import com.model.entity.EntityRelationType;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.FileUtils;

public class MainGuiGenarator {
	
	private static final String MODEL_PATH = "/src/main/java/com/gui";

	public static void generate(Project project, String path) {
		if(project!=null) {
			String modelPath=path+MODEL_PATH;
			FileUtils.getFile(mainGuiGenaration(project), modelPath,"MainGui.java");
		}
	}
	private static String mainGuiGenaration(Project project) {
		if(project!=null) {
			String grids="";
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				grids+="tabsheet.addTab(new "+projectEntity.getClassName()+"Panel(),\""+projectEntity.getClassName()+"\");\n";
			}
			if(project.getRelations()!=null) {
				for (EntityRelation entityRelation : project.getRelations()) {
					if(EntityRelationType.MANY.equals(entityRelation.getEntityRelationType1())&& EntityRelationType.MANY.equals(entityRelation.getEntityRelationType2())) {
						grids+="tabsheet.addTab(new "+entityRelation.getManyToManyName()+"Panel(),\""+entityRelation.getManyToManyName()+"\");\n";
					}
				}
			}
			Map<String, String> valuesMap = new HashMap<String, String>();
			valuesMap.put(GuiGeneratorConstants.GRIDS_CLASSES,grids);
			return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.MAIN_GUI_CLASS);
		}
		return null;
	}
}
