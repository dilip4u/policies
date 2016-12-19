/**
 * Created by Dilip Kancharla on Jun 28, 2015
 */
package com.dell.credant.core.dao.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.dell.credant.core.dao.PasswordPolicyDao;
import com.dell.credant.core.model.entities.PasswordPolicy;

/**
 * @author Dilip Kancharla.
 *
 */
@Component
public class PasswordPolicyDaoJmsImpl implements PasswordPolicyDao {
    /**
     * Logger object to assist in logging.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PasswordPolicyDaoJmsImpl.class);
    /**
     * JmsTemplate objevt to assist in inserting into JSM topic.
     */
    @Autowired
    private JmsTemplate jmsTemplate;
    /**
     * PasswordPolicyTopic referring to the topic where message
     * will be queued.
     */
    @Autowired
    private Topic passwordPolicyTopic;

    /**
     * Takes passwordPolicy object that needs to be queued
     *  into JMS topic.
     *  @param policy takes policy object that needs to be queued
     * @return PasswordPolicy returns policy object after queuing.
     */
    @Override
    public final PasswordPolicy queuePolicy(final PasswordPolicy policy) {
        LOGGER.debug("Policy info:" + policy);
        this.jmsTemplate.send(this.passwordPolicyTopic,
            new MessageCreator() {
             public Message createMessage(final Session session)
                     throws JMSException {
                        ObjectMessage mm = session.createObjectMessage();
                        mm.setObject(policy);
                        LOGGER.debug("From queue:" + mm);
                        return mm;
                     }
              });
        return policy;
    }

}
