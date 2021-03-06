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

    private static final String JMS_CONNECTION_FACTORY = "java:/JmsXA";

    private static final String JMS_USER_NAME = "admin-api";

    private static final String JMS_PASSWORD = "admin-api";

    private static final String QUEUE_NAME = "java:/queue/adminYahoo";

    private static final String SUCCESS_MESSAGE = "success";

    private static final String NO_SUCCESS_MESSAGE = "enter city";

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
     * @param queue объект класса queue
     */
    @Resource(name = QUEUE_NAME)
    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    /**
     * Геттер очереди
     *
     * @return объект класса queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Геттер JMS контекста
     *
     * @return объект класса JmsContext
     */
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

        if (text != null && text.length() > 0) {
            jmsContext.createProducer().send(queue, text);
            return SUCCESS_MESSAGE;
        } else {
            return NO_SUCCESS_MESSAGE;
        }
    }
}
