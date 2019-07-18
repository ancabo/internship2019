package tests;


import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import commons.TestBase;
import commons.CaptureScreenShot;
import commons.DriverHelpers;
import commons.LogType;
import pages.Contact;
import pages.Rooms;
import pages.Header;
import pages.Footer;

public class ZoomInTest extends TestBase {

	Contact contact;
	DriverHelpers d_helper;
	Header header;
	Rooms room;
	Footer footer;

	@BeforeMethod
	public void elements() {
		contact = new Contact(driver);
		d_helper = new DriverHelpers();
		header = new Header(driver);
		room = new Rooms(driver);
		footer = new Footer(driver);
	}

	@Test(priority = 0)
	public void zoomIn() throws InterruptedException, IOException  {
		SoftAssert zoom = new SoftAssert();
		logReport(LogType.INFO, "Test1 started.");
		header.waitAndClickContact();
	//	contact.scrollDown(0, 3000);
		//Thread.sleep(2000);
		contact.waitAndChangeIframe();
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("Map isn't zoomed"));
		contact.doubleClickToZoomIn();
		zoom.assertEquals(contact.checkZoomIn(), true);
		logReport(LogType.INFO, "Zoom test finished.");
		zoom.assertAll();
		
		
	}
	@Test(priority = 1)
	public void iconFacebook() throws InterruptedException, IOException {
		SoftAssert sa = new SoftAssert();
		logReport(LogType.INFO, "test2 started");
		//header.waitAndClickContact();
		footer.waitAndClickFacebookIcon();
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("First tab is displayed."));
		d_helper.changeTab(1);
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("Second tab is displayed"));
		sa.assertEquals(contact.compareCrtUrlToOtherURL(getCrtURL()), true);
		logReport(LogType.INFO, "facebook checked");
		sa.assertAll();
	}

	@Test(priority = 2)
	public void booking() throws InterruptedException, IOException {
		SoftAssert outDay = new SoftAssert();
		logReport(LogType.INFO, "test3 started");
		header.waitAndClickRooms();
		room.waitBodyFrame();
		room.waitRoomClickCheckIn();
		room.waitNextMonth();
		room.clickInDay("25");
	
		outDay.assertEquals(room.isOutDateClickable("24"), false);
		logReport(LogType.INFO, "Click out date checked.");
	
		outDay.assertEquals(room.isOutDateGreyed("24"), true);
		
		room.waitAndClickOutDay("31");
		
		room.waitRoomIncreaseAdults();
		room.waitRoomSearch();
		
		outDay.assertEquals(room.isCorrectTimeFrame(), true);
		logReport(LogType.INFO, "Period booking is checked");
		

		room.waitRoomHighestRate();
		
		
		outDay.assertEquals(room.isOutDateClickable("25+2"), false);
		logReport(LogType.INFO, "out date checked.");
	
		
		outDay.assertEquals(room.isPriceRight(), true);
		logReport(LogType.INFO, "Price checked.");
		outDay.assertAll();
	}

}
