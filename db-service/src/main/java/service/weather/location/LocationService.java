package service.weather.location;

import view.weather.Location;

public interface LocationService {

    model.weather.Location add(Location locationView);

    model.weather.Location get(Location locationView);

    model.weather.Location getByCity(String city) throws Exception;

}
