package service.weather.location;

import dao.weather.location.LocationDao;
import mapper.MapperFacade;
import view.weather.Location;

import javax.inject.Inject;

public class LocationServiceImpl implements LocationService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private LocationDao dao;

    @Override
    public model.weather.Location add(Location locationView) {

        model.weather.Location location = mapperFacade.map(locationView, model.weather.Location.class);

        dao.add(location);

        return location;
    }

    @Override
    public model.weather.Location get(Location locationView) {

        model.weather.Location location = mapperFacade.map(locationView, model.weather.Location.class);

        location = dao.get(location);

        return location;
    }

    @Override
    public model.weather.Location getByCity(String city) throws Exception{

        model.weather.Location location = dao.getByCity(city);

        if (location == null) {
            throw new Exception("There is no city named " + city);
        }

        return location;
    }
}
