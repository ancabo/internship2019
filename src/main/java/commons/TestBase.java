package commons;

//import java.io.IOException;
import java.lang.reflect.Method;

//import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions; 
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

///////////////////////////////////////
// Driver Related Methods ////////////
/////////////////////////////////////

	@BeforeTest(alwaysRun = true)
	public void setUpDriver() {
		// instantza de driver
		// System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		// driver = new ChromeDriver();
		useBrowser();
		//System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		//driver= new InternetExplorerDriver();
		htmlReporter = new ExtentHtmlReporter("./FirstReport.html");
		htmlReporter.config().setDocumentTitle("First Test Automation Report");
		htmlReporter.config().setReportName("First Test Automation Report");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, Object[] parameters) {
		
		System.out.println("TEST STARTED: " + method.getName()); // + methodParams);
		test = extent.createTest(method.getName()); // + methodParams);
		if (driver != null)
			if (!method.getName().contains("orderTest"))
				navigateToURL("https://ancabota09.wixsite.com/intern");
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("EXECUTION FINISHED FOR: " + result.getName());
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("FAILED TEST: " + result.getName());
			System.out.println(result.getThrowable().toString());
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString(), ExtentColor.RED));
			test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = CaptureScreenShot.getScreenshot(driver, result.getName());
			// add it in the extent report
			test.addScreenCaptureFromPath(screenshotPath);
			if (driver != null) {
				driver.manage().deleteAllCookies();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("SKIPPED");
			test.log(Status.SKIP, MarkupHelper.createLabel("Skipped", ExtentColor.ORANGE));
		} else {
			System.out.println("PASSED TEST: " + result.getName());
			test.log(Status.PASS, MarkupHelper.createLabel("Passed", ExtentColor.GREEN));
		}
		extent.flush();
	}

	@AfterTest(alwaysRun = true)
	public void quitDriver() {
		if (driver != null)
			driver.quit();
	}

//////////////////////////////////
// Selenium Core Methodes/////////
//////////////////////////////////

	public void navigateToURL(String url) {
		driver.get(url);
	}

	public String getCrtURL() {
		return driver.getCurrentUrl();
	}

	public void click(WebElement element) throws InterruptedException {
		element.click();

	}

	public void parentFrame() throws InterruptedException {
		Thread.sleep(100);
		driver.switchTo().parentFrame();
	}

	public void changeFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}

	public void doubleClick(WebElement element, int width, int hight) throws InterruptedException {
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(element, width, hight).doubleClick().build().perform();
	}

	public void dropDownSelect(String selectValue, Select select) {
		select.selectByVisibleText(selectValue);
	}

	public boolean isCheckBoxSelected(WebElement cBox) {
		boolean flag = false;
		if (cBox.isSelected()) {
			flag = true;
			return flag;
		}
		return flag;
	}

	///// Report////
	public void logReport(String logType, String logDetails) {

		switch (logType) {
		case Constants.PASS:
			test.log(Status.PASS, logDetails);
			break;
		case Constants.FAIL:
			test.log(Status.FAIL, logDetails);
			break;
		case Constants.WARNING:
			test.log(Status.WARNING, logDetails);
			break;
		case Constants.ERROR:
			test.log(Status.ERROR, logDetails);
			break;
		case Constants.INFO:
			test.log(Status.INFO, logDetails);
			break;
		}
	}
	
	private void useBrowser() {
			switch (getProperty("browser")) {
			case "ie":
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.ignoreZoomSettings();
				options.introduceFlakinessByIgnoringSecurityDomains();
				options.setCapability("nativeEvents", false);
				options.setCapability("unexpectedAlertBehaviour", "accept");
				options.setCapability("ignoreProtectedModeSettings", true);
				options.setCapability("disable-popup-blocking", true);
				options.setCapability("enablePersistentHover", true);
				options.setCapability("default", true);
				driver = new InternetExplorerDriver(options);
				break;
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
				break;
			case "noProp":
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				options = new InternetExplorerOptions();
				options.ignoreZoomSettings();
				options.introduceFlakinessByIgnoringSecurityDomains();
				options.setCapability("nativeEvents", false);
				options.setCapability("unexpectedAlertBehaviour", "accept");
				options.setCapability("ignoreProtectedModeSettings", true);
				options.setCapability("disable-popup-blocking", true);
				options.setCapability("enablePersistentHover", true);
				options.setCapability("default", true);
				driver = new InternetExplorerDriver(options);
				break;
			}
			
	}

	private String getProperty(String property) {
		String value = System.getProperty(property);
		if (value == null) {
			value = "noProp";
		}
		return value;
	}
 

}