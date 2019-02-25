package dao.weather.observation;

import model.weather.Observation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ObservationDaoImpl implements ObservationDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Observation observation) {
        entityManager.persist(observation);
        return null;
    }

    @Override
    public Observation getById(Long id) {
        return entityManager.find(Observation.class, id);
    }
}
