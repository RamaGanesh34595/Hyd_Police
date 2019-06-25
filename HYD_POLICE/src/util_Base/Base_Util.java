package util_Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Xls_Reader.Xls_Reader;

public class Base_Util {
	
	public static WebDriver driver;
	public static WebElement element;
	public static Xls_Reader xls = new Xls_Reader ((System.getProperty("user.dir")) +"\\src\\config_data\\Keyword_Driven.xlsx");
	public static int rowCount;
	public static int rowNum;
	public static String runMode;
	public static String testcaseName;
	public static String actionKeyword;
	public static String objectXpath;
	public static String testData;
	public static String result = null;
	
	public static String USERNAME = "//*[@id='username']";	
	public static String PASSWORD = "//*[@id=\'password\']";
	public static String LOGIN_BUTTON = "//*[@id=\'login-3\']/p[3]/input";
	
	public static String enterVale;
	public static String btnValue;
	
}
