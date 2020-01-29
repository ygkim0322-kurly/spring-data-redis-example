package com.redis.example.redistemplate.service;

import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

@Service
public class ExampleSortedSetOperationsService {
  @Resource private StringRedisTemplate stringRedisTemplate;

  /**
   * Sorted Set 자료형에 요소를 삽입하기 위한 메소드
   *
   * @param key Sorted Set의 키
   * @param value Sorted Set에 삽입할 값
   * @param score Sorted Set에 삽입할 값의 score
   * @return 삽입 성공여부
   */
  public boolean addValueToSet(final String key, final String value, final double score) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.add(key, value, score);
  }

  /**
   * Sorted Set 자료형에 요소를 삽입하기 위한 메소드
   *
   * @param key Sorted Set의 키
   * @param tuples Sorted Set에 삽입할 값과 score 쌍
   * @return 삽입한 요소의 갯수
   */
  public long addValuesToSet(
      final String key, final Set<ZSetOperations.TypedTuple<String>> tuples) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.add(key, tuples);
  }

  /**
   * Sorted Set 자료형 내 요소의 score 값을 가산시키기 위한 메소드
   *
   * @param key key Sorted Set의 키
   * @param value Sorted Set에 삽입되어 있는 요소의 값
   * @param score 가산할 score 값 (감산이 필요한 경우 음수값을 대입)
   * @return
   */
  public double incrementScore(final String key, final String value, final double score) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.incrementScore(key, value, score);
  }

  /**
   * Sorted Set 자료형 내 요소의 전체 갯수를 구하는 메소드
   *
   * @param key Sorted Set의 키
   * @return Sorted Set의 길이
   */
  public long size(final String key) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.size(key);
  }

  /**
   * Sorted Set 자료형 내 요소 중 score 조건에 해당하는 갯수를 구하는 메소드
   *
   * @param key Sorted Set의 키
   * @param minScore 집계 대상 요소의 최소 score
   * @param maxScore 집계 대상 요소의 최대 score
   * @return 집계 데이터
   */
  public long count(final String key, final double minScore, final double maxScore) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.count(key, minScore, maxScore);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 인덱스 조건 기준 조건 검색 메소드 (오름차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 인덱스 (최소값은 0)
   * @param max 최대 인덱스 (전체검색이 필요한 경우: 최소 인덱스값에는 0, 최대 인덱스 값에는 -1 대입 )
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> range(final String key, final long min, final long max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.range(key, min, max);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 메소드 (오름차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> rangeByScore(final String key, final double min, final double max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.rangeByScore(key, min, max);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 메소드 (오름차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @param offset SQL의 오프셋과 기능 동일
   * @param count SQL의 count(혹은 top n) 과 기능 동일
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> rangeByScore(
      final String key, final double min, final double max, final long offset, final long count) {

    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.rangeByScore(key, min, max, offset, count);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 메소드 (오름차순)
   *
   * <p>Range 객체를 활용하여 더욱 세밀한 조건설정이 가능한 메소드
   *
   * @param key Sorted Set의 키
   * @param range 최소-최대 인덱스 조건설정 객체
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> rangeByLex(final String key, final RedisZSetCommands.Range range) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.rangeByLex(key, range);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 (오름차순)
   *
   * <p>Range, Limit 객체를 활용하여 더욱 세밀한 조건설정이 가능한 메소드
   *
   * @param key Sorted Set의 키
   * @param range 최소-최대 인덱스 조건설정 객체
   * @param limit SQL 기능 중 offset, count(top N) 등의 조건설정 객체
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> rangeByLex(
      final String key, final RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.rangeByLex(key, range, limit);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 (오름차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @return 검색 결과 (요소 - 스코어 쌍 목록)
   */
  @Nullable
  public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(
      final String key, final double min, final double max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.rangeByScoreWithScores(key, min, max);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 (오름차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @param offset SQL의 오프셋과 기능 동일
   * @param count SQL의 count(혹은 top n) 과 기능 동일
   * @return 검색 결과 (요소 - 스코어 쌍 목록)
   */
  @Nullable
  public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(
      final String key, final double min, final double max, final long offset, final long count) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.rangeByScoreWithScores(key, min, max, offset, count);
  }


  /**
   * Sorted Set 자료형 내 요소 정보 중 인덱스 조건 기준 조건 검색 메소드 (내림차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 인덱스 (최소값은 0)
   * @param max 최대 인덱스 (전체검색이 필요한 경우: 최소 인덱스값에는 0, 최대 인덱스 값에는 -1 대입 )
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> reverseRange(final String key, final long min, final long max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.reverseRange(key, min, max);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 메소드 (내림차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> reverseRangeByScore(final String key, final double min, final double max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.reverseRangeByScore(key, min, max);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 메소드 (오름차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @param offset SQL의 오프셋과 기능 동일
   * @param count SQL의 count(혹은 top n) 과 기능 동일
   * @return 검색결과 (요소 목록)
   */
  @Nullable
  public Set<String> reverseRangeByScore(
      final String key, final double min, final double max, final long offset, final long count) {

    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.reverseRangeByScore(key, min, max, offset, count);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 (내림차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @return 검색 결과 (요소 - 스코어 쌍 목록)
   */
  @Nullable
  public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(
      final String key, final double min, final double max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.reverseRangeByScoreWithScores(key, min, max);
  }

  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색 (내림차순)
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @param offset SQL의 오프셋과 기능 동일
   * @param count SQL의 count(혹은 top n) 과 기능 동일
   * @return 검색 결과 (요소 - 스코어 쌍 목록)
   */
  @Nullable
  public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(
      final String key, final double min, final double max, final long offset, final long count) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.rangeByScoreWithScores(key, min, max, offset, count);
  }

  /**
   * Sorted Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 저장하는 메소드
   *
   * @param key Sorted Set의 키
   * @param otherKey 비교할 다른 Sorted Set 자료형의 key
   * @param destKey 교집합 데이터를 저장할 Sorted Set의 키
   * @return 처리한 데이터 갯수
   */
  @Nullable
  public Long intersectAndStore(final String key, final String otherKey, final String destKey) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.intersectAndStore(key, otherKey, destKey);
  }


  /**
   * Sorted Set 자료형 내 특정 요소의 오름차순 순위를 조회하는 메소드
   *
   * @param key Sorted Set의 키
   * @param value 순위를 조회하고 싶은 요소
   * @return 해당 요소의 순위
   */
  @Nullable
  public Long rank(final String key, final String value) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.rank(key, value);
  }


  /**
   * Sorted Set 자료형 내 특정 요소의 내림차순 순위를 조회하는 메소드
   *
   * @param key Sorted Set의 키
   * @param value 순위를 조회하고 싶은 요소
   * @return 해당 요소의 순위
   */
  @Nullable
  public Long reverseRank(final String key, final String value) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.reverseRank(key, value);
  }


  /**
   * Sorted Set 자료형 내 특정 요소를 삭제하는 메소드
   *
   * @param key Sorted Set의 키
   * @param values 삭제하고 대상 요소
   * @return 삭제작업 처리 갯수
   */
  @Nullable
  public Long remove(final String key, final String ...values) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.remove(key, values);
  }


  /**
   * Sorted Set 자료형 내 요소 정보 중 인덱스 조건 기준 검색조건에 해당하는 값을 삭제하는 메소드
   *
   * @param key Sorted Set의 키
   * @param min 최소 인덱스 (최소값은 0)
   * @param max 최대 인덱스 (전체검색이 필요한 경우: 최소 인덱스값에는 0, 최대 인덱스 값에는 -1 대입 )
   * @return 삭제작업 처리 갯수
   */
  @Nullable
  public Long removeRange(final String key, final long min, final long max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.removeRange(key, min, max);
  }


  /**
   * Sorted Set 자료형 내 요소 정보 중 스코어 조건 기준 검색조건에 해당하는 값을 삭제하는 메소드
   *
   * @param key Sorted Set의 키
   * @param min 최소 score
   * @param max 최대 score
   * @return 삭제작업 처리 갯수
   */
  @Nullable
  public Long removeRangeByScore(final String key, final long min, final long max) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.removeRangeByScore(key, min, max);
  }

  /**
   * Sorted Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 저장하는 메소드
   *
   * @param key Sorted Set의 키
   * @param otherKeys 비교할 Sorted Set 자료형의 키 콜렉션
   * @param destKey 교집합 데이터를 저장할 Sorted Set의 키
   * @return 처리한 데이터 갯수
   */
  @Nullable
  public Long intersectAndStore(
      final String key, final Collection<String> otherKeys, final String destKey) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.intersectAndStore(key, otherKeys, destKey);
  }

  /**
   * Sorted Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 저장하는 메소드
   *
   * @param key Sorted Set의 키
   * @param otherKeys 비교할 Sorted Set 자료형의 키 콜렉션
   * @param destKey 교집합 데이터를 저장할 Sorted Set의 키
   * @param aggregate 중복된 요소의 score를 어떻게 처리할 것인지 정해주는 열거형 객체 (이걸 대입 안할경우 기본값은 SUM)
   * @return 처리 완료한 요소의 갯수
   */
  @Nullable
  public Long intersectAndStore(
      final String key,
      final Collection<String> otherKeys,
      final String destKey,
      final RedisZSetCommands.Aggregate aggregate) {

    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.intersectAndStore(key, otherKeys, destKey, aggregate);
  }

  /**
   * Sorted Set 자료형을 비교하여, 중복된 요소만 모은 데이터(= 교집합 데이터)를 저장하는 메소드
   *
   * @param key Sorted Set의 키
   * @param otherKeys 비교할 Sorted Set 자료형의 키 콜렉션
   * @param destKey 교집합 데이터를 저장할 Sorted Set의 키
   * @param aggregate 중복된 요소의 score를 어떻게 처리할 것인지 정해주는 열거형 객체 (이걸 대입 안할경우 기본값은 SUM)
   * @param weights 비교할 Sorted Set 자료형별 요소의 score에 곱셈으로 가중치를 줄 값 (지정하지 않을경우 기본값은 1)
   * @return 처리 완료한 요소의 갯수
   */
  @Nullable
  public Long intersectAndStore(
      final String key,
      final Collection<String> otherKeys,
      final String destKey,
      final RedisZSetCommands.Aggregate aggregate,
      final RedisZSetCommands.Weights weights) {

    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();
    return zSetOperations.intersectAndStore(key, otherKeys, destKey, aggregate, weights);
  }

  /**
   * Sorted Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param key Sorted Set 키
   * @param otherKey 비교할 다른 Sorted Set 자료형의 key
   * @param destKey 병합한 데이터를 저장할 Sorted Set 키
   * @return 처리한 데이터 갯수
   */
  @Nullable
  public Long unionAndStore(final String key, final String otherKey, final String destKey) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.unionAndStore(key, otherKey, destKey);
  }

  /**
   * Sorted Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param key Sorted Set 키
   * @param otherKeys 비교할 Sorted Set 자료형의 key 콜렉션
   * @param destKey 병합한 데이터를 저장할 Sorted Set 키
   * @return 처리한 데이터 갯수
   */
  @Nullable
  public Long unionAndStore(
      final String key, final Collection<String> otherKeys, final String destKey) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.unionAndStore(key, otherKeys, destKey);
  }

  /**
   * Sorted Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param key Sorted Set 키
   * @param otherKeys 비교할 Sorted Set 자료형의 key 콜렉션
   * @param destKey 병합한 데이터를 저장할 Sorted Set 키
   * @param aggregate 중복된 요소의 score를 어떻게 처리할 것인지 정해주는 열거형 객체 (이걸 대입 안할경우 기본값은 SUM)
   * @return 처리한 데이터 갯수
   */
  @Nullable
  public Long unionAndStore(
      final String key,
      final Collection<String> otherKeys,
      final String destKey,
      final RedisZSetCommands.Aggregate aggregate) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.unionAndStore(key, otherKeys, destKey);
  }

  /**
   * Sorted Set 자료형을 비교하여, 모든 Set의 값을 하나로 병합한 데이터(= 합집합)를 저장하는 메소드
   *
   * @param key Sorted Set 키
   * @param otherKeys 비교할 Sorted Set 자료형의 key 콜렉션
   * @param destKey 병합한 데이터를 저장할 Sorted Set 키
   * @param aggregate 중복된 요소의 score를 어떻게 처리할 것인지 정해주는 열거형 객체 (이걸 대입 안할경우 기본값은 SUM)
   * @param weights 비교할 Sorted Set 자료형별 요소의 score에 곱셈으로 가중치를 줄 값 (지정하지 않을경우 기본값은 1)
   * @return 처리한 데이터 갯수
   */
  @Nullable
  public Long unionAndStore(
      final String key,
      final Collection<String> otherKeys,
      final String destKey,
      final RedisZSetCommands.Aggregate aggregate,
      final RedisZSetCommands.Weights weights) {
    ZSetOperations<String, String> zSetOperations = stringRedisTemplate.opsForZSet();

    return zSetOperations.unionAndStore(key, otherKeys, destKey);
  }
}
