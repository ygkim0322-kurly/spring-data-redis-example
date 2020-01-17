package com.redis.example.redistemplate.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.redis.example.redistemplate.dto.ValueDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 아래 객체는 기본적으로 RedisAutoConfiguration에서 생성함
 * - redisConnectionFactory
 * - redisTemplate<String,Object>
 * - stringRedisTemplate @See
 * org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */
@Configuration
public class RedisConfig {

  /**
   * 우리가 만든 DTO를 직접 직렬화하는 redisTemplate를 만들수도 있다.
   *
   */
  @Bean("simpleValueDtoRedisTemplate")
  public RedisTemplate<String, ValueDto> simpleValueDtoRedisTemplate(
      RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, ValueDto> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;
  }

  /**
   * spring-data-redis에 기본 탑재된 직렬화 객체가 마음에 들지 않아 만든 objectMapper
   *
   */
  @Bean
  public ObjectMapper objectMapper() {
    var mapper = new ObjectMapper();
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    mapper.registerModules(new JavaTimeModule(), new Jdk8Module());

    return mapper;
  }

  /**
   * 우리가 만든 DTO를, 우리가 설정한 objectMapper로 직접 직렬화하는 redisTemplate
   *
   */
  @Bean("customizedValueDtoRedisTemplate")
  public RedisTemplate<String, ValueDto> customizedValueDtoRedisTemplate
  (RedisConnectionFactory redisConnectionFactory, ObjectMapper objectMapper) {

    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(ValueDto.class);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    final RedisTemplate<String, ValueDto> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }



}
