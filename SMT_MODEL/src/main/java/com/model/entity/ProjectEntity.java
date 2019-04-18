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
@Table(name = "PROJECT_ENTITY")
public class ProjectEntity extends Followed implements Serializable{
	private static final long serialVersionUID = 205853363658324620L;
	private String entityName;
	private Integer projectId;
	private List<Attribute> attributes;
	

	
	public ProjectEntity( ) {
		super();
		attributes=new ArrayList<>();
	}

	public ProjectEntity(String entityName) {
		super();
		this.entityName = entityName;
		attributes=new ArrayList<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name = "NAME")
	public String getEntityName() {
		return entityName;
	}
	
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	@Transient
	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return entityName;
	}
	@Column(name = "PROJECT_ID")
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	@Transient
	public String getRepositoryName() {
		return StringUtils.capitalize(getEntityName().toLowerCase())+"Repository";
	}
	@Transient
	public String getClassName(){
		return StringUtils.capitalize(getEntityName().toLowerCase());
	}
	
	@Transient
	public String getServiceName(){
		return getClassName()+"Service";
	}
}
