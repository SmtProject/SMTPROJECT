package com.script.generator.utils;

import javax.swing.JFileChooser;

public class pathSelection {
	
	public static String selectPath() {
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		f.showSaveDialog(null);
		if(f.getSelectedFile()==null)
			return null;
		return f.getSelectedFile().getPath();
	}

}
