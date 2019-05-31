package com.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.context.ServicesProvider;
import com.gui.constant.GuiConstants;
import com.gui.project.ProjectTreeTablePanel;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.model.exception.CustomException;

public class ApplicationMainFrame extends JFrame{
	private static final long serialVersionUID = -2634427753054041155L;
	private static ApplicationMainFrame applicationMainFrame;
	private ProjectTreeTablePanel projectTreeTablePanel ;
	private JButton saveBtn;
	public static ApplicationMainFrame getinstance(){
		if(applicationMainFrame==null)
			applicationMainFrame=new ApplicationMainFrame();
		return applicationMainFrame;
	}

	private ApplicationMainFrame() { 
		super(GuiConstants.PROJECT_NAME);
		initLayout();
	}

	private void initLayout() {
		DefaultFormBuilder builder =new DefaultFormBuilder(new FormLayout("fill:p:grow"));
		builder.appendSeparator("<html><b>"+getTitle()+"</b></html>");
		projectTreeTablePanel = new ProjectTreeTablePanel();
		builder.append(projectTreeTablePanel);
		saveBtn=new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onBtnSaveClicked();
			}
		});
		builder.append(saveBtn);
		this.setContentPane(builder.getContainer());
	}

	protected void onBtnSaveClicked() {
		if(projectTreeTablePanel!=null) {
			try {
				ServicesProvider.getInstance().getProjectManagementService().saveOrUpdateProjects(projectTreeTablePanel.getProjects());
				JOptionPane.showMessageDialog(null,"Saved");
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}

	}

	public void showFrame() {
		this.pack();
		this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setVisible(true);
	}
}
