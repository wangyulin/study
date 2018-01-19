package com.wyl.conf;

/*
import com.wyl.model.Name;
import com.wyl.utils.redis.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
*/

/**
 * Created by wangyulin on 01/03/2017.
 */
//@Configuration
public class RedisConfig {

    /*@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Name> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Name> template = new RedisTemplate<String, Name>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }*/

}
