package com.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.gui.common.StringsListComponent;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.common.AttributeDataType;
import com.model.entity.Attribute;
import com.script.generator.utils.Replace;

public class AttributeManagementPanel extends JPanel{

	private static final long serialVersionUID = -8488960117996515625L;

	private JComboBox<AttributeDataType> entityTypeCbx;
	private JTextField entityTypeTxt;
	private JCheckBox isMandatoryCheckBx;
	private JCheckBox isUniqueCheckBx;
	private StringsListComponent stringsListComponent;
	private JButton applyBtn;
	private Attribute attribute;
	private RefreshListener refreshListener;
	private ActionListener action ;

	public AttributeManagementPanel(Attribute attribute,RefreshListener refreshListener) {
		this.attribute=attribute;
		this.refreshListener=refreshListener;
		initComponents();
		initLayouts();
		inittData();
	}
	private void initComponents() {
		entityTypeCbx=new JComboBox<AttributeDataType>(AttributeDataType.values());
		entityTypeTxt=new JTextField();
		isMandatoryCheckBx=new JCheckBox();
		isUniqueCheckBx=new JCheckBox();
		stringsListComponent=new StringsListComponent(attribute!=null?new ArrayList<>(attribute.getEnumValuesAsList()):new ArrayList<>());
		applyBtn=new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyBtnClicked();
			}
		});
		 action = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				stringsListComponent.clear();
				enableDisableStringsListComponent();
			}
		};
		entityTypeCbx.addActionListener (action);	
		enableDisableStringsListComponent();
	}
	protected void enableDisableStringsListComponent() {
		if(AttributeDataType.ENUM.equals(entityTypeCbx.getSelectedItem())) {
			stringsListComponent.setEnable(true);
		}else {
			stringsListComponent.setEnable(false);
		}
	}
	protected void applyBtnClicked() {
		if(attribute!=null) {
			attribute.setEntityType((AttributeDataType)entityTypeCbx.getSelectedItem());
			attribute.setEntityName(Replace.replaceBadCharacters(entityTypeTxt.getText()));
			attribute.setIsUnique(isUniqueCheckBx.isSelected());
			attribute.setIsMandatory(isMandatoryCheckBx.isSelected());
			attribute.setEnumValuesAsList(stringsListComponent.getList());
			if(refreshListener!=null)
				refreshListener.refreshTable();
		}
	}
	protected void inittData() {
		if(attribute!=null) {
			entityTypeCbx.removeActionListener(action);
			entityTypeCbx.setSelectedItem(attribute.getEntityType());
			entityTypeCbx.addActionListener (action);	
			entityTypeTxt.setText(attribute.getEntityName());
			isMandatoryCheckBx.setSelected(attribute.getIsMandatory()!=null?attribute.getIsMandatory():false);
			isUniqueCheckBx.setSelected(attribute.getIsUnique()!=null?attribute.getIsUnique():false);
		}
	}
	private void initLayouts() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("100dlu,10dlu,fill:p:grow"),this);
		builder.appendSeparator("<html><b>Attribute</b></html>");
		builder.append("Name",entityTypeTxt);
		builder.append("Type",entityTypeCbx);
		builder.append("is Mandatory",isMandatoryCheckBx);
		builder.append("is Unique",isUniqueCheckBx);
		builder.append("Values",stringsListComponent);
		builder.append(applyBtn,3);

	}


}
