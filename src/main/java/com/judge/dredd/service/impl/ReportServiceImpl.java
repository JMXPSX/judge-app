package com.judge.dredd.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.model.Event;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.service.CriteriaService;
import com.judge.dredd.service.EntryService;
import com.judge.dredd.service.ReportService;
import com.judge.dredd.service.ScoreService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CriteriaService criteriaService;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private ScoreService scoreService;

	@Override
	public String getReport(long eventId) {
		
		Event event = eventRepository.findById(eventId).get();		
		List<CriteriaDTO> criterias = criteriaService.getByEventDetailId(eventId);		
		List<EntryDTO> entries = entryService.getAllEntriesByEventId(eventId);			
		Workbook workbook = new XSSFWorkbook();		
		Map<String, CellStyle> styles = createStyles(workbook);
		
		Sheet sheet = workbook.createSheet("All");
		PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        
        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Event : " + event.getEventName());
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);        
        Cell headerCell; 
        
        for (int i = 0; i < criterias.size() + 3; i++) {   
        	headerCell = headerRow.createCell(i);
        	if(i != 0 && i < criterias.size() + 1) {
        		headerCell.setCellValue(criterias.get(i - 1).getCriteriaName());
        		headerCell.setCellStyle(styles.get("header"));        		
        	}else if(i == criterias.size() + 1) {
        		headerCell.setCellValue("TOTAL");
        		headerCell.setCellStyle(styles.get("formula"));
        	}else if (i == criterias.size() + 2){
        		headerCell.setCellValue("AVERAGE");
        		headerCell.setCellStyle(styles.get("formula"));        		
        	}else {
        		headerCell.setCellStyle(styles.get("cell"));  
        	}
        }
        
        int rownum = 2;        
        for (int i = 0; i < entries.size(); i++) {
            Row row = sheet.createRow(rownum++);            
            CellAddress firstCell = null;            
            CellAddress lastCell = null;            
            for (int j = 0; j < criterias.size() + 3; j++) {
                Cell cell = row.createCell(j);                

                if(j == 1) {
                	firstCell = cell.getAddress();
                }else if(j == criterias.size()) {
                	lastCell = cell.getAddress();
                } 
                
                if(j == criterias.size() + 1) {
                	String ref = firstCell +":"+ lastCell;
                	cell.setCellFormula("SUM("+ref+")");
                	cell.setCellStyle(styles.get("formula"));
                }else if(j == criterias.size() + 2) {
                	String ref = firstCell +":"+ lastCell;
                	cell.setCellFormula("AVERAGE("+ref+")");
                	cell.setCellStyle(styles.get("formula"));                
                }else {
                	cell.setCellStyle(styles.get("cell"));  
                }                        
            }
        }        
        
        // Set Entry Name on cell
        for (int i = 0; i < entries.size(); i++) {
            Row row = sheet.getRow(2 + i);            
            row.getCell(0).setCellValue(entries.get(i).getEntryName()); 
        }        
        
       // Set Score per entry and criteria
        for (int i = 0; i < entries.size(); i++) {        	
        	Row row = sheet.getRow(2 + i);        	
        	List<ScoreDTO> scores = scoreService.getScoreByEventIdAndEntryId(event.getId(), entries.get(i).getEntryId());
        	
        	for (int j = 0; j < criterias.size() + 1; j++) {        		
        		Cell cell = row.getCell(j);        		
        		if(j != 0) {            		
            		CriteriaDTO criteria = criteriaService.getOne(criterias.get(j - 1).getCriteriaId());              		
            		double criteriaScore = 0;            		
            		for(int x = 0; x < scores.size(); x++) {
            			for(int y = 0; y < scores.get(x).getScores().size(); y++) {
            				if(scores.get(x).getScores().get(y).getCriteriaId() == criteria.getCriteriaId()) {
            					criteriaScore += scores.get(x).getScores().get(y).getScore();
            					break;
            				}
            			}
            		}            		
            		cell.setCellValue(criteriaScore);    			
        		}        		
        	}        	
        }

        //finally set column widths, the width is measured in units of 1/256th of a character width
        sheet.setColumnWidth(0, 30*256); //30 characters wide
        for (int i = 1; i < criterias.size() + 3; i++) {
            sheet.setColumnWidth(i, 20*256);  //6 characters wide
        }
        
        FileOutputStream fileOut = null;
		
        try {
        	fileOut = new FileOutputStream("Judge-app Report.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return "DOWNLOAD REPORT SUCCESS! " + entries;		
	}	
	
	private static Map<String, CellStyle> createStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<>();
        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)18);
        titleFont.setBold(true);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("title", style);
        
        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("header", style);
        
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("cell", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        style.setFont(monthFont);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        styles.put("formula", style);
		
		return styles;
	}

}
