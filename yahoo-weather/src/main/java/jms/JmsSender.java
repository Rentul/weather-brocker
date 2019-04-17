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

    private static final String JMS_CONNECTION_FACTORY = "java:/JmsXA";

    private static final String JMS_USER_NAME = "yahoo-weather";

    private static final String JMS_PASSWORD = "yahoo-weather";

    private static final String QUEUE_NAME = "java:/queue/yahooDb";

    private JMSContext jmsContext;

    private Queue queue;

    /**
     * Сеттер JMS контекста
     *
     * @param jmsContext JMS контекст
     */
    @Inject
    @JMSConnectionFactory(JMS_CONNECTION_FACTORY)
    @JMSPasswordCredential(userName = JMS_USER_NAME, password = JMS_PASSWORD)
    public void setJmsContext(final JMSContext jmsContext) {
        this.jmsContext = jmsContext;
    }

    /**
     * Сеттер очереди
     *
     * @param queue очередь
     */
    @Resource(name = QUEUE_NAME)
    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    /**
     * Геттер JMS контекста
     *
     * @return JMS контекст
     */
    public JMSContext getJmsContext() {
        return jmsContext;
    }

    /**
     * Геттер очереди
     *
     * @return очередь
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Отправка прогноза погоды через JMS
     *
     * @param weatherBroadcast прогноз погоды
     */
    public void createJmsMessage(final WeatherBroadcast weatherBroadcast) {

        if (weatherBroadcast != null) {
            jmsContext.createProducer().send(queue, weatherBroadcast);
        } else {
            throw new RuntimeException("Error in JmsSender while sending Jms Message: weatherBroadcast is null");
        }
    }
}
