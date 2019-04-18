package com.script.generator.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Replace {
	
	public static String replaceBadCharacters(String string) {
		if(string==null)
			return null;
		return string.replace(" ", "");
	}
	
	public static void replaceFolderName(String folderPath,String newName) {
		File dir = new File(folderPath);
		if (!dir.isDirectory()) {
			System.err.println("There is no directory @ given path");
		} else {
			File newDir = new File(dir.getParent() + "//"+newName);
			dir.renameTo(newDir);	
		}
	}


	public static void replace(String folderPath,String oldString,String newString) {
		if(folderPath==null || oldString==null || newString==null)
			return ;
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles) {
			if(file.isDirectory())
				replace(file.getPath(), oldString, newString);
			else
				replaceText(file,oldString,newString);
		}
	}

	public static void replaceText(File file,String oldString,String newString) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			String line = "", oldtext = "";
			while ((line = reader.readLine()) != null) {
				oldtext += line + "\r\n";
			}
			reader.close();

			String replacedtext = oldtext.replaceAll(oldString, newString);

			FileWriter writer = new FileWriter(file);
			writer.write(replacedtext);

			writer.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}


