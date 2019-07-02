
package business_Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import test_Cases.extent_Reports;
import util_Base.Base_Util;

public class KeywordActions extends Base_Util {

	public static KeywordActions Action;

	public static void executeKeyword(String testName) {
		try {
			rowCount = xls.getRowCount("TestSteps");
			for (rowNum = 2; rowNum <= rowCount; rowNum++) {

				testcaseName = xls.getCellData("TestSteps", "TestCase_ID", rowNum);
				if (testName.equalsIgnoreCase(testcaseName)) {

					runMode = xls.getCellData("TestSteps", "Run_Mode", rowNum);
					if (runMode.equalsIgnoreCase("Y")) {
						actionKeyword = xls.getCellData("TestSteps", "Action_Keyword", rowNum);
						objectXpath = xls.getCellData("TestSteps", "Object_Xpath", rowNum);
						testData = xls.getCellData("TestSteps", "Test_Data", rowNum);

						if (actionKeyword.equalsIgnoreCase("openBrowser"))
							result = openBrowser(testData);

						if (actionKeyword.equalsIgnoreCase("navigateUrl"))
							result = navigateUrl(testData);

						if (actionKeyword.equalsIgnoreCase("enterText"))
							result = enterText(objectXpath, testData);

						if (actionKeyword.equalsIgnoreCase("clickButton"))
							result = clickButton(objectXpath);

						if (actionKeyword.equalsIgnoreCase("selectItem"))
							result = selectItem(objectXpath, testData);

						if (actionKeyword.equalsIgnoreCase("mouseHover"))
							result = mouseHover(objectXpath);

						if (actionKeyword.equalsIgnoreCase("clickLink"))
							result = clickLink(objectXpath);

						if (actionKeyword.equalsIgnoreCase("waitTime"))
							result = waitTime(testData);

						if (actionKeyword.equalsIgnoreCase("closeBrower"))
							result = closeBrower();
						
						xls.setCellData("TestSteps", "Results", rowNum, result);
						test.log(Status.PASS, result);
						
						
					} else {
						xls.setCellData("TestSteps", "Results", rowNum, "SKIP");
						test.log(Status.PASS, "SKIP");
					}

				}
			}

		} catch (Exception a) {
			a.printStackTrace();
		}

	}

	public KeywordActions() throws IOException {
		prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "\\src\\config_Data\\Object_Xpath.properties");
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
	}

	public static KeywordActions getActionInstance() throws Exception {

		if (Action == null) {
			Action = new KeywordActions();
		}
		return Action;
	}

	// Launch browser
	public static String openBrowser(String Browser) {

		try {
			if (Browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\config_Drivers\\chromedriver.exe");
				driver = new ChromeDriver();			

			} else if (Browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\config_Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();				

			} else if (Browser.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "\\config_Drivers\\operadriver.exe");
				driver = new OperaDriver();
			}

			driver.manage().window().maximize();
			System.out.println("User is able to open a browser");
			return "PASS - User is able to open " + Browser + " browser";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User is not able to open a browser");	
			return "FAIL - User is able to open " + Browser + " browser";
		}
	}

	// Navigate url
	public static String navigateUrl(String url) {
		try {
			driver.get(url);
			System.out.println("User is able to launch the application");
			return "PASS";
		} catch (Exception a) {
			a.printStackTrace();
			System.out.println("User is not able to launch the application");
			return "FAIL";
		}

	}

	public static String newTab() {
		try {
			WebElement body = driver.findElement(By.tagName("body"));
			body.sendKeys(Keys.CONTROL + "t");
			exceldata = "PASS ";
			return exceldata;
		} catch (Exception e) {
			exceldata = "FAIL";
			return exceldata;

		}
	}

	public static String switchWindow(String app) {
		try {
			if (app.equalsIgnoreCase("Assist")) {
				AppVal = "Assist";
				drAsstWin = driverAssit.getWindowHandle();
				driverAssit.switchTo().window(drAsstWin);
				System.out.println("Assist window");
			} else {
				AppVal = "Visitor";
				drInst = driver.getWindowHandle();
				driver.switchTo().window(drInst);
				System.out.println("Visitor window");
			}
			exceldata = "PASS" + AppVal;
			return exceldata;

		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--User is not able to switch to window" + AppVal;
			return exceldata;
		}
	}

	// taking screen shots:
	public static String takeScreenShot(String val) {
		try {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File((System.getProperty("user.dir")) + "\\test-output\\ScreenShots\\SS_" + val + ".png"));
			exceldata = "PASS--Screen shot saved to ScreenShots folder";
			return exceldata;
		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--User is not able to take a screen shot";
			return exceldata;
		}
	}

	public static String alertHandle() {
		try {
			Alert alert = driverAssit.switchTo().alert();
			alert.accept();
			exceldata = "PASS--User accepted the alert";
			return exceldata;
		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--Alert window is not closed";
			return exceldata;
		}
	}

	public static String iFrameBtnclick(String xobjpath) {
		try {
			while (true) {
				element = driverAssit.findElement(By.xpath(prop.getProperty(xobjpath)));
				if (element != null) {
					break;
				}
			}
			WebDriverWait waitt = new WebDriverWait(driverAssit, 80);
			waitt.until(ExpectedConditions.visibilityOf(element));
			element.click();
			exceldata = "PASS--User clicked";
			return exceldata;
		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--User is not able to click";
			return exceldata;
		}
	}

	public static String iFrameSwitch(String xobjpath, String app) {
		try {
			if (app.equalsIgnoreCase("assist")) {
				Thread.sleep(4000);
				WebElement ele = driverAssit.findElement(By.xpath(prop.getProperty(xobjpath)));
				System.out.println("Element is " + ele);
				System.out.println(ele.isEnabled());
				driverAssit.switchTo().frame(ele);
			} else {
				driver.switchTo().frame(driver.findElement(By.xpath(prop.getProperty(xobjpath))));
			}
			exceldata = "PASS--User switched to iFrame";
			return exceldata;
		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--User not able to switch to iFrame";
			return exceldata;
		}
	}

	public static String iFrameAlertclick(String xobjpath) {
		try {
			driverAssit.switchTo().frame(sliderIframe);
			while (true) {
				element = driverAssit.findElement(By.xpath(prop.getProperty(xobjpath)));
				if (element != null) {
					break;
				}
			}
			element.click();
			exceldata = "PASS--User closed alert popup";
			return exceldata;
		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--User is not able to close alert popup";
			return exceldata;
		}
	}

	public static String tabHandling() {
		try {
			ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
			System.out.println("Num of Tabs:::" + tabs.size());
			driver.switchTo().window(tabs.get(1));

			exceldata = "PASS--sfd";
			return exceldata;

		} catch (Exception e) {
			e.printStackTrace();
			exceldata = "FAIL--User is not able to switch to window";
			return exceldata;
		}
	}

	// Enter text value
	public static String enterText(String xpathValue, String enterVale) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(xpathValue)));
			element.sendKeys(enterVale);
			System.out.println("User is able to enter " + enterVale + " value");
			return "PASS";
		} catch (Exception d) {
			d.printStackTrace();
			System.out.println("User is not able to enter " + enterVale + " value");
			return "FAIL";
		}
	}

	// Click button
	public static String clickButton(String btnValue) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(btnValue)));
			element.click();
			System.out.println("User is able to click on button");
			return "PASS";
		} catch (Exception h) {
			h.printStackTrace();
			System.out.println("User is not able to click on button");
			return "FAIL";
		}
	}

	// Click Link
	public static String clickLink(String LinkValue) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(LinkValue)));
			element.click();
			System.out.println("User is able to click on Link");
			return "PASS";
		} catch (Exception h) {
			h.printStackTrace();
			System.out.println("User is not able to click on Link");
			return "FAIL";
		}
	}

	// Select an item from the dropdowns
	public static String selectItem(String xpathValue, String selectValue) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(xpathValue)));
			Select select = new Select(element);
			select.selectByVisibleText(prop.getProperty(selectValue));
			return "PASS";
		} catch (Exception k) {
			k.printStackTrace();
			return "FAIL";
		}
	}

	// MouseHover
	public static String mouseHover(String MenuLink) {
		try {
			element = driver.findElement(By.xpath(prop.getProperty(MenuLink)));
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();

			// driver.findElement(By.xpath(LinkName)).click();
			System.out.println("User is able to MouseHover on the link");
			return "PASS";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User is not able to MouseHover on the link");
			return "FAIL";
		}
	}

	// Wait Time
	public static String waitTime(String waitSeconds) throws Exception {
		try {
			int waitTime = Integer.parseInt(waitSeconds);
			Thread.sleep(waitTime);
			return "PASS";
		} catch (Exception e) {
			e.printStackTrace();
			return "FAIL";
		}
	}

	// Window Handles
	public void windowHandles() throws Exception {

		driver.findElement(By.xpath("Website")).click();
		Thread.sleep(5000);
		mainwindow = driver.getWindowHandle();
		s = driver.getWindowHandles();
		itr = s.iterator();
		while (itr.hasNext()) {
			String childwindow = itr.next();
			if (!mainwindow.equalsIgnoreCase(childwindow)) {
				driver.switchTo().window(childwindow);
			}

		}
		WebElement element = driver.findElement(By.linkText("History"));
		Assert.assertEquals(element.isDisplayed(), true);
		element.click();
		driver.close();
		driver.switchTo().window(mainwindow);
		driver.findElement(By.linkText("Website")).click();

	}

	// Close browser
	public static String closeBrower() {
		try {
			driver.close();
			// driver.quit();
			System.out.println("User is able to close the browser");
			return "PASS";
		} catch (Exception h) {
			h.printStackTrace();
			System.out.println("User is not able to close the browser");
			return "FAIL";
		}
	}

}
