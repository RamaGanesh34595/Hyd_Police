package util_Base;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Xls_Reader.Xls_Reader;

public class Base_Util {
	
	public static WebDriver driver;
	public static WebElement element;
	public static Xls_Reader xls = new Xls_Reader ((System.getProperty("user.dir")) +"\\src\\config_data\\Keyword_Driven.xlsx");
	
	public static Properties prop;
	
	public static int rowCount;
	public static int rowNum;
	public static String runMode;
	public static String testcaseName;
	public static String actionKeyword;
	public static String objectXpath;
	public static String testData;
	public static String result = null;
	
	// Window Handles
	public static String mainwindow;
	public static Set<String> s;
	public static Iterator<String> itr;
	
	public static String exceldata;
	public static String AppVal;
	public static String drAsstWin;
	public static WebDriver driverAssit;
	public static String drInst;
	public static WebElement sliderIframe = null ;
	
	// Extent Reports
	public static ExtentHtmlReporter htmlRporter;
	public static ExtentReports extent;
	
	public static ExtentTest test;
	 //= extent.createTest("Google Search test one", "This is a validate google statement");

	
}
