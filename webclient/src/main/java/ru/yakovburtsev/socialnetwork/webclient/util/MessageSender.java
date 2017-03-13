package ru.yakovburtsev.socialnetwork.webclient.util;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.io.Serializable;


public class MessageSender<T extends Serializable> {
    private final JmsTemplate jmsTemplate;
    private final String queue;

    public MessageSender(JmsTemplate jmsTemplate, String requestQueue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = requestQueue;
    }

    public void send(T object, String property, String value) {
        jmsTemplate.send(queue, session -> {
            ObjectMessage objectMessage = session.createObjectMessage(object);
            objectMessage.setStringProperty(property, value);
            return objectMessage;
        });
    }

    public void sendTextMessage(String text, String property, String value) {
        jmsTemplate.send(queue, session -> {
            TextMessage textMessage = session.createTextMessage(text);
            textMessage.setStringProperty(property, value);
            return textMessage;
        });
    }

    public void sendId(Long id, String property, String value) {
        jmsTemplate.send(queue, session -> {
            ObjectMessage objectMessage = session.createObjectMessage(id);
            objectMessage.setStringProperty(property, value);
            return objectMessage;
        });
    }
}
