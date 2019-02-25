package dao.weather.weatherBroadcast;

import model.weather.WeatherBroadcast;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class WeatherBroadcastDaoImpl implements WeatherBroadcastDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(WeatherBroadcast weatherBroadcast) {
        entityManager.persist(weatherBroadcast);
        return null;
    }
}
