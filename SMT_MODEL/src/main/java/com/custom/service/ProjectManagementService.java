package com.custom.service;

import java.util.List;

import com.model.entity.EntityRelation;
import com.model.entity.Project;
import com.model.exception.CustomException;

public interface ProjectManagementService {
	
	public List<Project>getAllProjects() throws CustomException;
	
	public List<Project> saveOrUpdateProjects(List<Project>projects) throws CustomException;

	
}
