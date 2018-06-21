package com.judge.dredd.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

public class WorkBookUtilTest {
	
	private static String UPLOADED_FOLDER = "C:/Users/eldon.i.a.leuterio/Desktop/";
	private String fileName = "dredd_upload.xlsx";

	@Test
	public void TestLoadEvent() throws EncryptedDocumentException, InvalidFormatException, IOException{
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		File file = path.toFile();
		Workbook workBook = WorkbookFactory.create(file);
		
		WorkBookUtil w = new WorkBookUtil(workBook);
		w.prepareEvent();
		System.out.println(w.getEventDTO().getEventName());
		System.out.println(w.getEventDTO().getStartDate());
		System.out.println(w.getEventDTO().getEndDate());
	}
}
