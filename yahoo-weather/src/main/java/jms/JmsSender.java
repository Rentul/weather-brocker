package jms;

import view.weather.WeatherBroadcast;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.jms.Queue;

/**
 * Publisher JMS сообщений
 */
@Stateless
public class JmsSender {

    private static final String JMS_CONNECTION_FACTORY = "java:jboss/exported/jms/RemoteConnectionFactory";

    private static final String JMS_USER_NAME = "yahoo-weather";

    private static final String JMS_PASSWORD = "yahoo-weather";

    private static final String QUEUE_NAME = "java:jboss/exported/jms/queue/test2";

    private JMSContext jmsContext;

    private Queue queue;

    @Inject
    @JMSConnectionFactory(JMS_CONNECTION_FACTORY)
    @JMSPasswordCredential(userName = JMS_USER_NAME, password = JMS_PASSWORD)
    public void setJmsContext(final JMSContext jmsContext) {
        this.jmsContext = jmsContext;
    }

    @Resource(name = QUEUE_NAME)
    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    /**
     * Отправка прогноза погоды через JMS
     *
     * @param weatherBroadcast  прогноз погоды
     */
    public void createJmsMessage(final WeatherBroadcast weatherBroadcast) {

        if (weatherBroadcast != null) {
            jmsContext.createProducer().send(queue, weatherBroadcast);
        } else {
            throw new RuntimeException("Error in JmsSender while sending Jms Message: weatherBroadcast is null");
        }
    }
}
