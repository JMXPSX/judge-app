package com.judge.dredd.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public String upload(MultipartFile file);
	
	public List<String> getAllFileNames();
	
	public Resource getFile(String fileName) throws Exception;
}
