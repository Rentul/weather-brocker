package service.weather;

import view.weather.WeatherBroadcast;

/**
 * Сервис для работы с БД
 */
public interface WeatherService {

    /**
     * Добавление прогноза погоды
     *
     * @param weatherBroadcast прогноз погоды
     */
    void addWeatherBroadcast(WeatherBroadcast weatherBroadcast);

    /**
     * Получение прогноза погоды по названию города
     *
     * @param city название города
     * @return прогноз погоды
     */
    WeatherBroadcast getWeatherBroadcast(String city);
}
