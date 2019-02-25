package service.weather.weatherBroadcast;

import dao.weather.weatherBroadcast.WeatherBroadcastDao;
import mapper.MapperFacade;
import view.weather.WeatherBroadcast;

import javax.inject.Inject;

public class WeatherBroadcastServiceImpl implements WeatherBroadcastService {

    @Inject
    private MapperFacade mapperFacade;

    @Inject
    private WeatherBroadcastDao dao;

    @Override
    public model.weather.WeatherBroadcast add(WeatherBroadcast broadcastView) {

        model.weather.WeatherBroadcast weatherBroadcast = mapperFacade.map(broadcastView, model.weather.WeatherBroadcast.class);

        if(weatherBroadcast == null) {
            weatherBroadcast = new model.weather.WeatherBroadcast();
        }

        dao.add(weatherBroadcast);

        return weatherBroadcast;
    }
}
