package jms;

import org.springframework.beans.factory.annotation.Autowired;
import service.weather.WeatherService;
import view.weather.WeatherBroadcast;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class JmsReceiver implements MessageListener {

    final private WeatherService weatherService;

    @Autowired
    public JmsReceiver(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void onMessage(final Message message) {

        final WeatherBroadcast weatherBroadcast;
        try {
            if (message instanceof ObjectMessage) {
                weatherBroadcast = message.getBody(WeatherBroadcast.class);
                weatherService.addWeatherBroadcast(weatherBroadcast);
            } else {
                throw new RuntimeException("Error in JmsReceiver while receiving Jms Message: the message is not an instance of an Object message");
            }
        } catch (JMSException e) {
            throw new RuntimeException("Error in JmsReceiver while receiving Jms Message", e);
        }
    }
}