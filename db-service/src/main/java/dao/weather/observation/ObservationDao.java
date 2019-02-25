package dao.weather.observation;

import model.weather.Observation;

public interface ObservationDao {

    String add(Observation observation);

    Observation getById(Long id);
}
