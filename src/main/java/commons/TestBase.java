package commons;

//import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		htmlReporter = new ExtentHtmlReporter("./FirstReport.html");
		htmlReporter.config().setDocumentTitle("First Test Automation Report");
		htmlReporter.config().setReportName("First Test Automation Report");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, Object[] parameters) {

//		String methodParams ="";

		// default IE browser
		// useBrowser();
//        if(parameters.length != 0){
//               if(method.getName().contains("AdHoc")){
//                      methodParams = " " + parameters[0] + " ";
//               }else{
//                      methodParams = " " + parameters[0] + " " + parameters[1] + " ";
//               }
//        }            
		System.out.println("TEST STARTED: " + method.getName()); // + methodParams);
		test = extent.createTest(method.getName()); // + methodParams);
		if (driver != null) {

			navigateToURL("https://ancabota09.wixsite.com/intern");
			driver.manage().window().maximize();
		}

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("EXECUTION FINISHED FOR: " + result.getName());
//		String screenShot;
//        if(driver != null){
//        screenShot = CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName(result));
//        }else{
//               screenShot = null;
//        }            

		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("FAILED TEST: " + result.getName());
			System.out.println(result.getThrowable().toString());
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable().toString(), ExtentColor.RED));
			test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = CaptureScreenShot.getScreenshot(driver, result.getName());
			// add it in the extent report
			test.addScreenCaptureFromPath(screenshotPath);
			// test.addScreenCaptureFromPath(screenShot);

//        if(driver != null){
//        driver.manage().deleteAllCookies();
//        }
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("SKIPPED");
			test.log(Status.SKIP, MarkupHelper.createLabel("Skipped", ExtentColor.ORANGE));
		} else {
			System.out.println("PASSED TEST: " + result.getName());
			test.log(Status.PASS, MarkupHelper.createLabel("Passed", ExtentColor.GREEN));
		}

		extent.flush();
	}

//	//public void getResult(ITestResult result) throws Exception{
//		 if(result.getStatus() == ITestResult.FAILURE) {
//		 test.log(Status.FAIL, "Test Case Failed is "+result.getName());
//		 test.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
//		 String screenshotPath = CaptureScreenShot.getScreenshot(driver, result.getName());
//		 // add it in the extent report 
//		 test.log(Status.FAIL, (Markup) test.addScreenCaptureFromPath(screenshotPath));
//		 }
//		 else 
//			 System.out.println("PASSED TEST: " + result.getName());
//		
//		 }

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

	public void parentFrame() {
		driver.switchTo().parentFrame();
	}

	public void changeFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}

	public void doubleClick(WebElement element, int x, int y) throws InterruptedException {
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		act.moveToElement(element, x, y).doubleClick().build().perform();
	}

	public void scrollDown(int a, int b) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("window.scrollTo" + a + b);

	}

	///// Report////
	public void logReport(String logType, String logDetails) {

		switch (logType) {
		case LogType.PASS:
			test.log(Status.PASS, logDetails);
			break;
		case LogType.FAIL:
			test.log(Status.FAIL, logDetails);
			break;
		case LogType.WARNING:
			test.log(Status.WARNING, logDetails);
			break;
		case LogType.ERROR:
			test.log(Status.ERROR, logDetails);
			break;
		case LogType.INFO:
			test.log(Status.INFO, logDetails);
			break;
		}
	}
}