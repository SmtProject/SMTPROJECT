package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.custom.service.ProjectManagementService;
import com.google.common.collect.Lists;
import com.model.entity.Attribute;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.model.entity.QAttribute;
import com.model.entity.QProjectEntity;
import com.model.exception.CustomException;
import com.model.repository.AttributeRepository;
import com.model.repository.ProjectEntityRepository;
import com.model.repository.ProjectRepository;


public class ProjectManagementServiceImpl implements ProjectManagementService {

	@Autowired
	private AttributeRepository attributeRepository;
	@Autowired
	private ProjectEntityRepository projectEntityRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Override
	public List<Project> getAllProjects() throws CustomException {
		try {
			List<Project> result= projectRepository.findAll();
			for (Project project : result) {
				List<ProjectEntity> projectEntities=Lists.newArrayList(projectEntityRepository.findAll(QProjectEntity.projectEntity.projectId.eq(project.getId())));
				project.setProjectEntitys(projectEntities);
				for (ProjectEntity projectEntity : projectEntities) {
					projectEntity.setAttributes(Lists.newArrayList(attributeRepository.findAll(QAttribute.attribute.entity.eq(projectEntity.getId()))));
				}
			}
			return result;
		}catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	public Project _saveOrUpdateProject(Project project) throws CustomException {
		try {
			_validateProject(project);
			Project savedProject= projectRepository.save(project);
			for (ProjectEntity projectEntity : project.getProjectEntitys()) {
				projectEntity.setProjectId(savedProject.getId());
				ProjectEntity savedProjectEntity=projectEntityRepository.save(projectEntity);
				for (Attribute attribute : projectEntity.getAttributes()) {
					attribute.setEntity(savedProjectEntity.getId());
					Attribute savedAttribute=attributeRepository.save(attribute);
					attribute.setId(savedAttribute.getId());
				}
			}
			return project;
		}catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
	private void _validateProject(Project project) throws CustomException {
		if(project==null) 
			throw new CustomException("project is null");
		if(project.getProjectName()==null || project.getProjectName().isEmpty())
			throw new CustomException("project NAme is null");
		if(project.getProjectEntitys()==null || project.getProjectEntitys().isEmpty())
			throw new CustomException("project entities is null");
		for (ProjectEntity projectEntity : project.getProjectEntitys()) {
			_validateProjectEntity(projectEntity);
		}
	}
	private void _validateProjectEntity(ProjectEntity projectEntity) throws CustomException {
		if(projectEntity==null) 
			throw new CustomException("projectEntity is null");
		if(projectEntity.getEntityName()==null || projectEntity.getEntityName().isEmpty())
			throw new CustomException("Entity Name is null");
		if(projectEntity.getAttributes()==null || projectEntity.getAttributes().isEmpty())
			throw new CustomException("Attributes is null");
		for (Attribute attribute : projectEntity.getAttributes()) {
			if(attribute==null) 
				throw new CustomException("Attribute is null");
			if(attribute.getEntityName()==null || attribute.getEntityName().isEmpty())
				throw new CustomException("Attribute Name is null");
			if(attribute.getEntityType()==null )
				throw new CustomException("Attribute Type is null");
			if(attribute.getIsMandatory()==null )
				throw new CustomException("IsMandatory is null");
			if(attribute.getIsUnique()==null )
				throw new CustomException("IsUnique is null");
		}
	}
	@Override
	public List<Project> saveOrUpdateProjects(List<Project> projects) throws CustomException {
		if(projects==null) 
			throw new CustomException("projects is null");
		try {
			attributeRepository.deleteAll();
			projectEntityRepository.deleteAll();
			projectRepository.deleteAll();
			List<Project> result=new ArrayList<>();
			for (Project project : projects) {
				result.add(_saveOrUpdateProject(project));
			}
			return result;
		}catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}


}
