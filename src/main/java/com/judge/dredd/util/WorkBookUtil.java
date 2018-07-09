package com.judge.dredd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.judge.dredd.dto.CategoryDTO;
import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDTO;
import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.service.CategoryService;
import com.judge.dredd.service.CriteriaService;
import com.judge.dredd.service.EntryService;
import com.judge.dredd.service.EventService;

@Service
@Transactional
public class WorkBookUtil {
	
	public static final String SHEET_EVENT="Event";
	public static final String SHEET_CATEGORY="Category";
	public static final String SHEET_CRITERIA="Criteria";
	public static final String SHEET_ENTRY="Entry";
	
	public static final String CELL_EVENT_NAME ="EVENT_NAME";
	public static final String CELL_CATEGORY_NAME ="CATEGORY_NAME";
	public static final String CELL_CRITERIA_NAME ="CRITERIA_NAME";
	
	public static final String CELL_ENTRY_NAME="ENTRY_NAME";
		
	DataFormatter dataFormatter = new DataFormatter();

	private Workbook workBook;
	
	private EventDTO eventDTO;
	
	private List<CategoryDTO> categoryDTOs;
	
	private List<CriteriaDTO> criteriaDTOs;
	
	private List<EntryDTO> entryDTOs;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CriteriaService criteriaService;
		
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private EntityManager entityManager;
	
	private void prepareEvent(){
		eventDTO = new EventDTO();
		Sheet sheet = workBook.getSheet(SHEET_EVENT);
		for (Row row: sheet) {
			Cell cell = row.getCell(row.getFirstCellNum());
			String cellValue = dataFormatter.formatCellValue(cell);
			if(!CELL_EVENT_NAME.equalsIgnoreCase(cellValue) && "" != cellValue){
				 populateEventByCellRow(row);
			}
        }
	}
	
	private void prepareCategory(long eventId){
		categoryDTOs = new ArrayList<>();
		Sheet sheet = workBook.getSheet(SHEET_CATEGORY);
		for(Row row : sheet){
			Cell cell = row.getCell(row.getFirstCellNum());
			String cellValue = dataFormatter.formatCellValue(cell);
			if(!CELL_CATEGORY_NAME.equalsIgnoreCase(cellValue) && "" != cellValue){
				populateCategoryByCellRow(row, eventId);
			}
		}
	}
	
	private void prepareCriteria(long eventId){
		criteriaDTOs = new ArrayList<>(); 
		Sheet sheet = workBook.getSheet(SHEET_CRITERIA);
		for(Row row : sheet){
			Cell cell = row.getCell(row.getFirstCellNum());
			String cellValue = dataFormatter.formatCellValue(cell);
			if(!CELL_CRITERIA_NAME.equalsIgnoreCase(cellValue) && "" != cellValue){
				populateCriteriaByCellRow(row, eventId);
			}
		}
	}
	
	private void prepareEntries(long eventId) throws Exception{
		entryDTOs = new ArrayList<>();
		Sheet sheet = workBook.getSheet(SHEET_ENTRY);
		for(Row row : sheet){
			Cell cell = row.getCell(row.getFirstCellNum());
			String cellValue = dataFormatter.formatCellValue(cell);
			System.out.println("CELL VALUE " + cellValue);
			if(!CELL_ENTRY_NAME.equalsIgnoreCase(cellValue) && "" != cellValue){
				populateEntryByCellRow(row, eventId);
			}
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
	
	public List<CategoryDTO> populateCategoryByCellRow(Row row, long eventId){
		CategoryDTO categoryDTO = new CategoryDTO();
		for(Cell cell: row) {
			if(null == categoryDTO.getCategoryName()){
				categoryDTO.setCategoryName(cellToString(cell));
			}else if (null == categoryDTO.getCategoryDescription()){
				categoryDTO.setCategoryDescription(cellToString(cell));
			}
		}
		categoryDTO.setEventId(eventId);
		categoryDTOs.add(categoryDTO);
		return categoryDTOs;
	}
	
	public List<CriteriaDTO> populateCriteriaByCellRow(Row row, long eventId){
		CriteriaDTO criteriaDTO = new CriteriaDTO();
		for(Cell cell: row){
			if(null == criteriaDTO.getCriteriaName()){
				criteriaDTO.setCriteriaName(cellToString(cell));
			}else if (null == criteriaDTO.getCriteriaDescription()){
				criteriaDTO.setCriteriaDescription(cellToString(cell));
			}else if (null == criteriaDTO.getMinValue()){
				criteriaDTO.setMinValue(cellToInteger(cell));
			}else if (null == criteriaDTO.getMaxValue()){
				criteriaDTO.setMaxValue(cellToInteger(cell));
			}
		}
		criteriaDTO.setEventId(eventId);
		criteriaDTOs.add(criteriaDTO);
		return criteriaDTOs;
	}
	
	public List<EntryDTO> populateEntryByCellRow(Row row, long eventId) throws Exception{
		EntryDTO entryDTO = new EntryDTO();
		
		String firstName = null;
		String lastName = null;
		String memberType = "Team Leader";
		
		for(Cell cell : row){
			String s = cellToString(cell);
			if(null == entryDTO.getEntryName()){
				entryDTO.setEntryName(s);
			}else if(null == entryDTO.getEntryDescription()){
				entryDTO.setEntryDescription(s);
			}else if(null == entryDTO.getCategoryId()){
				CategoryDTO cat = categoryDTOs.stream().filter(c -> c.getCategoryName().equalsIgnoreCase(s)).findFirst().orElseThrow(() -> new Exception("no category found: "+s));
				entryDTO.setCategoryId(cat.getCategoryId());
			}else{
				if(null == firstName){
					firstName = s;
				}else if(null == lastName){
					lastName = s;
				}
					
				if(null != firstName && null != lastName){
					MemberDTO m = new MemberDTO();
					m.setFirstName(firstName);
					m.setLastName(lastName);
					m.setMemberType(memberType);
					entryDTO.addMember(m);
					memberType = "Team Member";
					firstName = null;
					lastName = null;
				}
			}
		}		
		entryDTO.setEventId(eventId);
		entryDTOs.add(entryDTO);
		return entryDTOs;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doProcess(Workbook workBook) throws Exception{
		String eventId="eventId=";
		
		try{
			
			setWorkBook(workBook); 
			prepareEvent();
			eventDTO = eventService.addEvent(eventDTO);
			
			prepareCategory(eventDTO.getEventId());
			List<CategoryDTO> catDTOs = new ArrayList();
			for(CategoryDTO c : categoryDTOs){
				catDTOs.add(categoryService.addCategory(c));
			}
			categoryDTOs = catDTOs;
			
			prepareCriteria(eventDTO.getEventId());		
			List<CriteriaDTO> criDTOs = new ArrayList();
			for(CriteriaDTO c : criteriaDTOs){
				criDTOs.add(criteriaService.save(c));
			}
			criteriaDTOs = criDTOs;
			
			prepareEntries(eventDTO.getEventId());
			List<EntryDTO> entDTOs = new ArrayList();
			for(EntryDTO e : entryDTOs){
				entDTOs.add(entryService.addEntryWithMembers(e));
			}
			entryDTOs = entDTOs;
			
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally {
			workBook = null;
			eventDTO = null;
			categoryDTOs = null;
			criteriaDTOs = null;
			entryDTOs = null;
		}
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

	public List<CategoryDTO> getCategoryDTOs() {
		return categoryDTOs;
	}

	public void setCategoryDTOs(List<CategoryDTO> categoryDTOs) {
		this.categoryDTOs = categoryDTOs;
	}

	public List<CriteriaDTO> getCriteriaDTOs() {
		return criteriaDTOs;
	}

	public void setCriteriaDTOs(List<CriteriaDTO> criteriaDTOs) {
		this.criteriaDTOs = criteriaDTOs;
	}

	public List<EntryDTO> getEntryDTOs() {
		return entryDTOs;
	}

	public void setEntryDTOs(List<EntryDTO> entryDTOs) {
		this.entryDTOs = entryDTOs;
	}
	
	
}
