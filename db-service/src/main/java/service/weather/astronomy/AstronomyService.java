package service.weather.astronomy;

import model.weather.Observation;
import view.weather.Astronomy;

public interface AstronomyService {

    model.weather.Astronomy add(Astronomy astronomyView, Observation observation);

    model.weather.Astronomy getById(Long id);
}
