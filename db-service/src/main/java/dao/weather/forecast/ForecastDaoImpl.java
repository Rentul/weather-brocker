package dao.weather.forecast;

import model.weather.Forecast;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ForecastDaoImpl implements ForecastDao {

    @PersistenceContext(unitName = "sample")
    private EntityManager entityManager;

    @Override
    public String add(Forecast forecast) {
        entityManager.persist(forecast);
        return null;
    }

    @Override
    public String addList(List<Forecast> forecasts) {
        return null;
    }
}
