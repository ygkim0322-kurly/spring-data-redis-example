package com.redis.example.redistemplate.service;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/** 레디스의 집합형 자료형 중 하나인 Set 형식에 대한 예제 서비스 */
@Service
public class ExampleSetOperationsService {
  @Resource private StringRedisTemplate stringRedisTemplate;

  /**
   * Set에 요소를 삽입하기 위한 메소드
   *
   * @param key Set의 키
   * @param value Set에 삽입하고자 하는 값
   * @return 삽입 성공 갯수
   */
  public long addValueToSet(final String key, final String value) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
    return setOperations.add(key, value);
  }

  /**
   * Set에 여러 요소를 삽입하기 위한 메소드
   *
   * @param key Set의 키
   * @param values Set에 삽입하고자 하는 값 다건
   * @return 삽입 성공 갯수
   */
  public long addValuesToSet(final String key, final String... values) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.add(key, values);
  }

  /**
   * Set 자료형의 길이를 구하는 메소드
   *
   * @param key Set의 키
   * @return Set의 길이
   */
  public long size(final String key) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.size(key);
  }

  /**
   * Set 자료형의 맴버값 모두를 조회하는 메소드
   *
   * @param key Set의 키
   * @return Set의 멤버
   */
  public Set<String> members(final String key) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.members(key);
  }

  /**
   * Set 자료형의 맴버 요소인지를 확인하는 메소드
   *
   * @param key
   * @param value
   * @return
   */
  public boolean isMember(final String key, final String value) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.isMember(key, value);
  }

  /**
   * 원본 Set의 특정 요소를 다른 Set으로 옮기기 위한 메소드
   *
   * @param originKey 원본 Set의 키
   * @param destKey 대상 Set의 키
   * @param value 옮겨갈 요소
   * @return 이동 성공여부
   */
  public boolean move(final String originKey, final String destKey, final String value) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.move(originKey, destKey, value);
  }

  /**
   * Set의 요소 중 하나를 랜덤하게 pop 하는 메소드
   *
   * @param key Set의 키
   * @return pop 되어 나온 요소
   */
  public String pop(final String key) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
    return setOperations.pop(key);
  }

  /**
   * Set의 요소 중 정해진 갯수만큼 랜덤하게 pop 하는 메소드
   *
   * @param key Set의 키
   * @param count 랜덤하게 pop 해올 요소의 갯수
   * @return pop 해온 요소 목목
   */
  public List<String> popWithCount(final String key, final long count) {

    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();
    return setOperations.pop(key, count);
  }

  /**
   * Set 자료형을 비교하여, 중복되지 않은 요소만 모은 데이터(= 차집합 데이터)를 조회하는 메소드
   *
   * @param keys 요소를 비교할 Set 자료형의 key 콜렉션
   * @return difference 연산 결과로써 조회한 요소 목록
   */
  public Set<String> difference(final Collection<String> keys) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.difference(keys);
  }

  /**
   * Set 자료형을 비교하여, 중복되지 않은 요소만 모은 데이터(= 차집합 데이터)를 조회하는 메소드
   *
   * @param key Set의 키
   * @param anotherKey 비교할 Set 자료형의 key
   * @return difference 연산 결과로써 조회한 요소 목록
   */
  public Set<String> difference(final String key, final String anotherKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.difference(key, anotherKey);
  }

  /**
   * Set 자료형을 비교하여, 중복되지 않은 요소만 모은 데이터(= 차집합 데이터)를 조회하는 메소드
   *
   * @param key Set의 키
   * @param otherKeys 비교할 Set 자료형의 key 콜렉션
   * @return difference 연산 결과로써 조회한 요소 목록
   */
  public Set<String> difference(final String key, final Collection<String> otherKeys) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.difference(key, otherKeys);
  }

  /**
   * Set 자료형을 비교하여, 중복되지 않은 요소만 모은 데이터(= 차집합 데이터)를 저장하는 메소드
   *
   * @param keys keys 요소를 비교할 Set 자료형의 key 콜렉션
   * @param destKey 차집합 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long differenceAndStore(final Collection<String> keys, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.differenceAndStore(keys, destKey);
  }

  /**
   * Set 자료형을 비교하여, 중복되지 않은 요소만 모은 데이터(= 차집합 데이터)를 저장하는 메소드
   *
   * @param key Set의 키
   * @param otherKey 비교할 다른 Set 자료형의 key
   * @param destKey 차집합 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long differenceAndStore(final String key, final String otherKey, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.differenceAndStore(key, otherKey, destKey);
  }

  /**
   * Set 자료형을 비교하여, 중복되지 않은 요소만 모은 데이터(= 차집합 데이터)를 저장하는 메소드
   *
   * @param key Set의 키
   * @param otherKeys 비교할 Set 자료형의 key 콜렉션
   * @param destKey 차집합 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long differenceAndStore(final String key, final Collection<String> otherKeys, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.differenceAndStore(key, otherKeys, destKey);
  }


  /**
   * Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 조회하는 메소드
   *
   * @param keys 요소를 비교할 Set 자료형의 key 콜렉션
   * @return intersect 연산 결과로써 조회한 요소 목록
   */
  public Set<String> intersect(final Collection<String> keys) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.intersect(keys);
  }

  /**
   * Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 조회하는 메소드
   *
   * @param key Set의 키
   * @param anotherKey 비교할 Set 자료형의 key
   * @return intersect 연산 결과로써 조회한 요소 목록
   */
  public Set<String> intersect(final String key, final String anotherKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.intersect(key, anotherKey);
  }

  /**
   * Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 조회하는 메소드
   *
   * @param key Set의 키
   * @param otherKeys 비교할 Set 자료형의 key 콜렉션
   * @return intersect 연산 결과로써 조회한 요소 목록
   */
  public Set<String> intersect(final String key, final Collection<String> otherKeys) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.intersect(key, otherKeys);
  }

  /**
   * Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 조회하는 메소드
   *
   * @param keys keys 요소를 비교할 Set 자료형의 key 콜렉션
   * @param destKey 교집합 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long intersectAndStore(final Collection<String> keys, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.intersectAndStore(keys, destKey);
  }

  /**
   * Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 저장하는 메소드
   *
   * @param key Set의 키
   * @param otherKey 비교할 다른 Set 자료형의 key
   * @param destKey 교집합 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long intersectAndStore(final String key, final String otherKey, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.intersectAndStore(key, otherKey, destKey);
  }

  /**
   * Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 저장하는 메소드
   *
   * @param key Set의 키
   * @param otherKeys 비교할 Set 자료형의 key 콜렉션
   * @param destKey 교집합 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long intersectAndStore(final String key, final Collection<String> otherKeys, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.intersectAndStore(key, otherKeys, destKey);
  }

  /**
   * Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합하여 조회(= 합집합) 하는 메소드
   *
   * @param keys 요소를 비교할 Set 자료형의 key 콜렉션
   * @return union 연산 결과로써 조회한 요소 목록
   */
  public Set<String> union(final Collection<String> keys) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.union(keys);
  }

  /**
   * Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합) 하는 메소드
   *
   * @param key Set의 키
   * @param anotherKey 비교할 Set 자료형의 key
   * @return union 연산 결과로써 조회한 요소 목록
   */
  public Set<String> union(final String key, final String anotherKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.union(key, anotherKey);
  }

  /**
   * Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 조회 하는 메소드
   *
   * @param key Set의 키
   * @param otherKeys 비교할 Set 자료형의 key 콜렉션
   * @return union 연산 결과로써 조회한 요소 목록
   */
  public Set<String> union(final String key, final Collection<String> otherKeys) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.union(key, otherKeys);
  }

  /**
   * Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param keys keys 요소를 비교할 Set 자료형의 key 콜렉션
   * @param destKey 병합한 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long unionAndStore(final Collection<String> keys, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.unionAndStore(keys, destKey);
  }

  /**
   * Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param key Set의 키
   * @param otherKey 비교할 다른 Set 자료형의 key
   * @param destKey 병합한 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long unionAndStore(final String key, final String otherKey, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.unionAndStore(key, otherKey, destKey);
  }

  /**
   * Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param key Set의 키
   * @param otherKeys 비교할 Set 자료형의 key 콜렉션
   * @param destKey 병합한 데이터를 저장할 Set의 키
   * @return 처리한 데이터 갯수
   */
  public Long unionAndStore(final String key, final Collection<String> otherKeys, final String destKey) {
    SetOperations<String, String> setOperations = stringRedisTemplate.opsForSet();

    return setOperations.unionAndStore(key, otherKeys, destKey);
  }

}
