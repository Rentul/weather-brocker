package service.weather.condition;

import model.weather.Observation;
import view.weather.Condition;

public interface ConditionService {

    model.weather.Condition add(Condition conditionView, Observation observation);

    model.weather.Condition getById(Long id);
}
