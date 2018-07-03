package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.judge.dredd.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	FileService fileService;	

	@PostMapping(value = "dredd/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		return new ResponseEntity<>(fileService.upload(file), HttpStatus.OK);
	}
	
	@PostMapping(value = "dredd/api/upload/entry/{entryId}")
	public ResponseEntity<?> uploadEntryImage(@RequestParam("file") MultipartFile file, @PathVariable int entryId) {
		return new ResponseEntity<>(fileService.uploadEntryImage(file, entryId), HttpStatus.OK);
	}
	
//	@PostMapping(value = "dredd/api/loadFile/{fileName}")
//	public ResponseEntity<?> loadFile(@PathVariable String fileName) {
//		return new ResponseEntity<>(fileService.load(fileName), HttpStatus.OK);
//	}
	
	@GetMapping(value = "dredd/api/getAllFileNames")
	public ResponseEntity<?> getAllFileNames() {
		return new ResponseEntity<>(fileService.getAllFileNames(), HttpStatus.OK);
	}
	
	@GetMapping(value = "dredd/api/getFile/{fileName}")
	public ResponseEntity<?> getFile(@PathVariable String fileName) {
		
		try {
			Resource resource = fileService.getFile(fileName);
			
			return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "dredd/api/getImage/event/{eventId}/entry/{entryId}")
	public ResponseEntity<?> getFile(@PathVariable int eventId, @PathVariable int entryId) {
		
		try {
			Resource resource = fileService.getEntryImage(eventId, entryId);
			
			return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
