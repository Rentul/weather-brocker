package service;

import mapper.MapperFacade;
import model.weather.Forecast;
import model.weather.Location;
import model.weather.Observation;
import model.weather.WeatherBroadcast;
import service.weather.astronomy.AstronomyService;
import service.weather.atmosphere.AtmosphereService;
import service.weather.condition.ConditionService;
import service.weather.forecast.ForecastService;
import service.weather.location.LocationService;
import service.weather.observation.ObservationService;
import service.weather.weatherBroadcast.WeatherBroadcastService;
import service.weather.wind.WindService;
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
    private AstronomyService astronomyService;

    @Inject
    private AtmosphereService atmosphereService;

    @Inject
    private ConditionService conditionService;

    @Inject
    private WindService windService;

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

        astronomyService.add(weatherBroadcastView.getCurrentObservation().getAstronomy(), observation);
        atmosphereService.add(weatherBroadcastView.getCurrentObservation().getAtmosphere(), observation);
        conditionService.add(weatherBroadcastView.getCurrentObservation().getCondition(), observation);
        windService.add(weatherBroadcastView.getCurrentObservation().getWind(), observation);

        return "success";
    }

    @Override
    public view.weather.WeatherBroadcast getWeatherBroadcast(final String city) throws Exception {

        final view.weather.WeatherBroadcast weatherBroadcastView = new view.weather.WeatherBroadcast();

        final Location location = locationService.getByCity(city);

        final WeatherBroadcast weatherBroadcast = location.getLatestWeatherBroadcast();

        final CurrentObservation observationView = mapperFacade.map(
                observationService.getById(weatherBroadcast.getId()),
                CurrentObservation.class);

        final view.weather.Astronomy astronomyView = mapperFacade.map(
                astronomyService.getById(weatherBroadcast.getId()),
                view.weather.Astronomy.class);

        final view.weather.Atmosphere atmosphereView = mapperFacade.map(
                atmosphereService.getById(weatherBroadcast.getId()),
                view.weather.Atmosphere.class);

        final view.weather.Condition conditionView = mapperFacade.map(
                conditionService.getById(weatherBroadcast.getId()),
                view.weather.Condition.class);

        final view.weather.Wind windView = mapperFacade.map(
                windService.getById(weatherBroadcast.getId()),
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
