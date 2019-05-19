package com.gui.common;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;


public class StringsListComponent extends JPanel {

	private static final long serialVersionUID = -1931190467789584372L;
	private List<String> phonesList;
	private JButton addBtn;
	private JButton deleteBtn;
	private JList<String> phonesListgui;
	private DefaultListModel<String> model;
	private JScrollPane descriptionScrollPane;	

	public StringsListComponent(List<String> phonesList) {
		super();
		this.phonesList = phonesList;
		initComponent();
		initListners();
		initComponentsData();
		buildPanel();
	}

	public StringsListComponent() {
		this(new ArrayList<String>());
	}

	private void initComponent() {
		addBtn = new JButton("ADD");
		//		addBtn.setIcon(GuiUtilities.getImageIcon("images/add-16x16.png"));
		addBtn.setBorder(BorderFactory.createEmptyBorder());
		addBtn.setContentAreaFilled(false);

		deleteBtn =new JButton("DELETE");
		//		deleteBtn.setIcon(GuiUtilities.getImageIcon("images/delete-24x24.png"));	
		deleteBtn.setBorder(BorderFactory.createEmptyBorder());
		deleteBtn.setContentAreaFilled(false);

		model = new DefaultListModel<>();
		phonesListgui = new JList<String>(model);
		descriptionScrollPane = new JScrollPane(phonesListgui);

		descriptionScrollPane.setPreferredSize(new Dimension(186, 50));
	}

	private void buildPanel() {
		FormLayout Layout = new FormLayout("fill:p:grow,0dlu,p","fill:50dlu:grow");
		DefaultFormBuilder mainPanelBuilder = new DefaultFormBuilder(Layout,this);
		mainPanelBuilder.setDefaultDialogBorder();
		mainPanelBuilder.append(descriptionScrollPane);
		mainPanelBuilder.append(getButtonsPanel());
		this.setBorder(new EmptyBorder(10, 0, 10, 0));
	}

	private JPanel getButtonsPanel() {
		FormLayout Layout = new FormLayout("p");
		DefaultFormBuilder mainPanelBuilder = new DefaultFormBuilder(Layout);
		mainPanelBuilder.setDefaultDialogBorder();
		mainPanelBuilder.append(addBtn, deleteBtn);
		return mainPanelBuilder.getPanel();
	}

	private void initComponentsData() {
		if(phonesList!=null){
			for (int i = 0; i < phonesList.size(); i++) {
				model.addElement(phonesList.get(i));
			}
		}
	}

	private void initListners() {
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onBtnDeleteClicked();
			}
		});
		phonesListgui.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					onListDoubleClicked(evt);
				}
			}
		});
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onBtnAddClicked();
			}
		});
	}

	protected void onBtnDeleteClicked() {
		if (this.phonesListgui.getSelectedIndices().length > 0) {
			int[] selectedIndices = phonesListgui.getSelectedIndices();
			for (int i = selectedIndices.length - 1; i >= 0; i--) {
				model.removeElementAt(selectedIndices[i]);
			}
		}
	}

	protected void onListDoubleClicked(MouseEvent evt) {
		int index = phonesListgui.locationToIndex(evt.getPoint());
		updatePhonepanel(index);
	}

	public void onBtnAddClicked() {
		new StringNumberPanel(model).showFrame();
	}

	public void updatePhonepanel(int i) {
		new StringNumberPanel(i, model).showFrame();
	}

	public List<String> getList() {
		List<String> res = new ArrayList<String>();
		for (int i = 0; i < model.size(); i++) {
			res.add(model.get(i));
		}
		return res;
	}

	public void setPhonesList(List<String> phonesList) {
		this.phonesList = phonesList;
		initComponentsData();
	}
	public void  setEnable(boolean enable) {
		addBtn.setEnabled(enable);
		deleteBtn.setEnabled(enable);
	}
	public void clear() {
		phonesList.clear();
		phonesListgui.removeAll();
		model.clear();
	}
}
