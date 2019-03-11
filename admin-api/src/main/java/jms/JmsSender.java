package jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.jms.Queue;

/**
 * Publisher сообщений в JMS очередь
 */
public class JmsSender {

    private static final String JMS_CONNECTION_FACTORY = "java:jboss/exported/jms/RemoteConnectionFactory";

    private static final String JMS_USER_NAME = "admin-api";

    private static final String JMS_PASSWORD = "admin-api";

    private static final String QUEUE_NAME = "java:jboss/exported/jms/queue/test";

    private static final String SUCCESS_MESSAGE = "success";

    private static final String NO_SUCCESS_MESSAGE = "enter city";

    private JMSContext jmsContext;

    private Queue queue;

    @Inject
    @JMSConnectionFactory(JMS_CONNECTION_FACTORY)
    @JMSPasswordCredential(userName = JMS_USER_NAME, password = JMS_PASSWORD)
    public void setJmsContext(JMSContext jmsContext) {
        this.jmsContext = jmsContext;
    }

    @Resource(name = QUEUE_NAME)
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Queue getQueue() {
        return queue;
    }

    public JMSContext getJmsContext() {
        return jmsContext;
    }

    /**
     * Создание и отправка сообщения
     *
     * @param text текст сообщения
     * @return результат работы метода
     */
    public String createJmsMessage(final String text) {

        if (!"".equals(text)) {
            jmsContext.createProducer().send(queue, text);
            return SUCCESS_MESSAGE;
        } else {
            return NO_SUCCESS_MESSAGE;
        }
    }
}
