package service;

import jms.JmsSender;
import mapper.JacksonMapper;
import view.weather.WeatherBroadcast;

import javax.annotation.Resource;
import javax.ejb.Stateless;

@Stateless
public class YahooWeatherService implements Service {

    @Resource(lookup = "java:global/yahoo-weather/JmsSender")
    private JmsSender jmsSender;

    @Resource(lookup = "java:global/yahoo-weather/YahooRequester")
    private YahooRequester yahooRequester;

    @Resource(lookup = "java:global/yahoo-weather/JacksonMapper")
    private JacksonMapper jacksonMapper;

    public void serve (final String cityName) {

        final String yahooResponse = yahooRequester.makeRequestToYahoo(cityName);

        final WeatherBroadcast weatherBroadcast = jacksonMapper.mapJsonToWeatherBroadcast(yahooResponse);

        jmsSender.createJmsMessage(weatherBroadcast);

    }


}
