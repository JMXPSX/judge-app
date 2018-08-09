package com.judge.dredd.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.enums.UserType;
import com.judge.dredd.repository.AppUserRepository;
import com.judge.dredd.repository.CategoryRepository;
import com.judge.dredd.repository.EventRepository;
import com.judge.dredd.service.CriteriaService;
import com.judge.dredd.service.EntryService;
import com.judge.dredd.service.ReportService;
import com.judge.dredd.service.ScoreService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CriteriaService criteriaService;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private ScoreService scoreService;
	
	private static String UPLOADED_FOLDER = "./reports/";

	@Override
	public String generateReport(long eventId, long userTypeParam) {
		
		UserType userType = UserType.getUserTypeById((int) userTypeParam);
		
		if(null == userType) return "Invalid User Type " + userTypeParam;
		
		Event event = eventRepository.findById(eventId).get();		
		List<CriteriaDTO> criterias = criteriaService.getByEventDetailId(eventId);		
		List<EntryDTO> entries = entryService.getAllEntriesByEventId(eventId);		
		List<AppUser> userJudges = appUserRepository.findDistinctByEntries_Event_Id(event.getId());
		userJudges = userJudges.stream().filter(judge -> judge.getUserType() == userType.getId()).collect(Collectors.toList());	
		
		Workbook workbook = new XSSFWorkbook();		
		Map<String, CellStyle> styles = createStyles(workbook);
		
		Sheet allSheet = workbook.createSheet("All");		
		generateSheet(event, criterias, entries, allSheet, styles, 0);  		
			
		for(int x = 0; x < userJudges.size(); x++) {			
			List<EntryDTO> judgeEntries = entryService.getEntriesByEventIdAndUserId(eventId, userJudges.get(x).getUserId());
			Sheet judgeSheet = workbook.createSheet(userJudges.get(x).getUsername());
			generateSheet(event, criterias, judgeEntries, judgeSheet, styles, userJudges.get(x).getUserId());  			
		}
        
        FileOutputStream fileOut = null;
        String fileName = null;
        try {
        	mkDir();        	
        	fileName = getFileName(event.getEventName());        	
        	fileOut = new FileOutputStream(UPLOADED_FOLDER + fileName);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return fileName;		
	}

	@Override
	public List<String> getAllReportNames() {
		List<String> reportNames = new ArrayList();
		File dir = new File(UPLOADED_FOLDER);
		
		for(File file : dir.listFiles()){
			
			if(!file.isDirectory()){
				reportNames.add(file.getName());
			}
		}
		
		return reportNames;		
	}
	
	@Override
	public Resource getReportFile(String fileName) throws Exception {
		try {
			Path filePath = Paths.get(UPLOADED_FOLDER + fileName);
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
			    return resource;
			}else {
			    throw new Exception("File not found " + fileName);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new Exception("File not found " + fileName);
		}
	}

	private String mkDir() {
		File file = new File(UPLOADED_FOLDER);
	    if(!file.exists()){
	    	file.mkdirs();
	    }
	    return file.getAbsolutePath()+"/";
	}
	
	private String getFileName(String eventName) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();  
	    String fileDate = formatter.format(date);	    
	    
	    int cnt = 0;
	    List<String> reportNames = getAllReportNames();    	
	    String pattern = "\\b"+eventName+fileDate+"\\b"; 
    	Pattern p = Pattern.compile(pattern);
    	for(int x = 0; x < reportNames.size(); x++) {
    		Matcher m = p.matcher(reportNames.get(x));
    		if(m.find()) {
    			cnt++;
    		}
    	}
    	
    	File newFileName = null;
    	
    	if(cnt != 0) { 
    		newFileName = new File(eventName+fileDate+"("+cnt+")"+".xlsx"); 
		}else {
			newFileName = new File(eventName+fileDate+".xlsx");
		}
    	
		return newFileName.getName();
	}
	
	private void generateSheet(Event event, List<CriteriaDTO> criterias, List<EntryDTO> entries,
			Sheet sheet, Map<String, CellStyle> styles, long userId) {		

		PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        
        //title row
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(45);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(event.getEventName() + (userId != 0 ? " by " + appUserRepository.findById(userId).get().getUsername() : " Overall Score" ));
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));

        //header row
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);        
        Cell headerCell; 
        
        for (int i = 0; i < criterias.size() + 4; i++) {   
        	headerCell = headerRow.createCell(i);
        	
        	if(i == 1) {
        		headerCell.setCellValue("CATEGORY");
        		headerCell.setCellStyle(styles.get("formula"));   
        	}else if(i != 0 && i < criterias.size() + 2) {
        		headerCell.setCellValue(criterias.get(i - 2).getCriteriaName());
        		headerCell.setCellStyle(styles.get("header"));        		
        	}else if(i == criterias.size() + 2) {
        		headerCell.setCellValue("TOTAL");
        		headerCell.setCellStyle(styles.get("formula"));
        	}else if (i == criterias.size() + 3){
        		headerCell.setCellValue("AVERAGE");
        		headerCell.setCellStyle(styles.get("formula"));        		
        	}else {
        		headerCell.setCellStyle(styles.get("cell"));
        	}
        	
        }
        
        int rownum = 2;        
        for (int i = 0; i < entries.size(); i++) {
            Row row = sheet.createRow(rownum++);       
//            CellAddress firstCell = null;            
//            CellAddress lastCell = null;  
//            CellAddress valueCell = null;
            
            if(1 == criterias.size()) {
            	
            	fillScoreCell(criterias, row, styles, true);
            	
//            	for (int j = 0; j < criterias.size() + 3; j++) {
//            		
//            		Cell cell = row.createCell(j);
//            		
//            		if(j == 1) {
//            			valueCell = cell.getAddress();
//            		} 
//                	
//            		if(j == criterias.size() + 1) {
//                    	String ref = valueCell +":"+ valueCell;
//                    	cell.setCellFormula("SUM("+ref+")");
//                    	cell.setCellStyle(styles.get("formula"));
//                    }else if(j == criterias.size() + 2) {
//                    	String ref = valueCell +":"+ valueCell;
//                    	cell.setCellFormula("AVERAGE("+ref+")");
//                    	cell.setCellStyle(styles.get("formula"));                
//                    }else {
//                    	cell.setCellStyle(styles.get("cell"));  
//                    } 
//            		
//            	}
            	
            }else {
            	
            	fillScoreCell(criterias, row, styles, false);
            	
//            	for (int j = 0; j < criterias.size() + 3; j++) {
//            		
//                    Cell cell = row.createCell(j);                
//
//                    if(j == 1) {
//                    	firstCell = cell.getAddress();
//                    }else if(j == criterias.size()) {
//                    	lastCell = cell.getAddress();
//                    } 
//                    
//                    if(j == criterias.size() + 1) {
//                    	String ref = firstCell +":"+ lastCell;
//                    	cell.setCellFormula("SUM("+ref+")");
//                    	cell.setCellStyle(styles.get("formula"));
//                    }else if(j == criterias.size() + 2) {
//                    	String ref = firstCell +":"+ lastCell;
//                    	cell.setCellFormula("AVERAGE("+ref+")");
//                    	cell.setCellStyle(styles.get("formula"));                
//                    }else {
//                    	cell.setCellStyle(styles.get("cell"));  
//                    }  
//                    
//                }
            	
            }
            
        }        
        
        // Set Entry Name on cell
        for (int i = 0; i < entries.size(); i++) {
            Row row = sheet.getRow(2 + i);      
            row.getCell(0).setCellValue(entries.get(i).getEntryName()); 
        }
        
//        ----- Merge Category in Progress -----
        
        Cell firstMergeCell , lastMergeCell = null;
        
        int firstRowNum = 0;
        
        // Set Score per entry and criteria
        for (int i = 0; i < entries.size(); i++) {
        	String categoryFirstName = null;
        	String categoryNextName = null;
        	String categoryName = null;
        	
        	Row row = sheet.getRow(2 + i);    

        	categoryName =  categoryRepository.findByCategoryIdAndEvent_id(entries.get(i).getCategoryId(), event.getId()).getName();
        	
        	if(i == 0) {
        		categoryName = categoryFirstName = categoryNextName = categoryRepository.findByCategoryIdAndEvent_id(entries.get(i).getCategoryId(), event.getId()).getName();
        		firstRowNum = row.getRowNum();
        	}
        	
//        	----- Merge Category in Progress -----
        	
        	List<ScoreDTO> scores = scoreService.getScoreByEventIdAndEntryId(event.getId(), entries.get(i).getEntryId());
        	
        	for (int j = 0; j < criterias.size() + 2; j++) {        		
        		Cell cell = row.getCell(j); 
        		
        		if(j == 1) cell.setCellValue(categoryName);
        		
        		if(j > 1) {            		
            		CriteriaDTO criteria = criteriaService.getOne(criterias.get(j - 2).getCriteriaId());              		
            		double criteriaScore = 0;
            		
            		if(userId != 0) {
            			// Not working, comment out for the meantime
//            			ScoreDTO score = scoreService.getScoresByEventIdAndEntryIdAndJudgeIdAndCriteriaId(event.getId(), entries.get(i).getEntryId(), userId, criteria.getCriteriaId());            			            			

            			for(int x = 0; x < scores.size(); x++) {
            				
            				for(int y = 0; y < scores.get(x).getScores().size(); y++) {
                				if(scores.get(x).getScores().get(y).getCriteriaId() == criteria.getCriteriaId() && scores.get(x).getJudgeId() == userId) {
                					criteriaScore += scores.get(x).getScores().get(y).getScore();
                					break;
                				}
                			}
            				
            			}
            			
            		}else {
            			
            			for(int x = 0; x < scores.size(); x++) {
                			for(int y = 0; y < scores.get(x).getScores().size(); y++) {
                				if(scores.get(x).getScores().get(y).getCriteriaId() == criteria.getCriteriaId()) {
                					criteriaScore += scores.get(x).getScores().get(y).getScore();
                					break;
                				}
                			}
                		}

            		}
            		
            		cell.setCellValue(criteriaScore);    			
        		}        		
        	}        	
        }

        //finally set column widths, the width is measured in units of 1/256th of a character width
        sheet.setColumnWidth(0, 30*256); //30 characters wide 
        for (int i = 1; i < criterias.size() + 4; i++) {
            sheet.setColumnWidth(i, 20*256);  //6 characters wide 
        }
		
	}
	
	private void fillScoreCell (List<CriteriaDTO> criteriasParam, Row rowParam, Map<String, CellStyle> stylesParam, 
			boolean isSingleCriteria) {
		CellAddress firstCell = null;            
        CellAddress lastCell = null;  
        CellAddress valueCell = null;
		
		for (int j = 0; j < criteriasParam.size() + 4; j++) {
    		
            Cell cell = rowParam.createCell(j);   
            
            if(isSingleCriteria) {
        		if(j == 1) {
	    			valueCell = cell.getAddress();
	    		}
            }else {
            	if(j == 1) {
                	firstCell = cell.getAddress();
                }else if(j == criteriasParam.size()) {
                	lastCell = cell.getAddress();
                } 
            }
            
            if(j == criteriasParam.size() + 2) {
            	String ref = isSingleCriteria ? valueCell +":"+ valueCell : firstCell +":"+ lastCell;
            	cell.setCellFormula("SUM("+ref+")");
            	cell.setCellStyle(stylesParam.get("formula"));
            }else if(j == criteriasParam.size() + 3) {
            	String ref = isSingleCriteria ? valueCell +":"+ valueCell : firstCell +":"+ lastCell;
            	cell.setCellFormula("AVERAGE("+ref+")");
            	cell.setCellStyle(stylesParam.get("formula"));                
            }else {
            	cell.setCellStyle(stylesParam.get("cell"));  
            }  
            
        }
		
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
