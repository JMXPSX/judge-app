package com.judge.dredd.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public List<String> getAllFileNames(String folderName) {
		
		List<String> fileNames = new ArrayList();
		File dir = new File(folderName);
		
		for(File file : dir.listFiles()){
			
			if(!file.isDirectory()){
				fileNames.add(file.getName());
			}
		}
		System.out.println("FILENAME : " + fileNames);
		return fileNames;				
	}

}
