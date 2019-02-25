package dao.weather.wind;

import model.weather.Wind;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class WindDaoImpl implements WindDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Wind wind) {

        entityManager.persist(wind);

        return null;
    }

    @Override
    public Wind getById(Long id) {
        return entityManager.find(Wind.class, id);
    }
}
