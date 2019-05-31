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

import com.model.common.Followed;
@Entity
@Table(name = "PROJECT")
public class Project extends Followed implements Serializable{
	private static final long serialVersionUID = 4061441175240087239L;
	private String projectName;
	private List<ProjectEntity>projectEntitys;

	@Transient
	private List<EntityRelation> relations;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
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
		Project other = (Project) obj;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		return true;
	}
	@Transient
	public List<EntityRelation> getRelations() {
		return relations;
	}
	@Transient
	public void setRelations(List<EntityRelation> relations) {
		this.relations = relations;
	}
	@Transient
	public List<EntityRelation>getProjectEntityEntityRelation(ProjectEntity entity){
		Set<EntityRelation> result=new HashSet<>();
		if(entity!=null && getRelations()!=null) {
			for (EntityRelation entityRelation : getRelations()) {
				if(entity.getId()!=null  && entityRelation.getEntity1()!=null) {
					if(entity.getId().equals(entityRelation.getEntity1().getId())) {
						result.add(entityRelation) ;

					}else if(entity.getId().equals(entityRelation.getEntity2().getId())) {
						result.add(entityRelation) ;
					}
				} 

			}	
		}
		return new ArrayList<>(result);
	}

}
