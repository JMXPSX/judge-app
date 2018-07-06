package com.judge.dredd.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public String upload(MultipartFile file);
	
	public String uploadEntryImage(MultipartFile file, int entryId);
	
	public Resource getEntryImage(int eventId, int entryId) throws Exception;
	
	public List<String> getAllFileNames();
	
	public Resource getFile(String fileName) throws Exception;
	
	public String load(String fileName) throws IOException;
}
