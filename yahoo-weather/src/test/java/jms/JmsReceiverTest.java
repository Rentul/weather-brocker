package jms;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.YahooWeatherService;

import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import static org.mockito.Mockito.mock;

/**
 * Юнит тест JmsReceiver
 */
public class JmsReceiverTest {

    @Mock
    MessageDrivenContext messageDrivenContext = mock(MessageDrivenContext.class);

    @Mock
    YahooWeatherService yahooWeatherService = mock(YahooWeatherService.class);

    JmsReceiver jmsReceiver;

    /**
     * Инициализация полей перед тестами
     */
    @Before
    public void setUp() {
        jmsReceiver = new JmsReceiver();
        jmsReceiver.setMessageDrivenContext(messageDrivenContext);
        jmsReceiver.setYahooWeatherService(yahooWeatherService);
    }

    /**
     * Тест на успешное получение сообщения
     */
    @Test
    public void onMessageTest() {
        Message message = mock(TextMessage.class);
        String text = "text";

        try {
            Mockito.doReturn(text)
                    .when((TextMessage) message)
                    .getText();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

        jmsReceiver.onMessage(message);

        Mockito.verify(yahooWeatherService, Mockito.times(1)).serve(text);
    }

    /**
     * Тест на неудачное получение сообщения из-за неверного типа реализации Message
     */
    @Test
    public void onMessageFailTest() {
        Message message = mock(ObjectMessage.class);

        jmsReceiver.onMessage(message);

        Mockito.verify(yahooWeatherService, Mockito.never()).serve(Mockito.anyString());
    }
}
