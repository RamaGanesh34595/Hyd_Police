package test_Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import util_Base.Base_Util;

public class extent_Reports extends Base_Util{
	public static WebDriver driver;

	public static void main(String[] args) {
		htmlRporter = new ExtentHtmlReporter("extent_Reports.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlRporter);
		
		ExtentTest test  = extent.createTest("Google Search test one", "This is a validate google statement");
		
		// Launching URL
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\User\\git\\repository\\HYD_POLICE\\config_Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		test.log(Status.INFO, "Starting of testcase");
		driver.get("https://www.google.com/");
		test.pass("URL Has opened");
		
		driver.findElement(By.name("q")).sendKeys("Selenium Automation");
		test.pass("Data has entered");
		
		driver.findElement(By.name("btnK")).sendKeys(Keys.RETURN);;
		test.pass("Clciked on Google Search button");
		
		test.pass("Test Passed");
		driver.close();
		test.pass("Test Completed");
		
		extent.flush();
	}

}
