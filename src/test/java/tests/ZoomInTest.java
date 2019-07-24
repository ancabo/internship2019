package tests;

import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
import commons.CaptureScreenShot;
import commons.DriverHelpers;
import commons.Helpers;
import commons.LogType;
import pages.Contact;
import pages.Rooms;
import pages.Header;
import pages.Footer;

@Listeners(commons.ListenersTest.class)

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
		logReport(LogType.INFO, "Test1 started.");
		header.waitAndClickContact();
		// contact.scrollDown(0, 3000);
		// Thread.sleep(2000);
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
		// header.waitAndClickContact();
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
		// helper.readFromAFile();
		room.clickInDay(helper.readFromAFile(0));

		outDay.assertEquals(room.isOutDateClickable(helper.readFromAFile(1)), false);
		logReport(LogType.INFO, "Click out date checked.");

		outDay.assertEquals(room.isOutDateGreyed(helper.readFromAFile(1)), true);

		room.waitAndClickOutDay(helper.readFromAFile(2));

		room.waitRoomIncreaseAdults();
		room.waitRoomSearch();

		outDay.assertEquals(room.isCorrectTimeFrameOneMonth(helper.readFromAFile(0), helper.readFromAFile(2)), true);
		logReport(LogType.INFO, "Period booking is checked");

		room.waitRoomHighestRate();
		Thread.sleep(1500);
		room.waitRoomClickCheckOut();

		outDay.assertEquals(room.isOutDateClickable(helper.readFromAFile(3)), false);
		logReport(LogType.INFO, "out date checked.");

		outDay.assertEquals(room.isPriceRight(), true);
		logReport(LogType.INFO, "Price checked.");
		outDay.assertAll();
	}

}
