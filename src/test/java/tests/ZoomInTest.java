package tests;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
//import commons.CaptureScreenShot;
import commons.DriverHelpers;
import commons.Helpers;
import commons.Constants;
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
	Helpers helper;

	@BeforeMethod
	public void elements() {
		
		contact = new Contact(driver);
		d_helper = new DriverHelpers();
		header = new Header(driver);
		room = new Rooms(driver);
		footer = new Footer(driver);
		helper = new Helpers();
	}

	@Test(priority = 0)
	public void zoomIn() throws InterruptedException, IOException {
		SoftAssert zoom = new SoftAssert();
		logReport(Constants.INFO, "Test1 started.");
		header.waitAndClickContact();
		contact.waitAndChangeIframe();
		contact.doubleClickToZoomIn();
		zoom.assertEquals(contact.checkZoomIn(), true);
		logReport(Constants.INFO, "Zoom test finished.");
		zoom.assertAll();

	}

	@Test(priority = 1)
	public void iconFacebook() throws InterruptedException, IOException {
		SoftAssert sa = new SoftAssert();
		logReport(Constants.INFO, "test2 started");
		footer.waitAndClickFacebookIcon();
		d_helper.changeTab(1);
		sa.assertEquals(contact.compareCrtUrlToOtherURL(getCrtURL()), true);
		logReport(Constants.INFO, "facebook checked");
		sa.assertAll();
	}

	@Test(priority = 2)
	public void booking() throws InterruptedException, IOException {
		int i = 0;
		int tests = 2;
		SoftAssert outDay = new SoftAssert();
		logReport(Constants.INFO, "test3 started");
		while (tests != 0) {
			tests--;
			header.waitAndClickRooms();
			room.waitBodyFrame();
			room.waitRoomClickCheckIn();
			room.waitNextMonth();
			room.clickInDay(helper.readFromAFile(i));
			
			outDay.assertEquals(room.isOutDateClickable(helper.readFromAFile(i + 1)), false);
			logReport(Constants.INFO, "Click out date checked.");
			
			outDay.assertEquals(room.isOutDateGreyed(helper.readFromAFile(i + 1)), true);
			
			room.waitAndClickOutDay(helper.readFromAFile(i + 2));
			room.waitRoomIncreaseAdults();
			room.waitRoomSearch();
			
			outDay.assertEquals(room.isCorrectTimeFrameOneMonth(helper.readFromAFile(i), helper.readFromAFile(i + 2)),true);
			logReport(Constants.INFO, "Period booking is checked");
			room.waitRoomHighestRate();
			Thread.sleep(1500);
			room.waitRoomClickCheckOut();
			
			outDay.assertEquals(room.isOutDateClickable(helper.readFromAFile(i + 3)), false);
			logReport(Constants.INFO, "out date checked.");
			
			outDay.assertEquals(room.isPriceRight(), true);
			logReport(Constants.INFO, "Price checked.");
			i = i + 4;
			if (tests != 0) 
			navigateToURL("https://ancabota09.wixsite.com/intern");
			outDay.assertAll();
		}
	}

}
