package service;

import jms.JmsSender;
import mapper.JacksonMapper;
import view.weather.WeatherBroadcast;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
@Stateless
public class YahooWeatherServiceImpl implements YahooWeatherService {

    private final JmsSender jmsSender;

    private final YahooRequester yahooRequester;

    private final JacksonMapper jacksonMapper;

    @Inject
    public YahooWeatherServiceImpl(
            final JmsSender jmsSender,
            final YahooRequester yahooRequester,
            final JacksonMapper jacksonMapper) {

        this.jmsSender = jmsSender;
        this.yahooRequester = yahooRequester;
        this.jacksonMapper = jacksonMapper;
    }

    /**
     * {@inheritDoc}
     */
    public void serve (final String cityName) {

        final String yahooResponse = yahooRequester.makeRequestToYahoo(cityName);

        final WeatherBroadcast weatherBroadcast = jacksonMapper.mapJsonToWeatherBroadcast(yahooResponse);

        jmsSender.createJmsMessage(weatherBroadcast);

    }

}
