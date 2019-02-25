package service.weather.wind;

import dao.weather.wind.WindDao;
import mapper.MapperFacade;
import model.weather.Observation;
import view.weather.Wind;

import javax.inject.Inject;

public class WindServiceImpl implements WindService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private WindDao dao;

    @Override
    public model.weather.Wind add(Wind windView, Observation observation) {

        model.weather.Wind wind = mapperFacade.map(windView, model.weather.Wind.class);
        wind.setObservation(observation);

        dao.add(wind);

        return wind;
    }

    @Override
    public model.weather.Wind getById(Long id) {
        return dao.getById(id);
    }
}
