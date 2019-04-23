package jms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import view.weather.WeatherBroadcast;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.mock;

public class JmsSenderTest {

    @Mock
    JMSContext jmsContext = mock(JMSContext.class);

    @Mock
    Queue queue = mock(Queue.class);

    JmsSender jmsSender;

    /**
     * Инициализация полей перед тестами
     */
    @Before
    public void setUp() {
        jmsSender = new JmsSender();
        jmsSender.setQueue(queue);
        jmsSender.setJmsContext(jmsContext);
    }

    /**
     * Тест на успешную отправку сообщения
     */
    @Test
    public void createJmsMessageTest() {
        WeatherBroadcast weatherBroadcast = new WeatherBroadcast();

        JMSProducer producer = mock(JMSProducer.class);

        Mockito.doReturn(producer)
                .when(jmsContext)
                .createProducer();

        jmsSender.createJmsMessage(weatherBroadcast);

        Mockito.verify(jmsContext, Mockito.times(1)).createProducer();
        Mockito.verify(producer, Mockito.times(1)).send(queue, weatherBroadcast);
    }

    /**
     * Тест на неудачную отправку сообщения из-за null WeatherBroadcast
     */
    @Test(expected = RuntimeException.class)
    public void createJmsMessageFailTest() {
        WeatherBroadcast weatherBroadcast = null;

        jmsSender.createJmsMessage(weatherBroadcast);
    }
}
