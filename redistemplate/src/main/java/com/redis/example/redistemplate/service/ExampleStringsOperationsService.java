package com.redis.example.redistemplate.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/** 레디스에서 가장 단순한 자료형인 Strings 형식에 대한 예제 서비스 */
@Service
public class ExampleStringsOperationsService {
  @Resource private StringRedisTemplate stringRedisTemplate;

  /**
   * Strings 데이터 단건 저장 메소드
   *
   * @param key Strings 데이터의 key
   * @param value Strings 데이터의 value
   */
  public void set(final String key, final String value) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    valueOperations.set(key, value);
  }

  /**
   * Strings 데이터 다건 저장 메소드
   * @param mapObj 저장할 다량 데이터
   */
  public void multiSet(final Map<String, String> mapObj) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    valueOperations.multiSet(mapObj);
  }

  /**
   * Strings 데이터 단건을 조회하는 메소드
   *
   * @param key Strings 데이터의 key
   * @return Strings 데이터
   */
  public String get(final String key) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    return valueOperations.get(key);
  }

  /**
   * Strings 데이터 다건을 조회하는 메소드
   * @param keys Strings 데이터의 key 목록
   * @return 다건 조회 결과
   */
  public List<String> multiGet(final Collection<String> keys) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    return valueOperations.multiGet(keys);
  }

  /**
   * 기존 값을 조회하고, 새로운 값을 저장하는 메소드
   *
   * @param key Strings 데이터의 key
   * @param value 새롭게 저장할 값
   * @return 기존 Strings 데이터
   */
  public String getAndSet(final String key, final String value) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

    return valueOperations.getAndSet(key, value);
  }

  /**
   * String 데이터에 추가 데이터를 이어붙이는 메소드
   *
   * @param key key Strings 데이터의 key
   * @param value value 새롭게 이어붙일 값
   * @return 이어붙인 문자열 전체 길이
   */
  public Integer append(final String key, final String value) {
    ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    return valueOperations.append(key, value);
  }

  /**
   * Strings 데이터 단일 건 삭제하는 메소드
   *
   * @param key Strings 데이터의 key
   * @return 삭제성공 여부
   */
  public boolean delete(final String key) {
    return stringRedisTemplate.delete(key);
  }

  /**
   * Strings 데이터 여러 건 삭제하는 메소드
   *
   * @param keys Strings 데이터 콜렉션
   * @return 삭제 성공한 데이터 갯수
   */
  public long delete(final Collection<String> keys) {
    return stringRedisTemplate.delete(keys);
  }


}
