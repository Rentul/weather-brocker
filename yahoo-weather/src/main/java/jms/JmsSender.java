package jms;

import view.weather.WeatherBroadcast;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.jms.Queue;

@Stateless
public class JmsSender {

    @Inject
    @JMSConnectionFactory("java:jboss/exported/jms/RemoteConnectionFactory")
    @JMSPasswordCredential(userName = "yahoo-weather", password = "yahoo-weather")
    private JMSContext jmsContext;

    @Resource(name = "java:jboss/exported/jms/queue/test2")
    private Queue queue;

    public void createJmsMessage(final WeatherBroadcast weatherBroadcast) {

        if (weatherBroadcast != null) {
            jmsContext.createProducer().send(queue, weatherBroadcast);
        } else {
            throw new RuntimeException("Error in JmsSender while sending Jms Message: weatherBroadcast is null");
        }
    }
}
