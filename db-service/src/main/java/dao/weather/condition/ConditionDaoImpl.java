package dao.weather.condition;

import model.weather.Condition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ConditionDaoImpl implements ConditionDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Condition condition) {
        entityManager.persist(condition);
        return null;
    }

    @Override
    public Condition getById(Long id) {
        return entityManager.find(Condition.class, id);
    }
}
