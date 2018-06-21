package com.judge.dredd.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.judge.dredd.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	private static String UPLOADED_FOLDER = "./uploads/";
	
	@Override
	public String upload(MultipartFile file) {
		String message = null;
		try {
			if(null != file && !file.isEmpty()){
				mkDir();
				String fileName = getFileName(UPLOADED_FOLDER, file.getOriginalFilename());
								
				byte bytes[] = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + fileName);
				
	            Files.write(path, bytes);
	            message = UPLOADED_FOLDER + fileName + " done.";
			}else{
				message = "file is empty";
			}
		} catch (IOException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}
	
	public String getFileName(String location, String fileName){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
	    String fileDate = formatter.format(date);
		
		int cnt=1;
		String[] parts = fileName.split(Pattern.quote("."));
		File newFile = new File(location+parts[0]+fileDate+"."+parts[1]);
		while(newFile.exists()){
			
			newFile = new File(location+parts[0]+fileDate+"("+cnt+")"+"."+parts[1]);
			cnt++;
		}
		return newFile.getName();
	}
	
	public String mkDir(){
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
//	    Date date = new Date();  
//	    String folder = formatter.format(date); 
		
	    File file = new File(UPLOADED_FOLDER);
	    if(!file.exists()){
	    	file.mkdirs();
	    }
	    return file.getAbsolutePath()+"/";
	}

	@Override
	public List<String> getAllFileNames() {
		List<String> fileNames = new ArrayList();
		File dir = new File(UPLOADED_FOLDER);
		
		for(File file : dir.listFiles()){
			
			if(!file.isDirectory()){
				fileNames.add(file.getName());
			}
		}
		return fileNames;
	}

	@Override
	public Resource getFile(String fileName) throws Exception {
		try {
			Path filePath = Paths.get(UPLOADED_FOLDER + fileName);
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
			    return resource;
			}else {
			    throw new Exception("File not found " + fileName);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("File not found " + fileName);
		}
	}

	@Override
	public String load(String fileName) {
		try {
			Path path = Paths.get(UPLOADED_FOLDER + fileName);
			File file = path.toFile();
			
			Workbook workBook = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
