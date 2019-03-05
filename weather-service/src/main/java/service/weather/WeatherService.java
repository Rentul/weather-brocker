package service.weather;

import view.weather.WeatherBroadcast;

public interface WeatherService {

    void addWeatherBroadcast(WeatherBroadcast weatherBroadcast);

    WeatherBroadcast getWeatherBroadcast(String city);
}
