package org.com.MSBank.login_service.login_service.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class TokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void storeToken(String tokenId, String data, Duration ttl) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String tokenKey = "token:" + tokenId;
        String shadowKey = "shadow:token:" + tokenId;

        ops.set(tokenKey, data, ttl);
        ops.set(shadowKey, "1", ttl);
    }
}

