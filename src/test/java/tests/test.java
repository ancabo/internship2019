package tests;

import java.io.IOException;
import java.util.ArrayList;

//import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.CaptureScreenShot;
import commons.Helpers;
import commons.LogType;
import commons.TestBase;
import pages.Header;
import pages.Home;
import pages.Rooms;
import pages.Footer;

@Listeners(commons.ListenersTest.class)

public class test extends TestBase {

	Home home;
	Rooms room;
	Header header;
	Helpers helper;
	Footer footer;

	@BeforeMethod
	public void elements() {
		header = new Header(driver);
		home = new Home(driver);
		room = new Rooms(driver);
		helper = new Helpers();
		footer = new Footer(driver);
	}

	@Test
	public void bookOverOneYear() throws InterruptedException, IOException {
		logReport(LogType.INFO, "Booking test started started.");
		SoftAssert noOverbooking = new SoftAssert();
		home.waitFrameAndCheckIn();
		home.waitAndClickInDay(helper.azi());
		for (int i = 0; i < 12; i++) {
			home.waitAndNextMonth();
		}
		home.waitAndClickOutDay(helper.azi());
		home.waitFrameAndClickSearchBtn();
		noOverbooking.assertEquals(room.isOverBook(), true);
		logReport(LogType.INFO, "Booking test ended.");
		noOverbooking.assertAll();

	}

	@Test
	public void checkChatButton() throws InterruptedException, IOException {
		logReport(LogType.INFO, "Chat button test started.");
		SoftAssert chatWorks = new SoftAssert();
		footer.setFrameAndClickChat();
		Thread.sleep(1000);
		chatWorks.assertEquals(footer.isChatOpen(), true);
		CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName("chat window"));
		chatWorks.assertAll();

	}

	@Test
	public void bookingRoomVerify() throws InterruptedException, IOException {
		int tests = 3;
		int index = 0;
		ArrayList<String> checkersInOut = new ArrayList<String>();
		helper.addExcelToList(System.getProperty("user.dir"), "bookerDate_test.xls", checkersInOut);

		SoftAssert softAssert = new SoftAssert();
		logReport(LogType.INFO, "Room booking test started");

		// SoftAssert outDateGreyed = new SoftAssert();
		// SoftAssert correctTimeFrame = new SoftAssert();
		while (tests != 0) {
			tests--;
			Thread.sleep(1000);
			header.waitAndClickRooms();
			//Thread.sleep(1000);
			room.bodyFrame();
			room.waitRoomClickCheckIn();

			// calendar opens, calendar selection starts
			// Thread.sleep(1000);
			room.waitNextMonth();
			room.waitAndClickInDay(room.checkInXL(index, checkersInOut));

			//// it moves automatically to checkout calendar selection
			//// We verify if the date 24 august is clickable
			// Thread.sleep(1000);
			softAssert.assertEquals(room.isOutDateClickable(room.preCheckInXL(1, index, checkersInOut)), false);
			logReport(LogType.INFO, "Verification that the button is unclickable complete.");

			// verify color
			softAssert.assertEquals(room.isOutDateGreyed(room.preCheckInXL(1, index, checkersInOut)), true);
			logReport(LogType.INFO, "Verification that the button greyed out is complete.");

			///// set check out to 31 aug
			room.waitAndClickOutDay(room.checkOutXL(index + 1, checkersInOut));
			// Thread.sleep(1000);
			room.waitRoomIncreaseAdults();
			room.waitRoomSearch();

			// we verify the timeframe is correct
			softAssert.assertEquals(room.isCorrectTimeFrameOneMonth(room.checkInXL(index, checkersInOut),
					room.checkOutXL(index + 1, checkersInOut)), true);
			logReport(LogType.INFO, "Verification of the timeframe is complete.");

			// click highest rate room
			room.waitRoomHighestRate();
			Thread.sleep(1500);
			room.waitRoomClickCheckOut();

			softAssert.assertEquals(room.isOutDateClickable(room.preCheckOutXL(2, index, checkersInOut)), false);
			logReport(LogType.INFO,
					"Verification that the outdate is unclickable on the desired room page is complete.");

			softAssert.assertEquals(room.isPriceRight(), true);
			logReport(LogType.INFO, "Verification of the total price is complete.");

			index = index + 2;
			if (tests != 0)
				navigateToURL("https://ancabota09.wixsite.com/intern");
			softAssert.assertAll();
		}
	}

}
