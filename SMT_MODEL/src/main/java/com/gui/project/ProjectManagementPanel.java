package com.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.entity.Project;
import com.script.generator.utils.Replace;

public class ProjectManagementPanel extends JPanel{

	private static final long serialVersionUID = -8488960117996515625L;

	private JTextField projectNameTxt;
	private RefreshListener refreshListener;
	private Project project;
	private JButton applyBtn;

	public ProjectManagementPanel(Project project,RefreshListener refreshListener) {
		this.project=project;
		this.refreshListener=refreshListener;
		initComponents();
		initLayouts();
		inittData();
	}
	private void initComponents() {
		projectNameTxt=new JTextField();
		applyBtn=new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyBtnClicked();
			}
		});
	}
	protected void applyBtnClicked() {
		if(project!=null) {
			project.setProjectName(Replace.replaceBadCharacters(projectNameTxt.getText()));
			if(refreshListener!=null)
				refreshListener.refreshTable();
		}
	}
	protected void inittData() {
		if(project!=null) {
			projectNameTxt.setText(project.getProjectName());
		}
	}
	private void initLayouts() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("100dlu,10dlu,fill:p:grow"),this);
		builder.appendSeparator("<html><b>Project</b></html>");
		builder.append("Project Name",projectNameTxt);
		builder.append(applyBtn,3);

	}
}
