package dao.weather.condition;

import model.weather.Condition;

public interface ConditionDao {

    String add(Condition condition);

    Condition getById(Long id);
}
