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

    private static String LOCATION = "location";

    private static String CURRENT_OBSERVATION = "currentObservation";

    private static String FORECASTS = "forecasts";

    private static String LAT = "lat";

    private static String LATITUDE = "latitude";

    private static String WIND ="wind";

    private static String ATMOSPHERE = "atmosphere";

    private static String ASTRONOMY = "astronomy";

    private static String CONDITION = "condition";

    private static String ID = "id";

    private static String VERSION = "version";

    private static String OBSERVATION = "observation";

    private static String WEATHER_BROADCAST = "weatherBroadcast";

    private static String WEATHER_BROADCASTS = "weatherBroadcasts";

    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();

        mapperFactory.classMap(WeatherBroadcast.class, model.weather.WeatherBroadcast.class)
                .byDefault()
                .exclude(LOCATION)
                .exclude(CURRENT_OBSERVATION)
                .exclude(FORECASTS)
                .register();

        mapperFactory.classMap(Location.class, model.weather.Location.class)
                .byDefault()
                .fieldAToB(LAT, LATITUDE)
                .register();

        mapperFactory.classMap(CurrentObservation.class, model.weather.Observation.class)
                .byDefault()
                .exclude(WIND)
                .exclude(ATMOSPHERE)
                .exclude(ASTRONOMY)
                .exclude(CONDITION)
                .register();

        mapperFactory.classMap(model.weather.Astronomy.class, Astronomy.class)
                .byDefault()
                .exclude(ID)
                .exclude(VERSION)
                .exclude(OBSERVATION)
                .register();

        mapperFactory.classMap(model.weather.Atmosphere.class, Atmosphere.class)
                .byDefault()
                .exclude(ID)
                .exclude(VERSION)
                .exclude(OBSERVATION)
                .register();

        mapperFactory.classMap(model.weather.Condition.class, Condition.class)
                .byDefault()
                .exclude(ID)
                .exclude(VERSION)
                .exclude(OBSERVATION)
                .register();

        mapperFactory.classMap(model.weather.Forecast.class, Forecast.class)
                .byDefault()
                .exclude(ID)
                .exclude(VERSION)
                .exclude(OBSERVATION)
                .register();

        mapperFactory.classMap(model.weather.Location.class, Location.class)
                .byDefault()
                .exclude(WEATHER_BROADCASTS)
                .fieldAToB(LATITUDE, LAT)
                .register();

        mapperFactory.classMap(model.weather.Observation.class, CurrentObservation.class)
                .byDefault()
                .exclude(ID)
                .exclude(VERSION)
                .exclude(WEATHER_BROADCAST)
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
