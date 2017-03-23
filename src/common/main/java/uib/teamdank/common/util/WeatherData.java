package uib.teamdank.common.util;

/**
 * Imports the open source data from YR to provide the current weather situation
 * for every game. The weather is real time weather forecast.
 */
public class WeatherData {

	/**
	 * Represents different weather conditions.
	 */
	public static enum WeatherType {

	}

	/**
	 * Pulls the current weather conditions in the specified location and
	 * returns it as a {@link WeatherType}.
	 * 
	 * @param county
	 *            the county ("fylke" in Norwegian)
	 * @param municipality
	 *            the municipality ("kommune" in Norwegian)
	 * @param placeName
	 *            the place name ("stedsnavn" in Norwegian)
	 * @return the current weather type in the specified location
	 */
	public WeatherType pullWeather(String county, String municipality, String placeName) {
		return null;
	}
}
