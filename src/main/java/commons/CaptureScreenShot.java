package commons;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class CaptureScreenShot {
	private static final DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_mm_ss");

	public static String captureScreen(WebDriver driver, String screenName) throws IOException {

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);

		// String dest = System.getProperty("user.dir") +
		// "\\Test-ScreenShots\\"+screenName+".png";
		String dest = "./Test-ScreenShots/" + screenName + ".png";

		File target = new File(dest);
		FileUtils.copyFile(src, target);

		return dest;
	}

	public static String generateFileName(String method) {
		Date date = new Date();
		String fileName = method + "_" + dateFormat.format(date);
		return fileName;
	}

	public static String generateFileName(ITestResult result) {
		Date date = new Date();
		String fileName = result.getMethod().getMethodName() + "_" + dateFormat.format(date);
		return fileName;
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		 System.out.println("Destination1: " + destination);
		 String destination2 = "./FailedTestsScreenshots/"+screenshotName+dateName+".png";
		 System.out.println("Destination2:" + destination2);
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }
	
	
}

