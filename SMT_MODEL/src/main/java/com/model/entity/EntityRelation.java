package com.model.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.commons.lang.text.StrSubstitutor;

import com.generator.comon.GuiGeneratorConstants;
import com.generator.comon.ModelGenerationConstants;
import com.google.common.collect.Lists;
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
	@Transient
	public String getModel(ProjectEntity projectEntity) {
		String result="";
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.MANY) && entityRelationType2.equals(EntityRelationType.ONE) ) {
					Map<String, String> valuesMap = new HashMap<String, String>();
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHER, entity2.getClassName());
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHER_UPPER, entity2.getClassName().toUpperCase());
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHERSTART_LOWE,  ModelGenerationConstants.decapitalize(entity2.getClassName()));
					result+= new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.ONE_TO_MANY);
				}
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					Map<String, String> valuesMap = new HashMap<String, String>();
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHER, entity2.getClassName());
					valuesMap.put(ModelGenerationConstants.UPPER_CLASS_NAME, entity1.getClassName().toUpperCase());
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHERSTART_LOWE,  ModelGenerationConstants.decapitalize(entity2.getClassName()));
					result+= new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MANY_TO_ONE);
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {

				if(entityRelationType2.equals(EntityRelationType.MANY) && entityRelationType1.equals(EntityRelationType.ONE) ) {
					Map<String, String> valuesMap = new HashMap<String, String>();
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHER, entity1.getClassName());
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHER_UPPER, entity1.getClassName().toUpperCase());
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHERSTART_LOWE,  ModelGenerationConstants.decapitalize(entity1.getClassName()));
					result+= new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.ONE_TO_MANY);
				}else if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					Map<String, String> valuesMap = new HashMap<String, String>();
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHER, entity1.getClassName());
					valuesMap.put(ModelGenerationConstants.UPPER_CLASS_NAME, entity2.getClassName().toUpperCase());
					valuesMap.put(ModelGenerationConstants.CLASS_NAME_ANOTHERSTART_LOWE,  ModelGenerationConstants.decapitalize(entity1.getClassName()));
					result+= new StrSubstitutor(valuesMap).replace(ModelGenerationConstants.MANY_TO_ONE);
				}
			}
		}
		return result;
	}

	@Transient
	public String getManyToOneFormComponent(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					return "ComboBox<"+entity2.getClassName()+"> "+entity2.getClassName().toLowerCase()+" = new ComboBox<"+entity2.getClassName()+">(\"\");	\r\n";
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return "ComboBox<"+entity1.getClassName()+"> "+entity1.getClassName().toLowerCase()+" = new ComboBox<"+entity1.getClassName()+">(\"\");	\r\n";
				}
			}
		}
		return null;
	}
	@Transient
	public String getManyToOneFormComponentName(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					return entity2.getClassName()	;
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return entity1.getClassName()	;
				}
			}
		}
		return null;
	}
	@Transient
	public String getManyToOneFormComponentCaption(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					return entity2.getClassName().toLowerCase()+".setCaption(\""+entity2.getClassName().toLowerCase()+"\");";

				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return entity1.getClassName().toLowerCase()+".setCaption(\""+entity1.getClassName().toLowerCase()+"\");";
				}
			}
		}
		return null;
	}
	@Transient
	public String getManyToOneFormComponentData(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					return"try {\r\n" + 
							"			"+entity2.getClassName().toLowerCase()+".setItems(Services.getinstance().get"+entity2.getServiceName()+"().getAll"+entity2.getClassName()+"());\r\n" + 
							"		} catch (CustomException e) {\r\n" + 
							"		}";
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return"try {\r\n" + 
							"			"+entity1.getClassName().toLowerCase()+".setItems(Services.getinstance().get"+entity2.getServiceName()+"().getAll"+entity2.getClassName()+"());\r\n" + 
							"		} catch (CustomException e) {\r\n" + 
							"		}";				}
			}
		}
		return null;
	}

	@Transient
	public String getManyToOneService(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {

					return "public List<"+entity1.getClassName()+"> getAll"+entity1.getClassName()+"By"+entity2.getClassName()+"Id(Integer id) throws CustomException;";
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return "public List<"+entity2.getClassName()+"> getAll"+entity2.getClassName()+"By"+entity1.getClassName()+"Id(Integer id) throws CustomException;";
				}
			}
		}
		return null;
	}
	@Transient
	public String getManyToOneServiceImpl(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					return "public List<"+entity1.getClassName()+"> getAll"+entity1.getClassName()+"By"+entity2.getClassName()+"Id(Integer id) throws CustomException {\r\n" + 
							"		return Lists.newArrayList("+ModelGenerationConstants.decapitalize(entity1.getRepositoryName())+".findAll(Q"+entity1.getClassName()+"."+ModelGenerationConstants.decapitalize(entity1.getClassName())+"."+ModelGenerationConstants.decapitalize(entity2.getClassName())+".id.eq(id)));\r\n" + 
							"	}";
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return "public List<"+entity2.getClassName()+"> getAll"+entity2.getClassName()+"By"+entity1.getClassName()+"Id(Integer id) throws CustomException {\r\n" + 
							"		return Lists.newArrayList("+ModelGenerationConstants.decapitalize(entity2.getRepositoryName())+".findAll(Q"+entity2.getClassName()+"."+ModelGenerationConstants.decapitalize(entity2.getClassName())+"."+ModelGenerationConstants.decapitalize(entity1.getClassName())+".id.eq(id)));\r\n" + 
							"	}";				}
			}
		}
		return null;
	}
	 
	@Transient
	public String getManyToOneCustomGrid(ProjectEntity projectEntity) {

		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					Map<String, String> valuesMap = new HashMap<String, String>();
					valuesMap.put(GuiGeneratorConstants.MAIN_CLASS_NAME,entity1.getClassName());
					valuesMap.put(GuiGeneratorConstants.SEC_CLASS_NAME,entity2.getClassName());
					valuesMap.put(GuiGeneratorConstants.SEC_CLASS_NAME_LOWER,ModelGenerationConstants.decapitalize(entity2.getClassName()));
					return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.MANY_TO_ONE_CUSTOM_GRID);
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					Map<String, String> valuesMap = new HashMap<String, String>();
					valuesMap.put(GuiGeneratorConstants.MAIN_CLASS_NAME,entity2.getClassName());
					valuesMap.put(GuiGeneratorConstants.SEC_CLASS_NAME,entity1.getClassName());
					valuesMap.put(GuiGeneratorConstants.SEC_CLASS_NAME_LOWER,ModelGenerationConstants.decapitalize(entity1.getClassName()));
					return new StrSubstitutor(valuesMap).replace(GuiGeneratorConstants.MANY_TO_ONE_CUSTOM_GRID);
				}
			}
		}
		return null;
	}
	@Transient
	public String getManyToOneCustomGridName(ProjectEntity projectEntity) {

		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.ONE) && entityRelationType2.equals(EntityRelationType.MANY) ) {
					return entity1.getClassName()+"sBy"+entity2.getClassName()+"sGrid";
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.ONE) && entityRelationType1.equals(EntityRelationType.MANY) ) {
					return entity2.getClassName()+"sBy"+entity1.getClassName()+"sGrid";
				}
			}
		}
		return null;
	}

	public String getManyToOneFormCustomBtns(ProjectEntity projectEntity) {
		if(projectEntity!=null && projectEntity.getId()!=null) {
			if(entity1!=null && projectEntity.getId().equals(entity1.getId())) {
				if(entityRelationType1.equals(EntityRelationType.MANY) && entityRelationType2.equals(EntityRelationType.ONE) ) {
					
					return "Button btns1=new Button(\""+entity2.getClassName()+"s\");\n" + 
							"	buttons.addComponent(btns1);\n" + 
							"	btns1.addClickListener(e ->{ if(object!=null) {\n" + 
							"		new "+entity2.getClassName()+"sBy"+entity1.getClassName()+"sGrid(object).showGrid();\n" + 
							"	}\n" + 
							"	});";
				}
			}
			else if(entity2!=null && projectEntity.getId().equals(entity2.getId())) {
				if(entityRelationType2.equals(EntityRelationType.MANY) && entityRelationType1.equals(EntityRelationType.ONE) ) {
					return "Button btns1=new Button(\""+entity1.getClassName()+"s\");\n" + 
							"	buttons.addComponent(btns1);\n" + 
							"	btns1.addClickListener(e ->{ if(object!=null) {\n" + 
							"		new "+entity1.getClassName()+"sBy"+entity2.getClassName()+"sGrid(object).showGrid();\n" + 
							"	}\n" + 
							"	});";				}
			}
		}
		return null;
	
	}

}
