package vix.digital.exercise.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PollerUtilTest {

	@Test
	void getStatusUnirest() {
		PollerUtil.setup();
		String url = "https://www.google.com";
		int statusCode = PollerUtil.getStatusUnirest(url);
		assertEquals(statusCode, 200);
	}
}