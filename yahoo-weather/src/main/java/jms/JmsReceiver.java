package jms;

import service.Service;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.inject.Inject;
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
                    propertyValue = "java:jboss/exported/jms/queue/test")
})
public class JmsReceiver implements MessageListener {

    private MessageDrivenContext messageDrivenContext;

    private Service service;

    @Resource
    public void setMessageDrivenContext(final MessageDrivenContext messageDrivenContext) {
        this.messageDrivenContext = messageDrivenContext;
    }

    @Inject
    public void setService(final Service service) {
        this.service = service;
    }

    @Override
    public void onMessage(final Message message) {
        final String text;
        try {
            if (message instanceof TextMessage) {
                text = ((TextMessage) message).getText();
                service.serve(text);
            }
        } catch (JMSException e) {
            throw new RuntimeException("Error in JmsReceiver while receiving Jms Message", e);
        }
    }
}

