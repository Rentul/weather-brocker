package service;

import mapper.MapperFacade;
import model.weather.Forecast;
import model.weather.Location;
import model.weather.Observation;
import model.weather.WeatherBroadcast;
import service.weather.forecast.ForecastService;
import service.weather.location.LocationService;
import service.weather.observation.ObservationService;
import service.weather.weatherBroadcast.WeatherBroadcastService;
import view.weather.CurrentObservation;

import javax.inject.Inject;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {

    @Inject
    private LocationService locationService;

    @Inject
    private WeatherBroadcastService weatherBroadcastService;

    @Inject
    private ForecastService forecastService;

    @Inject
    private ObservationService observationService;

    @Inject
    private MapperFacade mapperFacade;

    @Override
    public String addWeatherBroadcast(final view.weather.WeatherBroadcast weatherBroadcastView) {

        final WeatherBroadcast weatherBroadcast = weatherBroadcastService.add(weatherBroadcastView);

        Location location = locationService.get(weatherBroadcastView.getLocation());
        if (location == null) {
            location = locationService.add(weatherBroadcastView.getLocation());
        }
        location.addWeatherBroadcast(weatherBroadcast);

        final List<Forecast> forecasts = forecastService.addForecastList(weatherBroadcastView.getForecasts());
        weatherBroadcast.setForecasts(forecasts);

        final Observation observation = observationService.add(
                weatherBroadcastView.getCurrentObservation(),
                weatherBroadcast);

        return "success";
    }

    @Override
    public view.weather.WeatherBroadcast getWeatherBroadcast(final String city) throws Exception {

        final view.weather.WeatherBroadcast weatherBroadcastView = new view.weather.WeatherBroadcast();

        final Location location = locationService.getByCity(city);

        final WeatherBroadcast weatherBroadcast = location.getLatestWeatherBroadcast();

        final CurrentObservation observationView = observationService.getById(weatherBroadcast.getId());

        final List<view.weather.Forecast> forecastViewList = mapperFacade.mapAsList(
                weatherBroadcast.getForecasts(),
                view.weather.Forecast.class);

        final view.weather.Location locationView = mapperFacade.map(
                location,
                view.weather.Location.class);

        weatherBroadcastView.setCurrentObservation(observationView);
        weatherBroadcastView.setForecasts(forecastViewList);
        weatherBroadcastView.setLocation(locationView);

        return weatherBroadcastView;
    }

}
