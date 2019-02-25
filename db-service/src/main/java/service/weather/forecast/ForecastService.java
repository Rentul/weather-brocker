package service.weather.forecast;

import view.weather.Forecast;

import java.util.List;

public interface ForecastService {

    model.weather.Forecast add(Forecast forecastView);

    List<model.weather.Forecast> addForecastList(List<Forecast> forecastViews);
}
