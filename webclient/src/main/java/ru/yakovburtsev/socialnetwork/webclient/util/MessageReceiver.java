package ru.yakovburtsev.socialnetwork.webclient.util;

import org.springframework.jms.core.JmsTemplate;

import java.util.List;


public class MessageReceiver<V> {
    private final JmsTemplate jmsTemplate;
    private final String queue;

    public MessageReceiver(JmsTemplate jmsTemplate, String requestQueue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = requestQueue;
    }

    @SuppressWarnings("unchecked")
    public V receive(String selector) {
        return (V) jmsTemplate.receiveSelectedAndConvert(queue, selector);
    }

    @SuppressWarnings("unchecked")
    public List<V> receiveList(String selector) {
        return (List<V>) jmsTemplate.receiveSelectedAndConvert(queue, selector);
    }
}
