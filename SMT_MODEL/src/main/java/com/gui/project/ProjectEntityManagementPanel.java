package com.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.entity.ProjectEntity;
import com.script.generator.utils.Replace;

public class ProjectEntityManagementPanel extends JPanel{

	private static final long serialVersionUID = -8488960117996515625L;

	private JTextField projectEntityNameTxt;
	private RefreshListener refreshListener;
	private ProjectEntity projectEntity;
	private JButton applyBtn;

	public ProjectEntityManagementPanel(ProjectEntity projectEntity,RefreshListener refreshListener) {
		this.projectEntity=projectEntity;
		this.refreshListener=refreshListener;
		initComponents();
		initLayouts();
		inittData();
	}
	private void initComponents() {
		projectEntityNameTxt=new JTextField();
		applyBtn=new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyBtnClicked();
			}
		});
	}
	protected void applyBtnClicked() {
		if(projectEntity!=null) {
			projectEntity.setEntityName(Replace.replaceBadCharacters(projectEntityNameTxt.getText()));
			if(refreshListener!=null)
				refreshListener.refreshTable();
		}
	}
	protected void inittData() {
		if(projectEntity!=null) {
			projectEntityNameTxt.setText(projectEntity.getEntityName());
		}
	}
	private void initLayouts() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("100dlu,10dlu,fill:p:grow"),this);
		builder.appendSeparator("<html><b>Entity</b></html>");
		builder.append("Entity Name",projectEntityNameTxt);
		builder.append(applyBtn,3);

	}
}
