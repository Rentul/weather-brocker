package dao.weather;

import model.weather.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class WeatherDaoImpl implements WeatherDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public void addAstronomy(Astronomy astronomy) {
        entityManager.persist(astronomy);
    }

    @Override
    public void addAtmosphere(Atmosphere atmosphere) {
        entityManager.persist(atmosphere);
    }

    @Override
    public void addBroadcast(WeatherBroadcast weatherBroadcast) {
        entityManager.persist(weatherBroadcast);
    }

    @Override
    public void addCondition(Condition condition) {
        entityManager.persist(condition);
    }

    @Override
    public void addForecastList(List<Forecast> forecasts) {
        for (Forecast forecast : forecasts) {
            entityManager.persist(forecast);
        }
    }

    @Override
    public void addLocation(Location location) {
        entityManager.persist(location);
    }

    @Override
    public void addObservation(Observation observation) {
        entityManager.persist(observation);
    }

    @Override
    public void addWind(Wind wind) {
        entityManager.persist(wind);
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
    public Location getLocationByCity(String city) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        final Root<Location> locationRoot = criteriaQuery.from(Location.class);
        final Predicate namePredicate = criteriaBuilder.equal(locationRoot.get("city"), city);
        criteriaQuery.where(namePredicate);
        criteriaQuery.select(locationRoot);
        final TypedQuery<Location> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public Location getLocationById(Long id) {
        return entityManager.find(Location.class, id);
    }

    @Override
    public Observation getObservationById(Long id) {
        return entityManager.find(Observation.class, id);
    }

    @Override
    public Wind getWindById(Long id) {
        return entityManager.find(Wind.class, id);
    }
}
