package com.redis.example.redistemplate.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 레디스에서 가장 단순한 자료형인 Strings 형식에 대한 예제 서비스
 */
@Service
public class ExampleStringsOperationsService {
  @Resource private StringRedisTemplate stringRedisTemplate;

  /**
   * Strings 데이터 저장 메소드
   *
   * @param key Strings 데이터의 key
   * @param value Strings 데이터의 value
   */
  public void setStringValue(final String key, final String value) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    valueOperations.set(key, value);
  }

  /**
   * Strings 데이터 조회하는 메소드
   *
   * @param key Strings 데이터의 key
   * @return  Strings 데이터
   */
  public String getStringValue(final String key) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    return valueOperations.get(key);
  }

  /**
   * Strings 데이터 단일 건 삭제하는 메소드
   * @param key Strings 데이터의 key
   * @return 삭제성공 여부
   */
  public boolean deleteStringValue(final String key) {
    return stringRedisTemplate.delete(key);
  }

  /**
   * Strings 데이터 여러 건 삭제하는 메소드
   * @param keys Strings 데이터 콜렉션
   * @return 삭제 성공한 데이터 갯수
   */
  public long deleteStringValues(final Collection<String> keys) {
    return stringRedisTemplate.delete(keys);
  }

  /**
   * String 데이터에 추가 데이터를 이어붙이는 메소드
   * @param key
   * @param value
   * @return
   */
  public Integer appendStringValues(final String key, final String value) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    return valueOperations.append(key, value);
  }




}
