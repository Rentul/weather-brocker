package jms;

import service.WeatherService;
import view.weather.WeatherBroadcast;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
        mappedName="java:jboss/exported/jms/queue/test2",
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "acknowledgeMode",
                        propertyValue = "auto-acknowledge"),
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(
                        propertyName = "destination",
                        propertyValue = "java:jboss/exported/jms/queue/test2"),
        })
public class JmsReceiver implements MessageListener {

    @Resource
    private MessageDrivenContext messageDrivenContext;

    @Inject
    private WeatherService weatherService;

    @Override
    public void onMessage(final Message message) {

        final WeatherBroadcast weatherBroadcast;
        try {
            if (message instanceof ObjectMessage) {
                weatherBroadcast = message.getBody(WeatherBroadcast.class);
                weatherService.addWeatherBroadcast(weatherBroadcast);
            }
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}