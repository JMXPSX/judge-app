package com.judge.dredd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.judge.dredd.dto.CategoryDTO;
import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDTO;

public class WorkBookUtil {
	
	public static final String EVENT_NAME ="EVENT_NAME";
	public static final String EVENT_START ="Event name";
	public static final String EVENT_END ="Event name";
	
	public static final String CATEGORY_NAME ="Event name";
	public static final String CATEGORY_DESCRIPTION ="Event name";
	
	DataFormatter dataFormatter = new DataFormatter();

	private Workbook workBook;
	
	private EventDTO eventDTO;
	
	private List<CategoryDTO> categoryDTOs;
	
	private List<CriteriaDTO> criteriaDTOs;
	
	private List<EntryDTO> entryDTOs;
	
	public WorkBookUtil(Workbook workBook){
		this.workBook = workBook;
	}
	
	public void prepareEvent(){
		Sheet sheet = workBook.getSheet("Event");
		eventDTO = new EventDTO();
		for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
                if(EVENT_NAME.equalsIgnoreCase(cellValue)){
                	break;
                }else{
                	populateEventByCellRow(row);
                }
                
            }
            System.out.println();
        }
	}
	
	public String cellToString(Cell cell){
		return dataFormatter.formatCellValue(cell);
	}
	
	public int cellToInteger(Cell cell){
		return Integer.parseInt(cellToString(cell));
	}
	
	public Date cellToDate(Cell cell){
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dataFormatter.formatCellValue(cell));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EventDTO populateEventByCellRow(Row row){
		eventDTO = new EventDTO();
		
		for(Cell cell: row) {
			if(null == eventDTO.getEventName()){
				eventDTO.setEventName(cellToString(cell));
			}else if(null == eventDTO.getStartDate()){
				eventDTO.setStartDate(cellToDate(cell));
			}else if(null == eventDTO.getEndDate()){
				eventDTO.setEndDate(cellToDate(cell));
			}
		}
		
		return eventDTO;
	}
	
	public List<CategoryDTO> populateCategoryByCellRow(Row row){
		if(null == categoryDTOs){
			categoryDTOs = new ArrayList<>();
		}
		CategoryDTO categoryDTO = new CategoryDTO();
		for(Cell cell: row) {
			if(null == categoryDTO.getCategoryName()){
				categoryDTO.setCategoryName(cellToString(cell));
			}else if (null == categoryDTO.getCategoryDescription()){
				categoryDTO.setCategoryDescription(cellToString(cell));
			}
		}
		categoryDTOs.add(categoryDTO);
		return categoryDTOs;
	}
	
	public List<CriteriaDTO> populateCriteriaByCellRow(Row row){
		if(null == criteriaDTOs){
			criteriaDTOs = new ArrayList();
		}
		CriteriaDTO criteriaDTO = new CriteriaDTO();
		for(Cell cell: row){
			if(null == criteriaDTO.getCriteriaName()){
				criteriaDTO.setCriteriaName(cellToString(cell));
			}else if (null == criteriaDTO.getCriteriaDescription()){
				criteriaDTO.setCriteriaDescription(cellToString(cell));
			}else if (0 == criteriaDTO.getMinValue()){
				criteriaDTO.setMinValue(cellToInteger(cell));
			}else if (0 == criteriaDTO.getMaxValue()){
				criteriaDTO.setMaxValue(cellToInteger(cell));
			}
		}
		criteriaDTOs.add(criteriaDTO);
		return criteriaDTOs;
	}

	public Workbook getWorkBook() {
		return workBook;
	}

	public void setWorkBook(Workbook workBook) {
		this.workBook = workBook;
	}

	public EventDTO getEventDTO() {
		return eventDTO;
	}

	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}
	
	
}
