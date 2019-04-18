package com.gui.project;

import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.model.entity.Project;
import com.model.entity.ProjectEntity;

public class ProjectJtreeModel implements TreeModel{
	private String header;
	private List<Project> projects;

	public ProjectJtreeModel(List<Project> projects) {
		this.projects=projects;
		header="Projects";
	}

	@Override
	public Object getRoot() {
		return header;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if(header.equals(parent)) {
			return	projects.get(index);
		}
		if(parent instanceof Project) {
			Project project=(Project)parent;
			if(project.getProjectEntitys()==null)
				return null;
			return project.getProjectEntitys().get(index);
		}
		if(parent instanceof ProjectEntity) {
			ProjectEntity projectEntity=(ProjectEntity)parent;
			if(projectEntity.getAttributes()==null)
				return null;
			return projectEntity.getAttributes().get(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(header.equals(parent)) {
			return	projects.size();
		}
		if(parent instanceof Project) {
			Project project=(Project)parent;
			if(project.getProjectEntitys()==null)
				return 0;
			return project.getProjectEntitys().size();
		}
		if(parent instanceof ProjectEntity) {
			ProjectEntity projectEntity=(ProjectEntity)parent;
			if(projectEntity.getAttributes()==null)
				return 0;
			return projectEntity.getAttributes().size();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if(header.equals(node)) {
			return	false;
		}
		if(node instanceof Project) {
			return false;
		}
		if(node instanceof ProjectEntity) {
			return false;
		}
		
		return true;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return 0;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}
	public void addProject(Project project) {
		if(project!=null) {
			projects.add(project);
		}
		
	}

	public List<Project> getProjects() {
		return projects;
	}
	

}
