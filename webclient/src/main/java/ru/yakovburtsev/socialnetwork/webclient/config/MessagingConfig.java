package ru.yakovburtsev.socialnetwork.webclient.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.webclient.util.MessageReceiver;
import ru.yakovburtsev.socialnetwork.webclient.util.MessageSender;


@Configuration
public class MessagingConfig {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    public final static String USER_QUEUE = "user_queue";
    public final static String USER_RESPONSE_QUEUE = "user_response_queue";
    public final static String FRIENDS_QUEUE = "friends_queue";
    public final static String FRIENDS_RESPONSE_QUEUE = "friends_response_queue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        connectionFactory.setTrustAllPackages(true);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(USER_QUEUE);
        template.setReceiveTimeout(5000);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        return factory;
    }

    @Bean
    public MessageSender<User> userMessageSender() {
        return new MessageSender<>(jmsTemplate(), USER_QUEUE);
    }

    @Bean
    public MessageReceiver<User> userMessageReceiver() {
        return new MessageReceiver<>(jmsTemplate(), USER_RESPONSE_QUEUE);
    }

    @Bean
    public MessageSender<UserInfo> userInfoMessageSender() {
        return new MessageSender<>(jmsTemplate(), USER_QUEUE);
    }

    @Bean
    public MessageReceiver<UserInfo> userInfoMessageReceiver() {
        return new MessageReceiver<>(jmsTemplate(), USER_RESPONSE_QUEUE);
    }
}
