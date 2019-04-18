package com.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.model.common.AttributeDataType;
import com.model.common.Followed;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute extends Followed implements Serializable {
	private static final long serialVersionUID = 4458257710739947115L;

	private String entityName;
	private AttributeDataType entityType;
	private Boolean isMandatory;
	private Boolean isUnique;
	private Integer entity;
	private String refProjectName;

	public Attribute() {
		super();
	}

	public Attribute(String entityName) {
		super();
		this.entityName = entityName;
	}

	public Attribute(String entityName, AttributeDataType entityType, Boolean isMandatory, Boolean isUnique) {
		super();
		this.entityName = entityName;
		this.entityType = entityType;
		this.isMandatory = isMandatory;
		this.isUnique = isUnique;
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

	@Column(name = "ENTITY_TYPE")
	@Enumerated(EnumType.STRING)
	public AttributeDataType getEntityType() {
		return entityType;
	}

	public void setEntityType(AttributeDataType entityType) {
		this.entityType = entityType;
	}

	@Column(name = "IS_MANDATORY")
	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	@Column(name = "IS_UNIQUE")
	public Boolean getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}

	@Column(name = "ENTITY_ID")
	public Integer getEntity() {
		return entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}
	@Column(name = "REF_PROJECT_NAME")
	public String getRefProjectName() {
		return refProjectName;
	}

	public void setRefProjectName(String refProjectName) {
		this.refProjectName = refProjectName;
	}
	@Transient
	public ProjectEntity getReferenceProjectEntity(List<ProjectEntity>projectEnties) {
		if(projectEnties!=null && refProjectName!=null) {
			for (ProjectEntity projectEntity : projectEnties) {
				if(refProjectName.equals(projectEntity.getEntityName()))
					return projectEntity;
			}
		}
		return null;
	}
	@Transient
	public void setReferenceProjectEntity(ProjectEntity referenceProjectEntity) {
		if(referenceProjectEntity==null)
			refProjectName=null;
		else
			refProjectName=referenceProjectEntity.getEntityName();
	}

	@Override
	public String toString() {
		return entityName;
	}

}
