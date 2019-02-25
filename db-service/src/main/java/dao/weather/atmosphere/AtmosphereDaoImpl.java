package dao.weather.atmosphere;

import model.weather.Atmosphere;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AtmosphereDaoImpl implements AtmosphereDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Atmosphere atmosphere) {
        entityManager.persist(atmosphere);
        return null;
    }

    @Override
    public Atmosphere getById(Long id) {
        return entityManager.find(Atmosphere.class, id);
    }
}
