package com.judge.dredd.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				String location = mkDir();
				
				byte bytes[] = file.getBytes();
				Path path = Paths.get(location + file.getOriginalFilename());
				
	            Files.write(path, bytes);
			}else{
				message = "file is empty";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String mkDir(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
	    String folder = formatter.format(date); 
		
	    File file = new File(UPLOADED_FOLDER+folder);
	    if(!file.exists()){
	    	file.mkdirs();
	    }
	    return file.getAbsolutePath()+"/";
	}

}
