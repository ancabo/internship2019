package commons;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class TestBase {
	public static WebDriver driver;

///////////////////////////////////////
// Driver Related Methods ////////////
/////////////////////////////////////

	@BeforeTest(alwaysRun = true)
	public void setUpDriver() {
		// instantza de driver
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, Object[] parameters) {

		System.out.println("TEST STARTED: " + method.getName());
		if (driver != null) {
			// navigateToURL(propsMap.get("APP_URL"));
			navigateToURL("https://ancabota09.wixsite.com/intern");
			driver.manage().window().maximize();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws IOException {
		System.out.println("EXECUTION FINISHED FOR: " + result.getName());
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

	public void click(WebElement element) throws InterruptedException {
		element.click();
		Thread.sleep(1300);
	}

	public void parentFrame() {
		driver.switchTo().parentFrame();
	}

	public void changeFrame(WebElement frame) {
		driver.switchTo().frame(frame);
	}
}