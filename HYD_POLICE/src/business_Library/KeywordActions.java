
package business_Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
					}
				else {
					xls.setCellData("TestSteps", "Results", rowNum, "SKIP");
				}
				
				}
			}

		} catch (Exception a) {
			a.printStackTrace();
		}

	}

	
	public KeywordActions() throws IOException {
		prop = new Properties();
		File file = new File("C:\\Users\\User\\git\\repository\\HYD_POLICE\\src\\config_Data\\Object_Xpath.properties");
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
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\User\\git\\repository\\HYD_POLICE\\config_Drivers\\chromedriver.exe");
				driver = new ChromeDriver();

			} else if (Browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"D:\\Automation_WorkSpace\\HYD_POLICE\\config_Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (Browser.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver",
						"D:\\Automation_WorkSpace\\HYD_POLICE\\config_Drivers\\operadriver.exe");
				driver = new OperaDriver();
			}

			driver.manage().window().maximize();
			System.out.println("User is able to open a browser");
			return "PASS";

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("User is not able to open a browser");
			return "FAIL";
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
			String childwindow=itr.next();
			if (!mainwindow.equalsIgnoreCase(childwindow)) {
				driver.switchTo().window(childwindow);
			}

		}
		WebElement element=driver.findElement(By.linkText("History"));
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
			//driver.quit();
			System.out.println("User is able to close the browser");
			return "PASS";
		} catch (Exception h) {
			h.printStackTrace();
			System.out.println("User is not able to close the browser");
			return "FAIL";
		}
	}

}
