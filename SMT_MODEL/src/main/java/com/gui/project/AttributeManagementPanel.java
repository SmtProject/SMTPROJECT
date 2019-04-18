package com.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.common.AttributeDataType;
import com.model.entity.Attribute;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.Replace;

public class AttributeManagementPanel extends JPanel{

	private static final long serialVersionUID = -8488960117996515625L;

	private JComboBox<AttributeDataType> entityTypeCbx;
	private JTextField entityTypeTxt;
	private JCheckBox isMandatoryCheckBx;
	private JCheckBox isUniqueCheckBx;
	private JButton applyBtn;
	private Attribute attribute;
	private RefreshListener refreshListener;
	private JComboBox<ProjectEntity> projectEntityCbx;
	private List<ProjectEntity>projectEntities;
	private ProjectEntity parent;

	public AttributeManagementPanel(Attribute attribute,RefreshListener refreshListener,List<ProjectEntity>projectEntities,ProjectEntity parent) {
		this.attribute=attribute;
		this.refreshListener=refreshListener;
		this.projectEntities=projectEntities;
		this.parent=parent;
		initComponents();
		initLayouts();
		inittData();
	}
	private void initComponents() {
		entityTypeCbx=new JComboBox<AttributeDataType>(AttributeDataType.values());
		entityTypeTxt=new JTextField();
		isMandatoryCheckBx=new JCheckBox();
		isUniqueCheckBx=new JCheckBox();
		applyBtn=new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyBtnClicked();
			}
		});
		projectEntityCbx=new JComboBox<ProjectEntity> (projectEntities(projectEntities, parent));
		entityTypeCbx.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SetProjectEntityCbxStatus();
			}
		});
		SetProjectEntityCbxStatus();
	}
	protected void SetProjectEntityCbxStatus() {
		AttributeDataType selectedItem = (AttributeDataType)entityTypeCbx.getSelectedItem();
		projectEntityCbx.setEnabled(selectedItem!=null? selectedItem.isObject():false);		
	}
	protected void applyBtnClicked() {
		if(attribute!=null) {
			attribute.setEntityType((AttributeDataType)entityTypeCbx.getSelectedItem());
			attribute.setEntityName(Replace.replaceBadCharacters(entityTypeTxt.getText()));
			attribute.setIsUnique(isUniqueCheckBx.isSelected());
			attribute.setIsMandatory(isMandatoryCheckBx.isSelected());
			attribute.setReferenceProjectEntity(getEntityRef());
			if(refreshListener!=null)
				refreshListener.refreshTable();
		}
	}
	protected void inittData() {
		if(attribute!=null) {
			entityTypeCbx.setSelectedItem(attribute.getEntityType());
			entityTypeTxt.setText(attribute.getEntityName());
			isMandatoryCheckBx.setSelected(attribute.getIsMandatory()!=null?attribute.getIsMandatory():false);
			isUniqueCheckBx.setSelected(attribute.getIsUnique()!=null?attribute.getIsUnique():false);
			projectEntityCbx.setSelectedItem(attribute.getReferenceProjectEntity(projectEntities));
		}
	}
	private ProjectEntity getEntityRef() {
		AttributeDataType attributeDataType=(AttributeDataType)entityTypeCbx.getSelectedItem();
		if(attributeDataType!=null && attributeDataType.isObject()) {
			return (ProjectEntity)projectEntityCbx.getSelectedItem();
		}
		return null;
	}
	private void initLayouts() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("100dlu,10dlu,fill:p:grow,10dlu,fill:p:grow"),this);
		builder.appendSeparator("<html><b>Attribute</b></html>");
		builder.append("Name",entityTypeTxt,3);
		builder.append("Type",entityTypeCbx,projectEntityCbx);
		builder.append("is Mandatory",isMandatoryCheckBx,3);
		builder.append("is Unique",isUniqueCheckBx,3);
		builder.append(applyBtn,5);

	}

	private static ProjectEntity[] projectEntities(List<ProjectEntity>projectEntities,ProjectEntity exclude) {
		if(projectEntities==null )
			return new ProjectEntity[0];
		ProjectEntity[] projectEntitiesArray=new ProjectEntity[projectEntities.size()-1];
		int i=0;
		for (ProjectEntity projectEntity : projectEntities) {
			if(!projectEntity.equals(exclude))
				projectEntitiesArray[i++]=projectEntity;
		}
		return projectEntitiesArray;
	}


}
