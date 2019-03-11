package dao.weather;

import model.weather.Astronomy;
import model.weather.Atmosphere;
import model.weather.Condition;
import model.weather.WeatherBroadcast;
import model.weather.Forecast;
import model.weather.Location;
import model.weather.Observation;
import model.weather.Wind;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class WeatherDaoImpl implements WeatherDao {

    private final String PERSISTENCE_UNIT_NAME = "sample";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAstronomy(Astronomy astronomy) {
        entityManager.persist(astronomy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAtmosphere(Atmosphere atmosphere) {
        entityManager.persist(atmosphere);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBroadcast(WeatherBroadcast weatherBroadcast) {
        entityManager.persist(weatherBroadcast);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCondition(Condition condition) {
        entityManager.persist(condition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addForecastList(List<Forecast> forecasts) {
        for (Forecast forecast : forecasts) {
            entityManager.persist(forecast);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLocation(Location location) {
        entityManager.persist(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObservation(Observation observation) {
        entityManager.persist(observation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWind(Wind wind) {
        entityManager.persist(wind);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Astronomy getAstronomyById(Long id) {
        return entityManager.find(Astronomy.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Atmosphere getAtmosphereById(Long id) {
        return entityManager.find(Atmosphere.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Condition getConditionById(Long id) {
        return entityManager.find(Condition.class, id);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocationById(Long id) {
        return entityManager.find(Location.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observation getObservationById(Long id) {
        return entityManager.find(Observation.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wind getWindById(Long id) {
        return entityManager.find(Wind.class, id);
    }
}
