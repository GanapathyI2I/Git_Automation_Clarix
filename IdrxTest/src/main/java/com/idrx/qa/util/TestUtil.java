package com.idrx.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.nio.file.Files;

import com.google.protobuf.Duration;
import com.idrx.qa.base.TestBase;

public class TestUtil extends TestBase {

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

	public static Map<String, Object> getExcelValue(String sheetName) throws Exception {

		if (sheet == null) {
			loadExcel(sheetName);
		}

		Map<String, Object> myMap = new HashMap<String, Object>();

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			row = sheet.getRow(i);
			Cell keyCell = row.getCell(0);
			String key = keyCell.getStringCellValue().trim();
			Cell valueCell = row.getCell(1);
			Object value = null;
			if (valueCell != null) {
				if (valueCell.getCellType() == 0) {
					value = valueCell.getNumericCellValue();
				} else if (valueCell.getCellType() == 1) {
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

	// -----this method is used to convert the number to short indian format with 2
	// decimal places--------
	public static String numberToShortIndianFormat(double num) {
		if (num >= 10000000) {
			double value = num / 10000000.0;
			String finalValueCr = String.format("%.2f Cr", value).replaceAll("\\.?0+Cr$", "Cr");
			System.out.println("finalValueCr: " + finalValueCr);
			return finalValueCr;
		} else if (num >= 100000) {
			double value = num / 100000.0;
			String finalValueL = String.format("%.2f L", value).replaceAll("\\.?0+L$", "L");
			System.out.println("finalValueL: " + finalValueL);
			return finalValueL;
		} else if (num >= 1000) {
			double value = num / 1000.0;
			String finalValueK = String.format("%.2f k", value).replaceAll("\\.?0+k$", "k");
			System.out.println("finalValueK: " + finalValueK);
			return finalValueK;
		} else {
			return String.valueOf(num);
		}

	}

	// -----this method is used to convert the number to short indian format with 1
	// decimal place--------
	public static String numberToShortIndianFormatOneDecimal(double num) {
		if (num >= 10000000) {
			double value = num / 10000000.0;
			String finalValueCr = String.format("%.1f Cr", value).replaceAll("\\.?0+Cr$", "Cr");
			System.out.println("finalValueCr: " + finalValueCr);
			return finalValueCr;
		} else if (num >= 100000) {
			double value = num / 100000.0;
			String finalValueL = String.format("%.1f L", value).replaceAll("\\.?0+L$", "L");
			System.out.println("finalValueL: " + finalValueL);
			return finalValueL;
		} else if (num >= 1000) {
			double value = num / 1000.0;
			String finalValueK = String.format("%.1f k", value).replaceAll("\\.?0+k$", "k");
			System.out.println("finalValueK: " + finalValueK);
			return finalValueK;
		} else {
			return String.valueOf(num);
		}

	}

	// -----this method is used to convert the number to short indian format with 1
	// decimal place in Cr--------
	public static String numberToShortIndianFormatInCr(double num) {
		double value = num / 10000000.0;
		String finalValueCr = String.format("%.1f Cr", value).replaceAll("\\.?0+ Cr$", " Cr");
		return finalValueCr;
	}

	// -----this method is used to convert the number to short indian format with 1
	// decimal place in L--------
	public static String numberToShortIndianFormatInLakh(double num) {
		double value = num / 100000.0;
		String finalValueL = String.format("%.1f L", value).replaceAll("\\.?0+ L$", " L");
		return finalValueL;
	}

	// -----Scroll to particular element-----
	public static void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static String takeScreenshot(WebDriver driver, String screenshotName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = "test-output/screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
			File dest = new File(path);
			dest.getParentFile().mkdirs(); // Ensure directory exists
			Files.copy(src.toPath(), dest.toPath());
			return dest.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Scrolls down the page to the bottom using JavaScriptExecutor.
	 * 
	 * @param driver The WebDriver instance.
	 */
	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	/**
	 * Scrolls down the page by a specific number of pixels using
	 * JavaScriptExecutor.
	 * 
	 * @param driver The WebDriver instance.
	 * @param pixels The number of pixels to scroll down.
	 */
	public static void scrollDownBy(WebDriver driver, int pixels) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", pixels);
	}

	public static String numberToShortIndianFormatThreeDigits(double num) {
		if (num >= 10000000) {
			double value = num / 10000000.0;
			String finalValueCr = String.format("%.1f Cr", value).replaceAll("\\.?0+Cr$", "Cr");
			System.out.println("finalValueCr: " + finalValueCr);
			return finalValueCr;
		} else if (num >= 100000) {
			double value = num / 100000.0;
			String finalValueL = String.format("%.1f L", value).replaceAll("\\.?0+L$", "L");
			System.out.println("finalValueL: " + finalValueL);
			return finalValueL;
		} else if (num >= 1000) {
			double value = num / 1000.0;
			String finalValueK = String.format("%.1f k", value).replaceAll("\\.?0+k$", "k");
			System.out.println("finalValueK: " + finalValueK);
			return finalValueK;
		} else {
			return String.valueOf(num);
		}
	}

	public static String numberToShortIndianFormatTwoDecimal(double num) {
		if (num >= 1_00_00_000) { // 1 crore
			BigDecimal value = BigDecimal.valueOf(num / 1_00_00_000.0)
					.setScale(2, RoundingMode.CEILING);
			return value + "Cr";
		} else if (num >= 1_00_000) { // 1 lakh
			BigDecimal value = BigDecimal.valueOf(num / 1_00_000.0)
					.setScale(2, RoundingMode.CEILING);
			return value + "L";
		} else if (num >= 1000) { // 1 thousand
			BigDecimal value = BigDecimal.valueOf(num / 1000.0)
					.setScale(2, RoundingMode.CEILING);
			return value + "K";
		} else {
			BigDecimal value = BigDecimal.valueOf(num)
					.setScale(2, RoundingMode.CEILING);
			return value.toString();
		}
	}

	public static String numberToShortIndianFormatTwoDecimalRoundOff(double num) {
		if (num >= 1_00_00_000) { // 1 crore
			BigDecimal value = BigDecimal.valueOf(num / 1_00_00_000.0)
					.setScale(2, RoundingMode.HALF_UP);
			return value + "Cr";
		} else if (num >= 1_00_000) { // 1 lakh
			BigDecimal value = BigDecimal.valueOf(num / 1_00_000.0)
					.setScale(2, RoundingMode.HALF_UP);
			return value + "L";
		} else if (num >= 1000) { // 1 thousand
			BigDecimal value = BigDecimal.valueOf(num / 1000.0)
					.setScale(2, RoundingMode.HALF_UP);
			return value + "K";
		} else {
			BigDecimal value = BigDecimal.valueOf(num)
					.setScale(2, RoundingMode.HALF_UP);
			return value.toString();
		}
	}
}
