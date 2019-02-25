package jms;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.jms.Queue;

public class JmsSender {

    @Inject
    @JMSConnectionFactory("java:jboss/exported/jms/RemoteConnectionFactory")
    @JMSPasswordCredential(userName = "admin-api", password = "admin-api")
    private JMSContext jmsContext;

    @Resource(name = "java:jboss/exported/jms/queue/test")
    private Queue queue;

    public String createJmsMessage(final String text) {

        if (!"".equals(text)) {
            jmsContext.createProducer().send(queue, text);
            return "success";
        } else {
            return "enter city";
        }
    }
}
