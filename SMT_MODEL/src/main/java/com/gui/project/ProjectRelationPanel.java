package com.gui.project;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.entity.EntityRelation;
import com.model.entity.EntityRelationType;
import com.model.entity.ProjectEntity;

public class ProjectRelationPanel extends JPanel{

	private static final long serialVersionUID = 8906343419764048044L;
	
	private JTextField relationName;
	private JComboBox<ProjectEntity>relation1Entity;
	private JComboBox<EntityRelationType>relation1Type;

	private JComboBox<ProjectEntity>relation2Entity;
	private JComboBox<EntityRelationType>relation2Type;

	private List<ProjectEntity> projectEntitis;
	private EntityRelation entityRelation;


	public ProjectRelationPanel (List<ProjectEntity> projectEntitis,EntityRelation entityRelation){
		this.projectEntitis=projectEntitis;
		this.entityRelation=entityRelation;
		initComponents();
		initLayouts();
	}

	private void initComponents() {
		relation1Entity= new JComboBox<ProjectEntity>();
		relation1Type= new JComboBox<EntityRelationType>(EntityRelationType.values());

		relation2Entity= new JComboBox<ProjectEntity>();
		if(projectEntitis!=null) {
			for (ProjectEntity projectEntity : projectEntitis) {
				relation2Entity.addItem(projectEntity);
				relation1Entity.addItem(projectEntity);
			}
		}
		relation2Type= new JComboBox<EntityRelationType>(EntityRelationType.values());		
		relationName=new JTextField();
		initData();
	}

	private void initData() {
		if(entityRelation!=null) {
			relationName.setText(entityRelation.getName());
			relation1Entity.setSelectedItem(entityRelation.getEntity1());
			relation1Type.setSelectedItem(entityRelation.getEntityRelationType1());
			relation2Entity.setSelectedItem(entityRelation.getEntity2());
			relation2Type.setSelectedItem(entityRelation.getEntityRelationType2());
		}
	}

	private void initLayouts() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("fill:p:grow,15dlu,fill:p:grow,15dlu,fill:p:grow,15dlu,fill:p:grow,15dlu,fill:p:grow"),this);
		builder.append(relationName);
		builder.append(relation1Entity);
		builder.append(relation1Type);
		builder.append(relation2Entity);
		builder.append(relation2Type);
	}
	public EntityRelation getEntityRelation() {
		if(entityRelation==null)
			entityRelation=new EntityRelation();
		entityRelation.setEntity1((ProjectEntity)relation1Entity.getSelectedItem());
		entityRelation.setEntityRelationType1((EntityRelationType)relation1Type.getSelectedItem());
		entityRelation.setEntityRelationType2((EntityRelationType)relation2Type.getSelectedItem());
		entityRelation.setEntity2((ProjectEntity)relation2Entity.getSelectedItem());
		entityRelation.setName(relationName.getText());
		return entityRelation;
	}

}
