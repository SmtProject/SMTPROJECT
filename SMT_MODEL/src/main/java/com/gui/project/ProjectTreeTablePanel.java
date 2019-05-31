package com.gui.project;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.context.ServicesProvider;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.entity.Attribute;
import com.model.entity.Project;
import com.model.entity.ProjectEntity;
import com.model.exception.CustomException;
import com.script.generator.ProjectBuilder;

public class ProjectTreeTablePanel extends JPanel implements RefreshListener{

	private static final long serialVersionUID = -2538090099328279910L;

	private JTree table;
	private JSplitPane splitPane;
	private ProjectJtreeModel tableModel;

	public ProjectTreeTablePanel() {
		initComponents();
		initLayout();

	}
	private void initComponents() {
		tableModel = new ProjectJtreeModel(LoadProjects());
		table=new JTree(tableModel);
		expandAll();
		addListeners();
		table.setPreferredSize(new Dimension(200,600));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,table,new JPanel());
	}

	private void initLayout() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("fill:p:grow"),this);
		builder.appendSeparator("<html><b>Project</b></html>");
		builder.append(splitPane);
	}

	protected void onProjectEntitySelected(ProjectEntity projectEntity) {
		if(projectEntity!=null) {
			splitPane.setRightComponent(new ProjectEntityManagementPanel(projectEntity,this));
		}
	}
	protected void onAttributeSelected(Attribute attribute, List<ProjectEntity> list, ProjectEntity projectEntity) {
		if(attribute!=null) {
			splitPane.setRightComponent(new AttributeManagementPanel(attribute,this));
		}
	}

	protected void onProjectSelected(Project project) {
		if(project!=null) {
			splitPane.setRightComponent(new ProjectManagementPanel(project, this));
		}
	}

	protected void initProjectRightClick(int x, int y, JTree tree, Project project) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem deleteProjectMenu = new JMenuItem("Delete");
		deleteProjectMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(project!=null) {
					getProjects().remove(project);
					refreshTable();
				}
			}
		});
		popup.add(deleteProjectMenu);
		JMenuItem addNewEntity = new JMenuItem("Add New Entity");
		addNewEntity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(project.getProjectEntitys()==null)
					project.setProjectEntitys(new ArrayList<>());
				project.getProjectEntitys().add(new ProjectEntity("New_Entity"));
				refreshTable();
			}
		});
		popup.add(addNewEntity);
		JMenuItem relations = new JMenuItem("Relations");
		relations.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(new ProjectRelationsPanel(project));
			}
		});
		popup.add(relations);

		JMenuItem generate = new JMenuItem("Generate Code");
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ServicesProvider.getInstance().getProjectManagementService().saveOrUpdateProjects(tableModel.getProjects());
					ProjectBuilder.buildProject(project);
				} catch (CustomException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		popup.add(generate);


		popup.show(tree, x, y);
	}
	protected void initProjectEntityRightClick(int x, int y, JTree tree, ProjectEntity selectedObject,Project parentPrject) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem deleteProjectMenu = new JMenuItem("Delete");
		deleteProjectMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parentPrject!=null && parentPrject.getProjectEntitys()!=null) {
					parentPrject.getProjectEntitys().remove(selectedObject);
					refreshTable();
				}
			}
		});
		popup.add(deleteProjectMenu);
		JMenuItem addNewAttributeMenu = new JMenuItem("add New Attribute");
		addNewAttributeMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedObject.getAttributes()==null)
					selectedObject.setAttributes(new ArrayList<>());
				selectedObject.getAttributes().add(new Attribute("New_Attribute"));
				refreshTable();
			}
		});
		popup.add(addNewAttributeMenu);
		popup.show(tree, x, y);

	}
	protected void initAttributeRightClick(int x, int y, JTree tree, Attribute selectedObject,ProjectEntity parentProjectEntity) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem deleteAttribite = new JMenuItem("Delete");
		deleteAttribite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(parentProjectEntity!=null && parentProjectEntity.getAttributes()!=null) {
					parentProjectEntity.getAttributes().remove(selectedObject);
					refreshTable();
				}
			}
		});
		popup.add(deleteAttribite);
		popup.show(tree, x, y);
	}

	private List<Project> LoadProjects() {
		try {
			return ServicesProvider.getInstance().getProjectManagementService().getAllProjects();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
	private void addNewProjectClicked() {
		Project newProject= new Project("New_Project");
		tableModel.addProject(newProject);
		refreshTable();
	}
	@Override
	public void refreshTable() {
		table.setModel(null);
		table.setModel(tableModel);
		expandAll();
	}
	public void expandAll() {
		int row = 0;
		while (row < table.getRowCount()) {
			table.expandRow(row);
			row++;
		}
	}
	private void addListeners() {
		table.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				Object selectedObject=  	table.getLastSelectedPathComponent();
				if(selectedObject instanceof Project) {
					Project project=(Project)selectedObject;
					onProjectSelected(project);
				}else if(selectedObject instanceof ProjectEntity) {
					ProjectEntity projectEntity=(ProjectEntity)selectedObject;
					onProjectEntitySelected(projectEntity);
				}
				else if(selectedObject instanceof Attribute) {
					Attribute attribute=(Attribute)selectedObject;
					Object[] path = e.getPath().getPath();
					Project project=(Project)path[path.length-3];
					ProjectEntity ProjectEntity=(ProjectEntity)path[path.length-2];
					onAttributeSelected(attribute,project.getProjectEntitys(),ProjectEntity);
				}

			}
		});
		MouseAdapter ma = new MouseAdapter() {
			private void myPopupEvent(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				JTree tree = (JTree)e.getSource();
				TreePath path = tree.getPathForLocation(x, y);
				if (path == null)
					return; 

				tree.setSelectionPath(path);

				Object selectedObject = (Object)path.getLastPathComponent();
				Object parentList = path.getParentPath();
				if(selectedObject instanceof Project) {
					initProjectRightClick(x, y, tree, (Project)selectedObject);
				}else  if(selectedObject instanceof ProjectEntity)  {
					Project parentProject = (Project)getParentFromPath(parentList);
					initProjectEntityRightClick(x, y, tree, (ProjectEntity)selectedObject,parentProject);
				}else  if(selectedObject instanceof Attribute)  {
					ProjectEntity parentProjectEntity = (ProjectEntity)getParentFromPath(parentList);
					initAttributeRightClick(x, y, tree, (Attribute)selectedObject,parentProjectEntity);
				}else  if(selectedObject instanceof String)  {
					JPopupMenu popup = new JPopupMenu();
					JMenuItem addProjectMenu = new JMenuItem("Add new Project");
					addProjectMenu.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							addNewProjectClicked();
						}
					});
					popup.add(addProjectMenu);
					popup.show(tree, x, y);
				}
				else {
					return;
				}
			}	
			private Object getParentFromPath(Object parentList) {
				Object parentProjectEntity=null;
				if(parentList instanceof TreePath) {
					TreePath list=(TreePath)parentList;
					parentProjectEntity=list.getLastPathComponent();
				}
				return parentProjectEntity;
			}

			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) myPopupEvent(e);
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) myPopupEvent(e);
			}
		};
		table.addMouseListener(ma);
	}
	public List<Project> getProjects() {
		return tableModel.getProjects();
	} 
}
