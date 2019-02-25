package service.weather.forecast;

import dao.weather.forecast.ForecastDao;
import mapper.MapperFacade;
import view.weather.Forecast;

import javax.inject.Inject;
import java.util.List;

public class ForecastServiceImpl implements ForecastService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private ForecastDao dao;

    @Override
    public model.weather.Forecast add(Forecast forecastView) {

        model.weather.Forecast forecast = mapperFacade.map(forecastView, model.weather.Forecast.class);

        dao.add(forecast);

        return forecast;
    }

    @Override
    public List<model.weather.Forecast> addForecastList(List<Forecast> forecastViews) {

        List<model.weather.Forecast> forecasts = mapperFacade.mapAsList(forecastViews, model.weather.Forecast.class);

        dao.addList(forecasts);

        return forecasts;
    }
}
