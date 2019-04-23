package jms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.mock;

/**
 * Юнит тест JmsSender
 */
public class JmsSenderTest {

    JmsSender jmsSender;

    @Mock
    JMSContext jmsContext = mock(JMSContext.class);

    @Mock
    Queue queue = mock(Queue.class);

    /**
     * Инициализация полей перед тестами
     */
    @Before
    public void setUp() {
        jmsSender = new JmsSender();
        jmsSender.setJmsContext(jmsContext);
        jmsSender.setQueue(queue);
    }

    /**
     * Тест на успешную отправку сообщения
     */
    @Test
    public void createJmsMessageTest() {
        String text = "sometext";

        JMSProducer jmsProducer = mock(JMSProducer.class);

        Mockito.doReturn(jmsProducer)
                .when(jmsContext)
                .createProducer();

        Assert.assertEquals("success", jmsSender.createJmsMessage(text));
        Mockito.verify(jmsContext, Mockito.times(1)).createProducer();
        Mockito.verify(jmsProducer, Mockito.times(1)).send(queue, text);
    }

    /**
     * Тест на неудачную отправку пустого сообщения
     */
    @Test
    public void createJmsMessageEmptyFailTest() {
        String text = "";

        Assert.assertEquals("enter city", jmsSender.createJmsMessage(text));
        Mockito.verify(jmsContext, Mockito.never()).createProducer();
    }

    /**
     * Тест на неудачную отправку null сообщения
     */
    @Test
    public void createJmsMessageNullFailTest() {
        String text = null;

        Assert.assertEquals("enter city", jmsSender.createJmsMessage(text));
        Mockito.verify(jmsContext, Mockito.never()).createProducer();
    }
}
