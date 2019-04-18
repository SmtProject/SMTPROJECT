package com.context;
import com.custom.service.ProjectManagementService;

public class ServicesProvider {
	private static ServicesProvider instance;
	
	private ProjectManagementService projectManagementService;
	
	public ServicesProvider() {
		instance=this;
	}

	public static ServicesProvider getInstance() {
		return instance;
	}

	public ProjectManagementService getProjectManagementService() {
		return projectManagementService;
	}

	public void setProjectManagementService(ProjectManagementService projectManagementService) {
		this.projectManagementService = projectManagementService;
	}

}
