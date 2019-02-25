package service.weather.astronomy;

import dao.weather.astronomy.AstronomyDao;
import mapper.MapperFacade;
import model.weather.Observation;
import view.weather.Astronomy;

import javax.inject.Inject;

public class AstronomyServiceImpl implements AstronomyService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private AstronomyDao dao;

    @Override
    public model.weather.Astronomy add(Astronomy astronomyView, Observation observation) {

        model.weather.Astronomy astronomy = mapperFacade.map(astronomyView, model.weather.Astronomy.class);
        astronomy.setObservation(observation);

        dao.add(astronomy);

        return astronomy;
    }

    @Override
    public model.weather.Astronomy getById(final Long id) {

        return dao.getById(id);
    }
}
