package dao.weather.atmosphere;

import model.weather.Atmosphere;

public interface AtmosphereDao {

    String add(Atmosphere atmosphere);

    Atmosphere getById(Long id);
}
