package dao.weather.forecast;

import model.weather.Forecast;

import java.util.List;

public interface ForecastDao {

    String add(Forecast forecast);

    String addList(List<Forecast> forecasts);
}
