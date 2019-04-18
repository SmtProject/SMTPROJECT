package com.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.model.common.Followed;
@Entity
@Table(name = "PROJECT")
public class Project extends Followed implements Serializable{
	private static final long serialVersionUID = 4061441175240087239L;
	private String projectName;
	private List<ProjectEntity>projectEntitys;
	
	public Project() {
		super();
		this.projectEntitys=new ArrayList<>();
	}
	
	public Project(String projectName) {
		super();
		this.projectName = projectName;
		this.projectEntitys=new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "NAME")
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Transient
	public List<ProjectEntity> getProjectEntitys() {
		return projectEntitys;
	}

	public void setProjectEntitys(List<ProjectEntity> projectEntitys) {
		this.projectEntitys = projectEntitys;
	}
	@Override
	public String toString() {
		return projectName;
	}
	@Transient
	public String getDataBaseName(){
		return projectName.toUpperCase();
	}
}
