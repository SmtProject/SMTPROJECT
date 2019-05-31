package com.gui.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.entity.EntityRelation;
import com.model.entity.Project;

public class ProjectRelationsPanel extends JPanel{
	private static final long serialVersionUID = -7799219428634759325L;

	private Project project;
	private Map<EntityRelation, ProjectRelationPanel>projectRelationPanels=new HashMap<>();
	private DefaultFormBuilder builder;
	private JButton applyBtn;
	private JButton addBtn;


	public ProjectRelationsPanel(Project project) {
		this.project=project;
		initComponents();
		initLayouts();


	}

	private void initComponents() {
		addBtn=new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBtnClicked();
			}
		});
		
		applyBtn=new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyBtnClicked();
			}
		});
		if(project!=null && project.getRelations()!=null) {
			for (EntityRelation entityRelation : project.getRelations()) {
				projectRelationPanels.put(entityRelation, new ProjectRelationPanel(project.getProjectEntitys(), entityRelation));

			}
		}

	}

	protected void addBtnClicked() {
		EntityRelation entityRelation=new EntityRelation();
		ProjectRelationPanel panel=new ProjectRelationPanel(project.getProjectEntitys(), entityRelation);
		projectRelationPanels.put(entityRelation, panel);
		builder.append(panel);
		this.revalidate();
		this.repaint();
		
	}

	protected void applyBtnClicked() {
		List<EntityRelation>entityRelations=new ArrayList<>();
		for (ProjectRelationPanel projectRelationPanel : projectRelationPanels.values()) {
			entityRelations.add(projectRelationPanel.getEntityRelation());
		}	
		project.setRelations(entityRelations);
	}

	private void initLayouts() {
		builder =new DefaultFormBuilder(new FormLayout("fill:p:grow"),this);
		builder.appendSeparator("<html><b>Relations</b></html>");
		builder.append(addBtn);
		for (ProjectRelationPanel projectRelationPanel : projectRelationPanels.values()) {
			builder.append(projectRelationPanel);
		}
		builder.append(applyBtn);

	}

}
