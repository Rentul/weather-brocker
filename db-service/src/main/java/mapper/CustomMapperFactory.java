package mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import view.weather.*;

import javax.enterprise.inject.Produces;

public class CustomMapperFactory {

    @Produces
    public MapperFactory createClient() {
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
}
