package jms;

import service.YahooWeatherService;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Получатель JMS сообщений
 */
@MessageDriven(
        mappedName="java:/queue/adminYahoo",
        activationConfig = {
            @ActivationConfigProperty(
                    propertyName = "acknowledgeMode",
                    propertyValue = "auto-acknowledge"),
            @ActivationConfigProperty(
                    propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(
                    propertyName = "destination",
                    propertyValue = "java:/queue/adminYahoo")
})
public class JmsReceiver implements MessageListener {

    private MessageDrivenContext messageDrivenContext;

    private YahooWeatherService yahooWeatherService;

    /**
     * Сеттер контекста MDB
     *
     * @param messageDrivenContext контекст MDB
     */
    @Resource
    public void setMessageDrivenContext(final MessageDrivenContext messageDrivenContext) {
        this.messageDrivenContext = messageDrivenContext;
    }

    /**
     * Сеттер погодного сервиса
     *
     * @param yahooWeatherService погодный сервис
     */
    @Inject
    public void setYahooWeatherService(final YahooWeatherService yahooWeatherService) {
        this.yahooWeatherService = yahooWeatherService;
    }

    /**
     * Геттер контекста MDB
     *
     * @return контекст MDB
     */
    public MessageDrivenContext getMessageDrivenContext() {
        return messageDrivenContext;
    }

    /**
     * Геттер погодного сервиса
     *
     * @return погодный сервис
     */
    public YahooWeatherService getYahooWeatherService() {
        return yahooWeatherService;
    }

    /**
     * Обработчик входящих сообщений
     *
     * @param message входящее сообщение
     */
    @Override
    public void onMessage(final Message message) {
        final String text;
        try {
            if (message instanceof TextMessage) {
                text = ((TextMessage) message).getText();
                yahooWeatherService.serve(text);
            }
        } catch (JMSException e) {
            throw new RuntimeException("Error in JmsReceiver while receiving Jms Message", e);
        }
    }
}

