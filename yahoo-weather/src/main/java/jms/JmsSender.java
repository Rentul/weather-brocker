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

    private static final String JMS_CONNECTION_FACTORY = "java:jboss/exported/jms/RemoteConnectionFactory";

    private static final String JMS_USER_NAME = "yahoo-weather";

    private static final String JMS_PASSWORD = "yahoo-weather";

    private static final String QUEUE_NAME = "java:jboss/exported/jms/queue/test2";

    @Inject
    @JMSConnectionFactory(JMS_CONNECTION_FACTORY)
    @JMSPasswordCredential(userName = JMS_USER_NAME, password = JMS_PASSWORD)
    private JMSContext jmsContext;

    @Resource(name = QUEUE_NAME)
    private Queue queue;

    public void createJmsMessage(final WeatherBroadcast weatherBroadcast) {

        if (weatherBroadcast != null) {
            jmsContext.createProducer().send(queue, weatherBroadcast);
        } else {
            throw new RuntimeException("Error in JmsSender while sending Jms Message: weatherBroadcast is null");
        }
    }
}
