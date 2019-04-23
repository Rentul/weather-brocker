package service;

import jms.JmsSender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.jms.JMSProducer;

import static org.mockito.Mockito.mock;

public class AdminApiServiceTest {

    AdminApiService adminApiService;

    @Mock
    JmsSender jmsSender = mock(JmsSender.class);


    /**
     * Инициализация полей перед тестами
     */
    @Before
    public void setUp() {
        adminApiService = new AdminApiServiceImpl(jmsSender);
    }

    /**
     * Тест на успешную отправку сообщения
     */
    @Test
    public void createJmsMessageTest() {
        String text = "sometext";

        Mockito.doReturn("success")
                .when(jmsSender)
                .createJmsMessage(text);

        Assert.assertEquals("success", adminApiService.serve(text));
        Mockito.verify(jmsSender, Mockito.times(1)).createJmsMessage(text);
    }

    /**
     * Тест на неудачную отправку пустого сообщения
     */
    @Test
    public void createJmsMessageEmptyFailTest() {
        String text = "";

        Mockito.doReturn("enter city")
                .when(jmsSender)
                .createJmsMessage(text);

        Assert.assertEquals("enter city", adminApiService.serve(text));
        Mockito.verify(jmsSender, Mockito.times(1)).createJmsMessage(text);
    }

    /**
     * Тест на неудачную отправку null сообщения
     */
    @Test
    public void createJmsMessageNullFailTest() {
        String text = null;

        Mockito.doReturn("enter city")
                .when(jmsSender)
                .createJmsMessage(text);

        Assert.assertEquals("enter city", adminApiService.serve(text));
        Mockito.verify(jmsSender, Mockito.times(1)).createJmsMessage(text);
    }
}
