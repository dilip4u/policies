/**
 * Created by Dilip Kancharla on Jun 29, 2015
 */
package com.dell.credant.jms.consumer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import com.dell.credant.jms.consumer.util.ConsumerUtil;

/**
 * @author Dilip Reddy Kancharla .
 * Consumes messages from PasswodPolicy topic.
 */
@Component
public class PasswordPolicyConsumer implements MessageListener {
   /**
     * Logger object.
     */
    private static final Logger LOGGER =
              LoggerFactory.getLogger(PasswordPolicyConsumer.class);
    /**
      * consumer utility object.
      */
    private ConsumerUtil consUtil;

    /**
     * @param consumerUtil the consUtil to set
     */
    public final void setConsUtil(final ConsumerUtil consumerUtil) {
        this.consUtil = consumerUtil;
    }

    /**
     * Listener method that polls Topic to dequeue them as they get queued.
     * @param message JMX Message that will be sent upon new message in topic.
     */
    @Override
    public final void onMessage(final Message message) {
          LOGGER.info("Listener starting");
        try {
          if (message instanceof ObjectMessage) {
            ObjectMessage mapMessage = (ObjectMessage) message;
            Object obj = mapMessage.getObject();
            LOGGER.info("received data:" + obj);
            consUtil.auditMessage(obj);
          }
        } catch (Exception e) {
          LOGGER.debug(e.toString());
        }
      }
}
