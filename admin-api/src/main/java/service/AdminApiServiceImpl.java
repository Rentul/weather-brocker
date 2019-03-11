package service;

import jms.JmsSender;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
@Stateless
public class AdminApiServiceImpl implements AdminApiService {

    private final JmsSender jmsSender;

    /**
     * Конструктор
     *
     * @param jmsSender экземпляр класса JmsSender
     */
    @Inject
    public AdminApiServiceImpl(final JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    /**
     * {@inheritDoc}
     */
    public String serve(final String text) {
        return jmsSender.createJmsMessage(text);
    }

}
