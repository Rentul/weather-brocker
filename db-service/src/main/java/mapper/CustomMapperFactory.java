package mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import view.weather.Astronomy;
import view.weather.CurrentObservation;
import view.weather.Location;
import view.weather.WeatherBroadcast;
import view.weather.Atmosphere;
import view.weather.Condition;
import view.weather.Forecast;

@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {

    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();

        mapperFactory.classMap(WeatherBroadcast.class, model.weather.WeatherBroadcast.class)
                .byDefault()
                .exclude("location")
                .exclude("currentObservation")
                .exclude("forecasts")
                .register();

        mapperFactory.classMap(Location.class, model.weather.Location.class)
                .byDefault()
                .fieldAToB("lat", "latitude")
                .register();

        mapperFactory.classMap(CurrentObservation.class, model.weather.Observation.class)
                .byDefault()
                .exclude("wind")
                .exclude("atmosphere")
                .exclude("astronomy")
                .exclude("condition")
                .register();

        mapperFactory.classMap(model.weather.Astronomy.class, Astronomy.class)
                .byDefault()
                .exclude("id")
                .exclude("version")
                .exclude("observation")
                .register();

        mapperFactory.classMap(model.weather.Atmosphere.class, Atmosphere.class)
                .byDefault()
                .exclude("id")
                .exclude("version")
                .exclude("observation")
                .register();

        mapperFactory.classMap(model.weather.Condition.class, Condition.class)
                .byDefault()
                .exclude("id")
                .exclude("version")
                .exclude("observation")
                .register();

        mapperFactory.classMap(model.weather.Forecast.class, Forecast.class)
                .byDefault()
                .exclude("id")
                .exclude("version")
                .exclude("weatherBroadcast")
                .register();

        mapperFactory.classMap(model.weather.Location.class, Location.class)
                .byDefault()
                .exclude("weatherBroadcasts")
                .fieldAToB("latitude", "lat")
                .register();

        mapperFactory.classMap(model.weather.Observation.class, CurrentObservation.class)
                .byDefault()
                .exclude("id")
                .exclude("version")
                .exclude("weatherBroadcast")
                .register();

        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
