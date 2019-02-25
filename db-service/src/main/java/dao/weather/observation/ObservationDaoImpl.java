package dao.weather.observation;

import model.weather.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ObservationDaoImpl implements ObservationDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Observation observation, Astronomy astronomy, Atmosphere atmosphere, Condition condition, Wind wind) {
        entityManager.persist(observation);
        entityManager.persist(astronomy);
        entityManager.persist(atmosphere);
        entityManager.persist(condition);
        entityManager.persist(wind);
        return "success";
    }

    @Override
    public Observation getObservationById(Long id) {
        return entityManager.find(Observation.class, id);
    }

    @Override
    public Astronomy getAstronomyById(Long id) {
        return entityManager.find(Astronomy.class, id);
    }

    @Override
    public Atmosphere getAtmosphereById(Long id) {
        return entityManager.find(Atmosphere.class, id);
    }

    @Override
    public Condition getConditionById(Long id) {
        return entityManager.find(Condition.class, id);
    }

    @Override
    public Wind getWindById(Long id) {
        return entityManager.find(Wind.class, id);
    }
}
