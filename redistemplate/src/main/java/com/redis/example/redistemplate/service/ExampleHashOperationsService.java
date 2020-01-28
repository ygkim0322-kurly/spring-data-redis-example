package com.redis.example.redistemplate.service;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExampleHashOperationsService {
  @Resource private StringRedisTemplate stringRedisTemplate;

  /**
   * Hash 자료형 내에 field 명이 존재유무를 확인하는 메소드
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @return field 존재유무
   */
  public boolean hasHashKey(final String key, final String hashKey) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.hasKey(key, hashKey);
  }

  /**
   * Hash 자료형 내에 field 명으로 존재하는 value의 길이값을 구하는 메소드
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @return value의 길이
   */
  public long lengthOfValue(final String key, final String hashKey) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.lengthOfValue(key, hashKey);
  }

  /**
   * Hash 자료형 내에 존재하는 field가 몇개인지를 조회하는 메소드
   *
   * @param key Hash 자료형의 key
   * @return field 갯수
   */
  public long size(final String key) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.size(key);
  }

  /**
   * Hash 자료형 내에, 특정 조건에 부합하는 데이터를 조건검색 하는 메소드
   *
   * @param key Hash 자료형의 key
   * @param scanOptions 검색을 하기 위한 조건객체
   * @return 조건에 부합하는 데이터 목록
   */
  public Cursor<Map.Entry<String, String>> scan(final String key, final ScanOptions scanOptions) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.scan(key, scanOptions);
  }

  /**
   * Hash 자료형 내에 존재하는 모든 field명을 조회하는 메소드
   *
   * @param key Hash 자료형의 key
   * @return Hash 자료형 내에 존재하는 field 명
   */
  public Set<String> keys(final String key) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.keys(key);
  }

  /**
   * Hash 자료형 내, 특정 field 명으로 존재하는 데이터를 조회하는 메소드
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @return 조건에 부합하는 데이터
   */
  public String get(final String key, final String hashKey) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.get(key, hashKey);
  }

  /**
   * Hash 자료형 내, 특정 field 명으로 존재하는 데이터를 조회하는 메소드 (field명 매개변수가 콜렉션임)
   *
   * @param key Hash 자료형의 key
   * @param hashKeys Hash의 field 이름이 담긴 콜렉션
   * @return 조건에 부합하는 값 목록
   */
  public List<String> multiGet(final String key, final Collection<String> hashKeys) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.multiGet(key, hashKeys);
  }

  /**
   * Hash 자료형 내에 존재하는 모든 field - value 쌍 데이터를 조회하는 메소드
   *
   * @param key Hash 자료형의 key
   * @return 조건에 부합하는 데이터 field - value Map 객체
   */
  public Map<String, String> entries(final String key) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.entries(key);
  }

  /**
   * Hash 자료형 내에 존재하는 field - value 데이터를 삭제하기 위한 메소드
   *
   * @param key Hash 자료형의 key
   * @param hashKeys Hash의 field 이름들
   * @return 조건에 부합하는 데이터
   */
  public long deleteHashKeys(final String key, final String... hashKeys) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.delete(key, hashKeys);
  }

  /**
   * Hash 자료형 내, 특정 field 명으로 새로운 값을 삽입하는 매소드. 동일한 field명으로 데이터가 존재할 경우, 새로운 값으로 덮어쓰는 메소드이다.
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @param value 삽입할 데이터
   */
  public void put(final String key, final String hashKey, final String value) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    stringHashOperations.put(key, hashKey, value);
  }

  /**
   * Hash 자료형 내, 특정 field 명으로 새로운 값을 삽입하는 매소드. 동일한 field명으로 데이터가 존재하지 않을 경우에만 삽입하는 메소드이다.
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @param value 삽입할 데이터
   * @return 삽입 성공 여부
   */
  public boolean putIfAbsent(final String key, final String hashKey, final String value) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();

    return stringHashOperations.putIfAbsent(key, hashKey, value);
  }

  /**
   * Hash 자료형 내, 특정 field 명으로 새로운 값을 삽입하는 매소드. 동일한 field명으로 데이터가 존재할 경우, 새로운 값으로 덮어쓰는 메소드이다.
   *
   * @param key Hash 자료형의 key
   * @param hashKeysAndValuesMap 삽입할 field name - value 쌍 데이터
   */
  public void putAll(final String key, final Map<String, String> hashKeysAndValuesMap) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    stringHashOperations.putAll(key, hashKeysAndValuesMap);
  }

  /**
   * Hash 자료형 내, 특정 field 명에 있는 숫자 데이터를 원하는 만큼 가산시키기 위한 메소드. 감산을 원할 시 음수 값을 전달하면 된다.
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @param increaseValue 증가시킬 값
   * @return 증가 완료시킨 최종 데이터
   */
  public long increase(final String key, final String hashKey, final long increaseValue) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.increment(key, hashKey, increaseValue);
  }

  /**
   * Hash 자료형 내, 특정 field 명에 있는 숫자 데이터를 원하는 만큼 가산시키기 위한 메소드. 감산을 원할 시 음수 값을 전달하면 된다.
   *
   * @param key Hash 자료형의 key
   * @param hashKey Hash의 field 이름
   * @param increaseValue 증가시킬 값
   * @return 증가 완료시킨 최종 데이터
   */
  public double increase(final String key, final String hashKey, final double increaseValue) {
    HashOperations<String, String, String> stringHashOperations = stringRedisTemplate.opsForHash();
    return stringHashOperations.increment(key, hashKey, increaseValue);
  }
}
