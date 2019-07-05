package test_Cases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import business_Library.KeywordActions;

public class Add_User extends KeywordActions {

	public Add_User() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void LoginMethod() throws Exception {		
		KeywordActions a = KeywordActions.getActionInstance();
		a.executeKeyword("TestCase_002");
	}
	
	@BeforeTest
	public void beforeTest() {
		htmlRporter = new ExtentHtmlReporter((System.getProperty("user.dir")) + "\\ExtentReports\\SeleniumExtentReport_" + timestamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlRporter);
		test  = extent.createTest("Google Search test one", "This is a validate google statement");
	}
	
	@AfterTest
	public void afterTest() {
		extent.flush();
	}

}
