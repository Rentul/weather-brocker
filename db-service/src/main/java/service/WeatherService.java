package service;

import view.weather.WeatherBroadcast;

public interface WeatherService {

    String addWeatherBroadcast(WeatherBroadcast weatherBroadcast);

    WeatherBroadcast getWeatherBroadcast(String city) throws Exception;
}
