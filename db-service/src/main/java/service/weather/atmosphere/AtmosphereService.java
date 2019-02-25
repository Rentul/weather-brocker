package service.weather.atmosphere;

import model.weather.Observation;
import view.weather.Atmosphere;

public interface AtmosphereService {

    model.weather.Atmosphere add(Atmosphere atmosphereView, Observation observation);

    model.weather.Atmosphere getById(Long id);
}
