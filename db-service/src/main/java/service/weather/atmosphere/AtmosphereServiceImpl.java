package service.weather.atmosphere;

import dao.weather.atmosphere.AtmosphereDao;
import mapper.MapperFacade;
import model.weather.Observation;
import view.weather.Atmosphere;

import javax.inject.Inject;

public class AtmosphereServiceImpl implements AtmosphereService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private AtmosphereDao dao;

    @Override
    public model.weather.Atmosphere add(Atmosphere atmosphereView, Observation observation) {

        model.weather.Atmosphere atmosphere = mapperFacade.map(atmosphereView, model.weather.Atmosphere.class);
        atmosphere.setObservation(observation);

        dao.add(atmosphere);

        return atmosphere;
    }

    @Override
    public model.weather.Atmosphere getById(Long id) {
        return dao.getById(id);
    }
}
