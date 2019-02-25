package service.weather.observation;

import dao.weather.observation.ObservationDao;
import mapper.MapperFacade;
import model.weather.*;
import view.weather.CurrentObservation;

import javax.inject.Inject;

public class ObservationServiceImpl implements ObservationService{

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private ObservationDao dao;

    @Override
    public Observation add(final CurrentObservation observationView, final WeatherBroadcast weatherBroadcast) {

        final Observation observation = mapperFacade.map(observationView, Observation.class);
        final Astronomy astronomy = mapperFacade.map(observationView.getAstronomy(), Astronomy.class);
        final Atmosphere atmosphere = mapperFacade.map(observationView.getAtmosphere(), Atmosphere.class);
        final Condition condition = mapperFacade.map(observationView.getCondition(), Condition.class);
        final Wind wind = mapperFacade.map(observationView.getWind(), Wind.class);

        observation.setWeatherBroadcast(weatherBroadcast);
        astronomy.setObservation(observation);
        atmosphere.setObservation(observation);
        condition.setObservation(observation);
        wind.setObservation(observation);

        dao.add(observation, astronomy, atmosphere, condition, wind);

        return observation;
    }

    @Override
    public CurrentObservation getById(Long id) {

        final Observation observation = dao.getObservationById(id);
        final Astronomy astronomy = dao.getAstronomyById(id);
        final Atmosphere atmosphere = dao.getAtmosphereById(id);
        final Condition condition = dao.getConditionById(id);
        final Wind wind = dao.getWindById(id);

        final CurrentObservation observationView = mapperFacade.map(
                observation,
                CurrentObservation.class);

        final view.weather.Astronomy astronomyView = mapperFacade.map(
                astronomy,
                view.weather.Astronomy.class);

        final view.weather.Atmosphere atmosphereView = mapperFacade.map(
                atmosphere,
                view.weather.Atmosphere.class);

        final view.weather.Condition conditionView = mapperFacade.map(
                condition,
                view.weather.Condition.class);

        final view.weather.Wind windView = mapperFacade.map(
                wind,
                view.weather.Wind.class);

        observationView.setAstronomy(astronomyView);
        observationView.setAtmosphere(atmosphereView);
        observationView.setCondition(conditionView);
        observationView.setWind(windView);

        return observationView;
    }
}
