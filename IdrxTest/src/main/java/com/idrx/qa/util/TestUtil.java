package com.idrx.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.idrx.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static final String EXCELFILELOCATION = "./src/main/java/Automation data.xlsx";
	public static FileInputStream fis;
	public static Workbook workbook;
	public static Sheet sheet;
	public static Row row;
	public static DataFormatter dataformatter;
	public final Logger logger = LogManager.getLogger(TestUtil.class);
	
	public static void switchToFrame() {		
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
	}
	
	public static void loadExcel(String sheetName) throws Exception {		  
		
		try {
			fis = new FileInputStream(EXCELFILELOCATION);
			 workbook = new XSSFWorkbook(fis);
			 sheet = workbook.getSheet(sheetName);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public static Map<String, Object> getExcelValue(String sheetName) throws Exception{
		
		if(sheet == null) {
			loadExcel(sheetName);
		}
		
		Map<String, Object> myMap = new HashMap<String, Object>();
		
		for(int i = 1; i<sheet.getLastRowNum() + 1; i++) {
			row = sheet.getRow(i);
			Cell keyCell = row.getCell(0);
			String key = keyCell.getStringCellValue().trim();			
			Cell valueCell = row.getCell(1);
			Object value = null;			
			if(valueCell != null) {
				if(valueCell.getCellType() == 0) {
					value = valueCell.getNumericCellValue();					
				} else if(valueCell.getCellType() == 1) {
					value = valueCell.getStringCellValue();
				}
			}							
			myMap.put(key, value);			
		}
		return myMap;
	}
	
	public static Object getValue(String key, String sheetName) throws Exception {
		Map<String, Object> value = getExcelValue(sheetName);
		Object getValueByKey = value.get(key);
		return getValueByKey;
	}
	
	 public <T> void assertEquals(T actual, T expected, String message) {
		 logger.info("Asserting values. Expected: [{}], Actual: [{}]", expected, actual);
		 Assert.assertEquals(actual, expected, message);		 
		 logger.info("Assertion passed: {}", message);		 
	 }
	 
	 public void sendKeys(WebElement element, String value, String description) {
	        try {
	            logger.info("Entering Value in {} → '{}'", description, value);
	            element.clear();
	            element.sendKeys(value);
	            logger.info("✅ Entered Value successfully in: {}", description);
	        } catch (Exception e) {
	            logger.error("❌ Failed to type in: {} - {}", description, e.getMessage());
	            throw e;
	        }
	 }
	 
	 public void clickElement(WebElement element, String description) {
	        try {
	            logger.info("Clicking on: {}", description);
	            element.click();
	            logger.info("✅ Clicked successfully on: {}", description);
	        } catch (Exception e) {
	            logger.error("❌ Failed to click on: {} - {}", description, e.getMessage());
	            throw e;
	        }
	}
	 
	 public void launchURL(WebDriver driver, String url) {
	        logger.info("🌐 Launching URL: {}", url);
	        driver.get(url);
	        logger.info("✅ URL launched successfully");
	 }

}
