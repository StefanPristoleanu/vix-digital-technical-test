package vix.digital.exercise.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class PollerUtil {

	private static final int TIMEOUT = 4000;

	public static void setup() {
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger("org.apache.http");
		root.setLevel(ch.qos.logback.classic.Level.INFO);
		Unirest.config()
			.socketTimeout(TIMEOUT)
			.connectTimeout(TIMEOUT)
			.verifySsl(false);
	}

	public static int getStatusUnirest(String urlAddress) {
		try {
			return Unirest.get(urlAddress)
				.asString().getStatus();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return 404;
	}
}
