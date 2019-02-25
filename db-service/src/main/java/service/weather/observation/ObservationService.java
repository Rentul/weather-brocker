package service.weather.observation;

import model.weather.Observation;
import model.weather.WeatherBroadcast;
import view.weather.CurrentObservation;

public interface ObservationService {

    Observation add(CurrentObservation observationView, WeatherBroadcast weatherBroadcast);

    Observation getById(Long id);
}
