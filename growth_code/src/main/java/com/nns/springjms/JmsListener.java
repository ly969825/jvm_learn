package com.nns.springjms;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component(value = "jmsListener")
public class JmsListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            System.out.println("收到消息：" + objectMessage.getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
