package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.Helpers;
import commons.TestBase;
import pages.Header;
import pages.Home;
import pages.Rooms;

public class test extends TestBase {

	Home home;
	Rooms room;
	Header header;
	Helpers helper;

	@BeforeMethod
	public void elements() {
		header = new Header(driver);
		home = new Home(driver);
		room = new Rooms(driver);
		helper = new Helpers();
	}

//	@Test
//	public void bookOverOneYear() throws InterruptedException {
//		SoftAssert noOverbooking = new SoftAssert();
//		home.setFrameAndCheckin();
//		home.clickInDay(helper.azi());
//		for (int i = 0; i < 12; i++) {
//			home.nextMonth();
//		}
//		home.clickOutDay(helper.azi());
//		home.setFrameAndClickSearchBtn();
//		noOverbooking.assertEquals(room.isOverBook(), true);
//		noOverbooking.assertAll();
//
//	}
//
//	@Test
//	public void checkChatButton() throws InterruptedException {
//		SoftAssert chatWorks = new SoftAssert();
//		home.setFrameAndClickChat();
//		chatWorks.assertEquals(home.isChatOpen(), true);
//		chatWorks.assertAll();
//
//	}

	@Test
	public void bookingRoomVerify() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// SoftAssert outDateGreyed = new SoftAssert();
		// SoftAssert correctTimeFrame = new SoftAssert();
		Thread.sleep(4000);
		header.clickRooms();
		Thread.sleep(2000);
		room.bodyFrame();
		room.roomClickCheckIn();

		// calendar opens, calendar selection starts
		Thread.sleep(1000);
		room.nextMonth();
		room.clickInDay("25");

		//// it moves automatically to checkout calendar selection
		//// We verify if the date 24 august is clickable
		Thread.sleep(1000);
		softAssert.assertEquals(room.isOutDateClickable("24"), false);

		// verify color
		softAssert.assertEquals(room.isOutDateGreyed("24"), true);

		///// set check out to 31 aug
		room.clickOutDay("31");
		Thread.sleep(1000);
		room.roomIncreaseAdults();
		Thread.sleep(1000);
		room.roomSearch();

		// we verify the timeframe is correct
		softAssert.assertEquals(room.isCorrectTimeFrame(), true);

		// click highest rate room
		room.roomHighestRate();
		room.roomClickCheckOut();
		softAssert.assertEquals(room.isOutDateClickable("27"), false);
		softAssert.assertEquals(room.isPriceRight(), true);
		softAssert.assertAll();

	}

}
