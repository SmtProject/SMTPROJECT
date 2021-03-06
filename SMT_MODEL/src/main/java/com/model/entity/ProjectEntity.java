package com.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.generator.comon.ModelGenerationConstants;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityName == null) ? 0 : entityName.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectEntity other = (ProjectEntity) obj;
		if (entityName == null) {
			if (other.entityName != null)
				return false;
		} else if (!entityName.equals(other.entityName))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}
	@Transient
	public String getAttributesAsStringApiParam() {
		List<String> result=new ArrayList<>();
		for (Attribute attribute : attributes) {
			result.add(attribute.getAttributeAsStringForApiParam());
		}
		return String.join(",", result);
	}
	@Transient
	public String getFillObject() {
		String result="";
		if(attributes!=null) {
			for (Attribute attribute : attributes) {
				if(attribute.getEntityName()!=null)
					result+="object.set"+attribute.getFormatedEntityName()+"("+ModelGenerationConstants.decapitalize(attribute.getEntityName())+");\n";
			}
		}
		return result;
	}
	
}
