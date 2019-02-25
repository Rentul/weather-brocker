package dao.weather.astronomy;

import model.weather.Astronomy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AstronomyDaoImpl implements AstronomyDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Astronomy astronomy) {
        entityManager.persist(astronomy);
        return null;
    }

    @Override
    public Astronomy getById(final Long id) {
        return entityManager.find(Astronomy.class, id);
    }
}
