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
	public static String Browser;
	/*public static String url;
	public static String xpathValue;
	public static String enterVale;
	public static String btnValue;*/
	
}
