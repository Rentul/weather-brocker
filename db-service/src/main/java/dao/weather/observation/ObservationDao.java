package dao.weather.observation;

import model.weather.*;

public interface ObservationDao {

    String add(Observation observation, Astronomy astronomy, Atmosphere atmosphere, Condition condition, Wind wind);

    Observation getObservationById(Long id);

    Astronomy getAstronomyById(Long id);

    Atmosphere getAtmosphereById(Long id);

    Condition getConditionById(Long id);

    Wind getWindById(Long id);
}
