package dao.weather.location;

import model.weather.Location;

public interface LocationDao {

    Location add(Location location);

    Location get(Location location);

    Location getByCity(String city);
}
