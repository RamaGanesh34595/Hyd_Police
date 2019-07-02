package test_Cases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import business_Library.KeywordActions;

public class LoginAndLogout extends KeywordActions{

	public LoginAndLogout() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void LoginMethod() throws Exception {		
		KeywordActions a = KeywordActions.getActionInstance();
		a.executeKeyword("TestCase_001");
	}
	
	@BeforeTest
	public void beforeTest() {
		htmlRporter = new ExtentHtmlReporter((System.getProperty("user.dir")) + "\\ExtentReports\\extent_Reports" + timestamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlRporter);
		test  = extent.createTest("Hyderabad Police", "This is a validation page of Login Module ");
	}
	
	@AfterTest
	public void afterTest() {
		extent.flush();
	}
}
