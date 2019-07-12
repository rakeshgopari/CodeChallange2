package com.challange2.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.challange2.qa.util.TestUtil;


public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	
	
	
	public TestBase(){
		
		try{
			prop = new Properties();
			//FileInputStream  ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
			FileInputStream  ip = new FileInputStream("C://Users//AF44976//EclipseWorkspace//CodeChallange2//src//main//java//com//challange2//qa//config//config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException exceptionFNF){
			exceptionFNF.printStackTrace();
		}
		catch(IOException exceptionIO){
			exceptionIO.printStackTrace();
		}
	}
	
	
	public static void initialization(){
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecho.driver", "C:/drivers/gechodriver.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", "C:/drivers/iedriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	

	
}
