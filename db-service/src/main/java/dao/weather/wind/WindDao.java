package dao.weather.wind;

import model.weather.Wind;

public interface WindDao {

    String add(Wind wind);

    Wind getById(Long id);
}
