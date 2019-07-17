package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.Helpers;
import commons.LogType;
import commons.TestBase;
import pages.Header;
import pages.Home;
import pages.Rooms;
import pages.Footer;

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
	public void bookOverOneYear() throws InterruptedException {
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
	public void checkChatButton() throws InterruptedException {
		logReport(LogType.INFO, "Chat button test started.");
		SoftAssert chatWorks = new SoftAssert();
		footer.setFrameAndClickChat();
		chatWorks.assertEquals(footer.isChatOpen(), true);
		logReport(LogType.INFO, "Chat button functionality verrified.");
		chatWorks.assertAll();

	}

	@Test
	public void bookingRoomVerify() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		logReport(LogType.INFO, "Room booking test started");
		// SoftAssert outDateGreyed = new SoftAssert();
		// SoftAssert correctTimeFrame = new SoftAssert();
		Thread.sleep(4000);
		header.clickRooms();
		// Thread.sleep(2000);
		room.waitBodyFrame();
		room.waitRoomClickCheckIn();

		// calendar opens, calendar selection starts
		// Thread.sleep(1000);
		room.waitNextMonth();
		room.waitAndClickInDay("25");

		//// it moves automatically to checkout calendar selection
		//// We verify if the date 24 august is clickable
		// Thread.sleep(1000);
		softAssert.assertEquals(room.isOutDateClickable("24"), false);
		logReport(LogType.INFO, "Verification that the button is unclickable complete.");

		// verify color
		softAssert.assertEquals(room.isOutDateGreyed("24"), true);
		logReport(LogType.INFO, "Verification that the button greyed out is complete.");

		///// set check out to 31 aug
		room.waitAndClickOutDay("31");
		// Thread.sleep(1000);
		room.waitRoomIncreaseAdults();
		// Thread.sleep(1000);
		room.waitRoomSearch();

		// we verify the timeframe is correct
		softAssert.assertEquals(room.isCorrectTimeFrame(), true);
		logReport(LogType.INFO, "Verification of the timeframe is complete.");

		// click highest rate room
		room.roomHighestRate();
		room.waitRoomClickCheckOut();
		softAssert.assertEquals(room.isOutDateClickable("27"), false);
		logReport(LogType.INFO, "Verification that the outdate is unclickableon the desired room page is complete.");
		softAssert.assertEquals(room.isPriceRight(), true);
		logReport(LogType.INFO, "Verification of the total price is complete.");
		softAssert.assertAll();

	}

}
