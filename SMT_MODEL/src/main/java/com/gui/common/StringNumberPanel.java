package com.gui.common;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class StringNumberPanel extends JDialog {

	private static final long serialVersionUID = 3272648728184240865L;
	private DefaultListModel<String> model;
	private Integer modelSelectedIndex;
	private JTextField phoneTxt;
	private JButton saveBtn;

	public StringNumberPanel(DefaultListModel<String> model) {
		this(null, model);
	}

	public StringNumberPanel(Integer modelSelectedIndex, DefaultListModel<String> model) {
		super();
		this.modelSelectedIndex = modelSelectedIndex;
		this.model = model;
		initComponent();
		initLayout();
		fillTextFeild();
	}

	private void initComponent() {
		phoneTxt=new JTextField();		
		saveBtn=new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveclicked();
			}
		});
	}
	
	private void initLayout() {
		FormLayout Layout = new FormLayout("fill:150dlu:grow,10dlu,p");
		DefaultFormBuilder mainPanelBuilder = new DefaultFormBuilder(Layout);
		mainPanelBuilder.setDefaultDialogBorder();
		mainPanelBuilder.append(phoneTxt,saveBtn);
		this.add(mainPanelBuilder.getPanel());
	}

	public void showFrame() {
		this.pack();
		this.repaint();
		this.setSize(getPreferredSize());
		this.setMinimumSize(getPreferredSize());
		this.setResizable(false);
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenDim.width - this.getWidth()) / 2, (screenDim.height - this.getHeight()) / 2);
		this.setVisible(true);
	}

	protected void btnSaveclicked() {
		if(phoneTxt.getText()!=null && !phoneTxt.getText().isEmpty()){
			if (modelSelectedIndex == null) {
				model.addElement(phoneTxt.getText());
			}else{
				model.set(modelSelectedIndex, phoneTxt.getText());
			}
		}
		dispose();
	}

	private void fillTextFeild() {
		if (modelSelectedIndex != null)
			phoneTxt.setText(model.get(modelSelectedIndex).toString());

	}

}

