package com.idrx.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.idrx.qa.util.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static Properties dbProp;
	public final static Logger logger = LogManager.getLogger(TestUtil.class);

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/idrx/qa/config/config.properties");
			prop.load(ip);
			dbProp = new Properties();
			FileInputStream ip1 = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/idrx/qa/config/DBUtilConfig.properties");
			dbProp.load(ip1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		logger.info("🌐 Launching URL: {}", prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		logger.info("✅ URL launched successfully");
	}
}
