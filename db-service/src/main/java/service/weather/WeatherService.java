package service.weather;

import view.weather.WeatherBroadcast;

/**
 * Сервис для работы с БД
 */
public interface WeatherService {

    /**
     * Добавить прогноз погоды
     *
     * @param weatherBroadcast прогноз погоды
     */
    void addWeatherBroadcast(WeatherBroadcast weatherBroadcast);

    /**
     * Получить прогноз погоды
     *
     * @param city название города
     * @return прогноз погоды
     */
    WeatherBroadcast getWeatherBroadcast(String city);
}
