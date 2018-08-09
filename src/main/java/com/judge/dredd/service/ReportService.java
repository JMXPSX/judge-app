package com.judge.dredd.service;

import java.util.List;

import org.springframework.core.io.Resource;

public interface ReportService {
	
	public String generateReport(long eventId, long userType);
	
	public List<String> getAllReportNames();
	
	public Resource getReportFile(String fileName) throws Exception;

}
