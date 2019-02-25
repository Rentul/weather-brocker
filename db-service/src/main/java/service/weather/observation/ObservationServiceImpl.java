package service.weather.observation;

import dao.weather.observation.ObservationDao;
import mapper.MapperFacade;
import model.weather.Observation;
import model.weather.WeatherBroadcast;
import view.weather.CurrentObservation;

import javax.inject.Inject;

public class ObservationServiceImpl implements ObservationService{

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private ObservationDao dao;

    @Override
    public Observation add(CurrentObservation observationView, WeatherBroadcast weatherBroadcast) {

        Observation observation = mapperFacade.map(observationView, Observation.class);
        observation.setWeatherBroadcast(weatherBroadcast);

        dao.add(observation);

        return observation;
    }

    @Override
    public Observation getById(Long id) {
        return dao.getById(id);
    }
}
