package com.zhkuchen.framework.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis配置
 * 
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport
{
    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory)
    {
    	 RedisTemplate redisTemplate = new RedisTemplate<>();
         redisTemplate.setConnectionFactory(connectionFactory);
         // 使用 GenericFastJsonRedisSerializer 替换默认序列化
         GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
         // 设置key和value的序列化规则
         redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Object.class));
         redisTemplate.setValueSerializer(genericFastJsonRedisSerializer);
         // 设置hashKey和hashValue的序列化规则
         redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
         redisTemplate.setHashValueSerializer(genericFastJsonRedisSerializer);
         // 设置支持事物
         redisTemplate.setEnableTransactionSupport(true);
         redisTemplate.afterPropertiesSet();
         return redisTemplate;
//         RedisTemplate template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//
//        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//
//        template.setValueSerializer(serializer);
//        // 使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
    }
}
