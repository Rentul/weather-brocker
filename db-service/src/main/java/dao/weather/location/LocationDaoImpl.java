package dao.weather.location;

import model.weather.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LocationDaoImpl implements LocationDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public Location add(final Location location) {
        entityManager.persist(location);
        return location;
    }

    @Override
    public Location get(Location location) {
        return entityManager.find(Location.class, location.getWoeid());
    }

    @Override
    public Location getByCity(String city) {

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Location> criteriaQuery = criteriaBuilder.createQuery(Location.class);
        final Root<Location> locationRoot = criteriaQuery.from(Location.class);
        final Predicate namePredicate = criteriaBuilder.equal(locationRoot.get("city"), city);
        criteriaQuery.where(namePredicate);
        criteriaQuery.select(locationRoot);
        final TypedQuery<Location> query = entityManager.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
