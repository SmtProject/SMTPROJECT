package com.script.generator;

import com.model.entity.Project;
import com.script.generator.utils.CopyUtils;
import com.script.generator.utils.Replace;
import com.script.generator.utils.pathSelection;

public class CreateEmptyProject {
	
	public static final String PROJECT_SAMPLE = "PROJECT_SAMPLE";

	public static String createEmptyProject(Project project) {
		if(project==null)
			return null;
		String selectPath = pathSelection.selectPath();
		if(selectPath!=null) {
			CopyUtils.copy("Resources//project",selectPath);
			Replace.replace(selectPath, PROJECT_SAMPLE, project.getProjectName());
			Replace.replaceFolderName(selectPath+"//"+PROJECT_SAMPLE, project.getProjectName());
			return selectPath+"//"+project.getProjectName();
		}
		return null;
	}

}
