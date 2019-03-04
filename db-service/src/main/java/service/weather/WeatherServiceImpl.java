package service.weather;

import dao.weather.WeatherDao;
import mapper.MapperFacade;
import model.weather.Astronomy;
import model.weather.Atmosphere;
import model.weather.Condition;
import model.weather.WeatherBroadcast;
import model.weather.Forecast;
import model.weather.Location;
import model.weather.Observation;
import model.weather.Wind;
import view.weather.CurrentObservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherDao dao;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    @Transactional
    public void addWeatherBroadcast(final view.weather.WeatherBroadcast weatherBroadcastView) {

        final WeatherBroadcast weatherBroadcast = mapperFacade.map(
                weatherBroadcastView,
                WeatherBroadcast.class);

        Location location = mapperFacade.map(
                weatherBroadcastView.getLocation(),
                Location.class);

        final List<Forecast> forecasts = mapperFacade.mapAsList(
                weatherBroadcastView.getForecasts(),
                Forecast.class);

        final Observation observation = mapperFacade.map(
                weatherBroadcastView.getCurrentObservation(),
                Observation.class);

        final Astronomy astronomy = mapperFacade.map(
                weatherBroadcastView.getCurrentObservation().getAstronomy(),
                Astronomy.class);

        final Atmosphere atmosphere = mapperFacade.map(
                weatherBroadcastView.getCurrentObservation().getAtmosphere(),
                Atmosphere.class);

        final Condition condition = mapperFacade.map(
                weatherBroadcastView.getCurrentObservation().getCondition(),
                Condition.class);

        final Wind wind = mapperFacade.map(
                weatherBroadcastView.getCurrentObservation().getWind(),
                Wind.class);

        final Location locationFromDb = dao.getLocationById(location.getWoeid());

        if (locationFromDb == null) {
            dao.addLocation(location);
        } else {
            location = locationFromDb;
        }

        location.addWeatherBroadcast(weatherBroadcast);

        dao.addBroadcast(weatherBroadcast);

        weatherBroadcast.setForecasts(forecasts);

        dao.addForecastList(forecasts);

        observation.setWeatherBroadcast(weatherBroadcast);

        dao.addObservation(observation);

        astronomy.setObservation(observation);

        dao.addAstronomy(astronomy);

        atmosphere.setObservation(observation);

        dao.addAtmosphere(atmosphere);

        condition.setObservation(observation);

        dao.addCondition(condition);

        wind.setObservation(observation);

        dao.addWind(wind);
    }

    @Override
    @Transactional
    public view.weather.WeatherBroadcast getWeatherBroadcast(final String city) throws Exception {

        final Location location = dao.getLocationByCity(city);
        final WeatherBroadcast weatherBroadcast = location.getLatestWeatherBroadcast();
        final Observation observation = dao.getObservationById(weatherBroadcast.getId());
        final Astronomy astronomy = dao.getAstronomyById(weatherBroadcast.getId());
        final Atmosphere atmosphere = dao.getAtmosphereById(weatherBroadcast.getId());
        final Condition condition = dao.getConditionById(weatherBroadcast.getId());
        final Wind wind = dao.getWindById(weatherBroadcast.getId());

        final view.weather.WeatherBroadcast weatherBroadcastView = new view.weather.WeatherBroadcast();

        final CurrentObservation observationView = mapperFacade.map(
                observation,
                CurrentObservation.class);

        final view.weather.Astronomy astronomyView = mapperFacade.map(
                astronomy,
                view.weather.Astronomy.class);

        final view.weather.Atmosphere atmosphereView = mapperFacade.map(
                atmosphere,
                view.weather.Atmosphere.class);

        final view.weather.Condition conditionView = mapperFacade.map(
                condition,
                view.weather.Condition.class);

        final view.weather.Wind windView = mapperFacade.map(
                wind,
                view.weather.Wind.class);

        final List<view.weather.Forecast> forecastViewList = mapperFacade.mapAsList(
                weatherBroadcast.getForecasts(),
                view.weather.Forecast.class);

        final view.weather.Location locationView = mapperFacade.map(
                location,
                view.weather.Location.class);

        observationView.setAstronomy(astronomyView);
        observationView.setAtmosphere(atmosphereView);
        observationView.setCondition(conditionView);
        observationView.setWind(windView);

        weatherBroadcastView.setCurrentObservation(observationView);
        weatherBroadcastView.setForecasts(forecastViewList);
        weatherBroadcastView.setLocation(locationView);

        return weatherBroadcastView;
    }

}
