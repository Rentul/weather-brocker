package service.weather.condition;

import dao.weather.condition.ConditionDao;
import mapper.MapperFacade;
import model.weather.Observation;
import view.weather.Condition;

import javax.inject.Inject;

public class ConditionServiceImpl implements ConditionService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private ConditionDao dao;

    @Override
    public model.weather.Condition add(Condition conditionView, Observation observation) {

        model.weather.Condition condition = mapperFacade.map(conditionView, model.weather.Condition.class);
        condition.setObservation(observation);

        dao.add(condition);

        return condition;
    }

    @Override
    public model.weather.Condition getById(Long id) {
        return dao.getById(id);
    }
}
