package com.interview.questions.airport;

public class AirportFactory {
	public static Airport createFactory(String airport) {
		if ("SFO".equals(airport)) {
			return new SanFranciscoAirport();
		}
		return null;
	}
}
