package dao.weather;

import model.weather.*;

import java.util.List;

public interface WeatherDao {

    void addAstronomy(Astronomy astronomy);

    void addAtmosphere(Atmosphere atmosphere);

    void addBroadcast(WeatherBroadcast weatherBroadcast);

    void addCondition(Condition condition);

    void addForecastList(List<Forecast> forecasts);

    void addLocation(Location location);

    void addObservation(Observation observation);

    void addWind(Wind wind);

    Astronomy getAstronomyById(Long id);

    Atmosphere getAtmosphereById(Long id);

    Condition getConditionById(Long id);

    Location getLocationById(Long id);

    Location getLocationByCity(String city);

    Observation getObservationById(Long id);

    Wind getWindById(Long id);
}
