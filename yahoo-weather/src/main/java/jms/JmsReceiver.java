package jms;

import service.Service;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
        mappedName="java:jboss/exported/jms/queue/test",
        activationConfig = {
            @ActivationConfigProperty(
                    propertyName = "acknowledgeMode",
                    propertyValue = "auto-acknowledge"),
            @ActivationConfigProperty(
                    propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(
                    propertyName = "destination",
                    propertyValue = "java:jboss/exported/jms/queue/test"),
})
public class JmsReceiver implements MessageListener {

    @Resource
    private MessageDrivenContext messageDrivenContext;

    @Resource(lookup = "java:global/yahoo-weather/YahooWeatherService")
    private Service service;

    @Override
    public void onMessage(final Message message) {
        final String text;
        try {
            if (message instanceof TextMessage) {
                text = ((TextMessage) message).getText();
                service.serve(text);
            }
        } catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

