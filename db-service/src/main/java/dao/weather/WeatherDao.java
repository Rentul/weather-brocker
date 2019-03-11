package dao.weather;

import model.weather.Astronomy;
import model.weather.Atmosphere;
import model.weather.Condition;
import model.weather.WeatherBroadcast;
import model.weather.Forecast;
import model.weather.Location;
import model.weather.Observation;
import model.weather.Wind;

import java.util.List;

/**
 * Работа с БД
 */
public interface WeatherDao {

    /**
     * Добавить астрономию
     *
     * @param astronomy астрономия
     */
    void addAstronomy(Astronomy astronomy);

    /**
     * Добавить атмосферу
     *
     * @param atmosphere атмосфера
     */
    void addAtmosphere(Atmosphere atmosphere);

    /**
     * Добавить прогноз погоды
     *
     * @param weatherBroadcast прогноз погоды
     */
    void addBroadcast(WeatherBroadcast weatherBroadcast);

    /**
     * Добавить состояние
     *
     * @param condition состояние
     */
    void addCondition(Condition condition);

    /**
     * Добавить прогнозы
     *
     * @param forecasts прогнозы
     */
    void addForecastList(List<Forecast> forecasts);

    /**
     * Добавить местонахождение
     *
     * @param location местонахождение
     */
    void addLocation(Location location);

    /**
     * Добавить наблюдения
     *
     * @param observation наблюдения
     */
    void addObservation(Observation observation);

    /**
     * Добавить ветер
     *
     * @param wind ветер
     */
    void addWind(Wind wind);

    /**
     * Получить астрономию
     *
     * @param id id
     * @return астрономия
     */
    Astronomy getAstronomyById(Long id);

    /**
     * Получить атмосферу
     *
     * @param id id
     * @return атмосфера
     */
    Atmosphere getAtmosphereById(Long id);

    /**
     * Получить состояние
     *
     * @param id id
     * @return состояние
     */
    Condition getConditionById(Long id);

    /**
     * Получить местонахождение
     *
     * @param id id
     * @return местонахождение
     */
    Location getLocationById(Long id);

    /**
     * Получить город
     *
     * @param city название города
     * @return город
     */
    Location getLocationByCity(String city);

    /**
     * Получить наблюдения
     *
     * @param id id
     * @return наблюдения
     */
    Observation getObservationById(Long id);

    /**
     * Получить ветер
     *
     * @param id id
     * @return ветер
     */
    Wind getWindById(Long id);
}
