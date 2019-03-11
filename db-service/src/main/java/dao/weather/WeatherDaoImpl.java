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

    private final String PERSISTENCE_UNIT_NAME = "weather";

    private EntityManager entityManager;

    /**
     * Геттер энтити-менеджера
     *
     * @return экземляр класса EntityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Сеттер энтити-менеджера
     *
     * @param entityManager экземпляр класса EntityManager
     */
    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAstronomy(final Astronomy astronomy) {
        entityManager.persist(astronomy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAtmosphere(final Atmosphere atmosphere) {
        entityManager.persist(atmosphere);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBroadcast(final WeatherBroadcast weatherBroadcast) {
        entityManager.persist(weatherBroadcast);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCondition(final Condition condition) {
        entityManager.persist(condition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addForecastList(final List<Forecast> forecasts) {
        for (Forecast forecast : forecasts) {
            entityManager.persist(forecast);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLocation(final Location location) {
        entityManager.persist(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObservation(final Observation observation) {
        entityManager.persist(observation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWind(final Wind wind) {
        entityManager.persist(wind);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Astronomy getAstronomyById(final Long id) {
        return entityManager.find(Astronomy.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Atmosphere getAtmosphereById(final Long id) {
        return entityManager.find(Atmosphere.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Condition getConditionById(final Long id) {
        return entityManager.find(Condition.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocationByCity(final String city) {
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
    public Location getLocationById(final Long id) {
        return entityManager.find(Location.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Observation getObservationById(final Long id) {
        return entityManager.find(Observation.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Wind getWindById(final Long id) {
        return entityManager.find(Wind.class, id);
    }
}
