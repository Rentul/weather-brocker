package service.weather.wind;

import model.weather.Observation;
import view.weather.Wind;

public interface WindService {

    model.weather.Wind add(Wind windView, Observation observation);

    model.weather.Wind getById(Long id);
}
