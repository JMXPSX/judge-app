package com.judge.dredd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.judge.dredd.service.ReportService;

@CrossOrigin
@RestController
@RequestMapping({"/dredd/api"})
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping(value = "/report/{eventId}")
	public ResponseEntity<?> getReport(@PathVariable long eventId){
		return new ResponseEntity<>(reportService.getReport(eventId), HttpStatus.OK);
	}
	
	@GetMapping(value = "/report/getAllReportNames")
	public ResponseEntity<?> getAllReportNames() {
		return new ResponseEntity<>(reportService.getAllReportNames(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/report/getReport/{fileName}")
	public ResponseEntity<?> getFile(@PathVariable String fileName) {
		
		try {
			Resource resource = reportService.getFile(fileName);
			
			return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType("application/octet-stream"))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
