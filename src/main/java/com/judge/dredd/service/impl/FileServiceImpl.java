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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.service.EntryService;
import com.judge.dredd.service.FileService;
import com.judge.dredd.util.WorkBookUtil;

@Service
public class FileServiceImpl implements FileService {

	private static String UPLOADED_FOLDER = "./uploads/";
	private static String IMAGE_FOLDER = "./imgs/";
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private WorkBookUtil workBookUtil;
	
	@Override
	public String upload(MultipartFile file) {
		String message = null;
		try {
			if(null != file && !file.isEmpty()){
				mkDir(UPLOADED_FOLDER);
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
	
	public String mkDir(String s){
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
//	    Date date = new Date();  
//	    String folder = formatter.format(date); 
		
	    File file = new File(s);
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
	public List<EntryDTO> load(String fileName) throws Exception {
		Workbook workBook = null;
		
		try {
			Path path = Paths.get(UPLOADED_FOLDER + fileName);
			File file = path.toFile();
			
			workBook = WorkbookFactory.create(file);
			workBookUtil.doProcess(workBook);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(null != workBook){
					workBook.close();
			}
		}
		return workBookUtil.getEntryDTOs();
	}

	@Override
	public String uploadEntryImage(MultipartFile file, int entryId) {
		String message = null;
		long eventId = 0;
		try {
			EntryDTO entryDTO = entryService.getOne(entryId);
			if (null == entryDTO) {
				message = "entry " + entryId + " does not exists";
			} else if (null == file || file.isEmpty()) {
				message = "file is empty";
			} else {
				eventId = entryDTO.getEventId();

				String dir = mkDir(IMAGE_FOLDER + "event"+eventId + "/");

				byte bytes[] = file.getBytes();
				String ext = file.getOriginalFilename().split(Pattern.quote("."))[1];
				Path path = Paths.get(dir + entryId+"."+ext);

				Files.write(path, bytes);
				message = dir + entryId + " done.";
			}

		} catch (IOException e) {
			e.printStackTrace();
			message = e.getMessage();
		}
		return message;
	}
	
	@Override
	public Resource getEntryImage(int eventId, int entryId) throws Exception{
		
		String eventDir = IMAGE_FOLDER + "event"+eventId;
		
		String fileTarget = null;
		File dir = new File(eventDir);
		
		for(File file : dir.listFiles()){
			if(!file.isDirectory()){
				String[] fName = file.getName().split(Pattern.quote("."));
				if(String.valueOf(entryId).equals(fName[0])){
					fileTarget = file.getName();
					break;
				}
			}
		}
		
		if(null == fileTarget){
			throw new Exception("File not found!");
		}
		
		String path = eventDir+"/"+fileTarget;
		try {
			Path filePath = Paths.get(path);
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
			    return resource;
			}else {
			    throw new Exception("File not found " + path);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("File not found " + path);
		}
	}

}
