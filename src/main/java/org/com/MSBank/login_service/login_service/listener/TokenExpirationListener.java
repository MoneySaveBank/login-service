package org.com.MSBank.login_service.login_service.listener;

import jakarta.annotation.PostConstruct;
import org.com.MSBank.login_service.login_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class TokenExpirationListener implements MessageListener {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisMessageListenerContainer listenerContainer;

    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        listenerContainer.addMessageListener(this, new PatternTopic("__keyevent@0__:expired"));
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {String expiredKey = new String(message.getBody()); // token:{uuid}
        System.out.println("Evento captado de Redis: " + expiredKey);

        if (!expiredKey.startsWith("token:")) return;

        String shadowKey = "shadow:" + expiredKey;
        String customerNumber = redisTemplate.opsForValue().get(shadowKey);

        redisTemplate.delete(shadowKey);

        customerRepository.findById(customerNumber).ifPresent(customer -> {
            customer.setSession(false);
            customerRepository.save(customer);
            System.out.println("Sesión cerrada para cliente: " + customerNumber);
        });
    }
}

