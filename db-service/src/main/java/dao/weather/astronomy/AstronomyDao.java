package dao.weather.astronomy;

import model.weather.Astronomy;

public interface AstronomyDao {

    String add(Astronomy astronomy);

    Astronomy getById(Long id);
}
