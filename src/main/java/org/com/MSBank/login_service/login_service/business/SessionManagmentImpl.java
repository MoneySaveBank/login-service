package org.com.MSBank.login_service.login_service.business;

import org.com.MSBank.login_service.login_service.exception.ForbiddenException;
import org.com.MSBank.login_service.login_service.model.Customer;
import org.com.MSBank.login_service.login_service.repository.CustomerRepository;
import org.com.MSBank.login_service.login_service.utils.Utils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class SessionManagmentImpl {
    private final RedisTemplate<String, String> redisTemplate;

    private final CustomerRepository customerRepository;

    public SessionManagmentImpl(RedisTemplate<String, String> redisTemplate, CustomerRepository customerRepository) {
        this.redisTemplate = redisTemplate;
        this.customerRepository = customerRepository;
    }

    public String createSession(Customer customer){

        if(customer.getStatus().equals("b")){
            throw new ForbiddenException("Your customer is blocked");
        }
        if(customer.getSession()){
            throw new ForbiddenException("Your have an active session");
        }
        String token = Utils.creatToken();
        String redisTokenKey = "token:" + token;
        String shadowKey = "shadow:" + redisTokenKey;

        redisTemplate.opsForValue().set(redisTokenKey, customer.getCustomerNumber(), 2, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(shadowKey, customer.getCustomerNumber());

        //redisTemplate.opsForValue().set(token,
        //        Objects.requireNonNull(customer).getCustomerNumber(), 2, TimeUnit.MINUTES);

        customer.setSession(true);

        customerRepository.save(customer);

        return token;
    }
}
