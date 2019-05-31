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

import com.model.common.Followed;
@Entity
@Table(name = "ENTITY_RELATION")
public class EntityRelation extends Followed implements Serializable{
	private static final long serialVersionUID = -4884556344336283745L;
	public String name;
	public ProjectEntity entity1;
	public EntityRelationType entityRelationType1;
	public ProjectEntity entity2;
	public EntityRelationType entityRelationType2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ENTITY_1")
	public ProjectEntity getEntity1() {
		return entity1;
	}
	public void setEntity1(ProjectEntity entity1) {
		this.entity1 = entity1;
	}
	@Column(name = "ENTITY_1_TYPE")
	@Enumerated(EnumType.STRING)
	public EntityRelationType getEntityRelationType1() {
		return entityRelationType1;
	}
	public void setEntityRelationType1(EntityRelationType entityRelationType1) {
		this.entityRelationType1 = entityRelationType1;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ENTITY_2")
	public ProjectEntity getEntity2() {
		return entity2;
	}
	public void setEntity2(ProjectEntity entity2) {
		this.entity2 = entity2;
	}
	@Column(name = "ENTITY_2_TYPE")
	@Enumerated(EnumType.STRING)
	public EntityRelationType getEntityRelationType2() {
		return entityRelationType2;
	}
	public void setEntityRelationType2(EntityRelationType entityRelationType2) {
		this.entityRelationType2 = entityRelationType2;
	}
	public static void updateFkIds(List<EntityRelation>entityRelations,ProjectEntity entity) {
		if(entityRelations!=null && entity!=null && entity.getId()!=null) {
			for (EntityRelation entityRelation : entityRelations) {
				if(entity.equals(entityRelation.getEntity1())) {
					entityRelation.getEntity1().setId(entity.getId());
				}
				if(entity.equals(entityRelation.getEntity2())) {
					entityRelation.getEntity2().setId(entity.getId());
				}
			}
		}
	}
	@Override
	public String toString() {
		return name;
	}
	
}
