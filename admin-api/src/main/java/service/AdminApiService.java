package service;

import jms.JmsSender;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AdminApiService implements Service {

    private final JmsSender jmsSender;

    @Inject
    public AdminApiService(final JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    public String serve(final String text) {
        return jmsSender.createJmsMessage(text);
    }

}
