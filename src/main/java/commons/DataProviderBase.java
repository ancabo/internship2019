package commons;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderBase {
	@DataProvider(name = "DateProviderA")
	public static Object[][] getDatesProvider(Method met) {
		return new Object[][] { { "25", "31" }, { "12", "23" }, { "15", "26" } };
	}

	@DataProvider(name = "DateProviderS")
	public static Object[][] getDatesProvider() {
		return new Object[][] { { "25", "24", "31", "27" }, { "14", "13", "23", "16" } };
	}
}
