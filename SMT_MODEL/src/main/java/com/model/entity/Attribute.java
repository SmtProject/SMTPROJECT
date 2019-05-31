package com.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

import org.apache.commons.lang.StringUtils;

import com.model.common.AttributeDataType;
import com.model.common.Followed;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute extends Followed implements Serializable {
	private static final long serialVersionUID = 4458257710739947115L;
	private static final String SEP = "#_#";


	private String entityName;
	private AttributeDataType entityType;
	private Boolean isMandatory;
	private Boolean isUnique;
	private Integer entity;
	private String enumValues;


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

	@Transient
	public String getFormatedEntityName(){
		return StringUtils.capitalize(getEntityName().toLowerCase());
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

	public String getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(String enumValues) {
		this.enumValues = enumValues;
	}

	@Override
	public String toString() {
		return entityName;
	}
	@Transient
	public void setEnumValuesAsList(Collection<String>enumValues) {
		if(enumValues==null)
			this.enumValues=null;
		else
			this.enumValues=String.join(SEP, enumValues);

	}
	@Transient
	public Set<String> getEnumValuesAsList() {
		Set<String> result=new HashSet<>();
		if(enumValues!=null && !enumValues.isEmpty()) {
			String[] values=enumValues.split(SEP);
			for(String value:values) {
				result.add(value);
			}
		}
		return result;
	}
	@Transient
	public String getFromatedComponent(){
		if(AttributeDataType.ENUM.equals(entityType))
			return entityType.getComponent()+"<"+getFormatedEntityName()+"> "+getEntityName().toLowerCase()+" = new "+entityType.getComponent()+"<"+getFormatedEntityName()+">(\"\", Arrays.asList("+getFormatedEntityName()+".values()));";
		else
			return entityType.getComponent()+" "+getEntityName().toLowerCase()+" = new "+entityType.getComponent()+"(); ";

	}
	@Transient
	public String getFromatedComponentCaption(){
		return getEntityName().toLowerCase()+".setCaption(\""+getEntityName().toLowerCase()+"\");";
	}

	@Transient
	public String getFormatedEnumValues() {
		if(enumValues==null || enumValues.isEmpty())
			return "";
		return enumValues.replaceAll(SEP, ",")+";";
	}
	@Transient
	public String getFormatedEntityType() {
		if(AttributeDataType.ENUM.equals(getEntityType())) 
			return getFormatedEntityName();
		return getEntityType().toString();
	}


	}
