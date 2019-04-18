package com.script.generator.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtils {

	public static File getFile(String fileData,String filePath,String fileName) {
		if(fileData!=null) {
			File file = new File(filePath+"//"+fileName);
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file,true);
				fos.write(fileData.getBytes());
				fos.flush();
				fos.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return file;
		}
		return null;
	}


}
