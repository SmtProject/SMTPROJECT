package com.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private JButton applyBtn;
	private Attribute attribute;
	private RefreshListener refreshListener;

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
		applyBtn=new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyBtnClicked();
			}
		});
	}
	protected void applyBtnClicked() {
		if(attribute!=null) {
			attribute.setEntityType((AttributeDataType)entityTypeCbx.getSelectedItem());
			attribute.setEntityName(Replace.replaceBadCharacters(entityTypeTxt.getText()));
			attribute.setIsUnique(isUniqueCheckBx.isSelected());
			attribute.setIsMandatory(isMandatoryCheckBx.isSelected());
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
		}
	}
	private void initLayouts() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("100dlu,10dlu,fill:p:grow"),this);
		builder.appendSeparator("<html><b>Attribute</b></html>");
		builder.append("Name",entityTypeTxt);
		builder.append("Type",entityTypeCbx);
		builder.append("is Mandatory",isMandatoryCheckBx);
		builder.append("is Unique",isUniqueCheckBx);
		builder.append(applyBtn,3);

	}


}
