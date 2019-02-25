package service;

import jms.JmsSender;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AdminApiService implements Service {

    @Inject
    private JmsSender jmsSender;

    public void serve(final String text) {
        jmsSender.createJmsMessage(text);
    }

}
