package com.redis.example.redistemplate.service;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** 레디스의 목록형 자료형 중 하나인 Lists 형식에 대한 예제 서비스 */
@Service
public class ExampleListOperationsService {
  @Resource private StringRedisTemplate stringRedisTemplate;

  /**
   * List 자료형의 오른쪽 끝에 새로운 값을 추가하는 메소드
   *
   * <p>입력받은 키값이 레디스에 존재하지 않을 경우, 해당 키로 새로운 리스트를 만든 다음에 이 작업을 진행한다.
   *
   * @param key List의 키
   * @param value List에 넣을 데이터
   * @return 리스트에 들어가 있는 값의 갯수
   */
  public long rightPush(String key, String value) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.rightPush(key, value);
  }

  /**
   * List 자료형의 오른쪽 끝에 새로운 값을 삽입하는 메소드
   *
   * <p>입력받은 키값이 레디스에 존재하지 않을 경우, 요청받은 값을 삽입하지 않는다.
   *
   * @param key List의 키
   * @param value List에 넣을 데이터
   * @return 리스트에 들어가 있는 값의 갯수 (리스트가 없을 경우 null 반환)
   */
  public Long rightPushIfPresent(String key, String value) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();

    return stringListOperations.rightPushIfPresent(key, value);
  }

  /**
   * 기준이 되는 피봇 값의 오른쪽(=피봇 값의 뒤)에 새로운 값을 삽입하는 메소드
   *
   * @param key List의 키
   * @param pivotValue 삽입 위치의 기준이 될 피봇 값
   * @param newValue 새로 삽입할 값
   * @return 리스트에 들어가 있는 값의 갯수
   */
  public long rightInsertValue(String key, String pivotValue, String newValue) {

    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.rightPush(key, pivotValue, newValue);
  }

  /**
   * List 자료형의 왼쪽 끝에 새로운 값을 삽입하는 메소드
   *
   * <p>입력받은 키값이 레디스에 존재하지 않을 경우, 해당 키로 새로운 리스트를 만든 다음에 이 작업을 진행한다.
   *
   * @param key List의 키
   * @param value List에 넣을 데이터
   * @return 리스트에 들어가 있는 값의 갯수
   */
  public long leftPush(String key, String value) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.leftPush(key, value);
  }

  /**
   * List 자료형의 왼쪽 끝에 새로운 값을 삽입하는 메소드
   *
   * <p>입력받은 키값이 레디스에 존재하지 않을 경우, 해당 키로 새로운 리스트를 만든 다음에 이 작업을 진행한다.
   *
   * @param key List의 키
   * @param value List에 넣을 데이터
   * @return 리스트에 들어가 있는 값의 갯수
   */
  public Long leftPushIfPresent(String key, String value) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.leftPushIfPresent(key, value);
  }

  /**
   * 기준이 되는 피봇 값의 왼쪽(=피봇 값의 앞)에 새로운 값을 삽입하는 메소드
   *
   * @param key List의 키
   * @param pivotValue 삽입 위치의 기준이 될 피봇 값
   * @param newValue 새로 삽입할 값
   * @return 리스트에 들어가 있는 값의 갯수
   */
  public long leftInsertValue(String key, String pivotValue, String newValue) {

    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.leftPush(key, pivotValue, newValue);
  }

  /**
   * List 자료형의 오른쪽 끝에 있는 값을 조회하여 가져온 후, 그 값을 삭제하는 메소드
   *
   * <p>리스트에 값이 없다면 해당 명령어는 작동하지 않으며, null을 리턴한다.
   *
   * @param key List의 키
   * @return pop 한 데이터 (존재하지 않을 경우 null 반환)
   */
  public String rightPop(String key) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.rightPop(key);
  }

  /**
   * List 자료형의 왼쪽 끝에 있는 값을 조회하여 가져온 후, 그 값을 삭제하는 메소드
   *
   * <p>리스트 키에 값이 없다면 해당 명령어는 작동하지 않으며, null을 리턴한다.
   *
   * @param key List의 키
   * @return pop 한 데이터 (존재하지 않을 경우 null 반환)
   */
  public String leftPop(String key) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.leftPop(key);
  }

  /**
   * {@link ExampleListOperationsService#rightPop(String)} 의 블로킹 버전 메소드
   *
   * <p>조회 시점에 이미 값이 존재할 경우 바로 그 값을 lpop 하여 기다린다. 만약 조회 시점에 값이 없다면, 지정한 시간동안 리스트에 값이 들어올 때 까지 기다리며,
   * 이 제한시간마저 지나면 {@link ExampleListOperationsService#rightPop(String)} 메소드와 동일하게 null을 리턴한다.
   *
   * <p>참고로 제한시간에 0을 넣으면, 시간 제한 없이 값이 들어올 때까지 무한정 기다린다.
   *
   * @param key List의 키
   * @param timeOut 제한시간
   * @param timeUnit 제한시간 단위
   * @return pop 한 데이터
   */
  public String blockingRightPop(String key, long timeOut, TimeUnit timeUnit) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.rightPop(key, timeOut, timeUnit);
  }

  /**
   * {@link ExampleListOperationsService#leftPop(String)} 의 블로킹 버전 메소드
   *
   * <p>조회 시점에 이미 값이 존재할 경우 바로 그 값을 lpop 하여 기다린다. 만약 조회 시점에 값이 없다면, 지정한 시간동안 리스트에 값이 들어올 때 까지 기다리며,
   * 이 제한시간마저 지나면 {@link ExampleListOperationsService#leftPop(String)} 메소드와 동일하게 null을 리턴한다.
   *
   * <p>참고로 제한시간에 0을 넣으면, 시간 제한 없이 값이 들어올 때까지 무한정 기다린다.
   *
   * @param key List의 키
   * @param timeOut 제한시간
   * @param timeUnit 제한시간 단위
   * @return pop 한 데이터
   */
  public String blockingLeftPop(String key, long timeOut, TimeUnit timeUnit) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.rightPop(key, timeOut, timeUnit);
  }

  /**
   * 원본 리스트 키의 값을 rpop한 후에 대상 리스트 키에 lpush 하는 메소드
   *
   * <p>원본 리스트 키에 값이 없다면 해당 메소드는 작동하지 않으며, null을 리턴한다.
   *
   * @param originKey 원본 List의 키
   * @param destKey 대상 List의 키
   * @return 원본 키에서 대상 키로 옮겨간 값. (옮긴 값이 없을 경우 null)
   */
  public String rightPopAndLeftPush(String originKey, String destKey) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();

    return stringListOperations.rightPopAndLeftPush(originKey, destKey);
  }

  /**
   * {@link ExampleListOperationsService#rightPopAndLeftPush(String, String)} 의 블로킹 버전 메소드.
   *
   * <p>정확히 말하자면 '{@link ExampleListOperationsService#blockingRightPop(String, long, TimeUnit)}' 과
   * '{@link ExampleListOperationsService#leftPush(String, String)}' 의 조합이라고 할 수 있다.
   *
   * <p>조회 시점에 원본 리스트 키에 이미 값이 존재한다면 바로 rpop을 하고, 그 값을 대상 리스트 키에 lpush한다. 만약 조회 시점에 원본 리스트 키에 값이
   * 없다면, 지정한 시간동안 원본 리스트 키에 값이 들어올 때까지 기다리며, 이 제한시간 마저 지나면, {@link
   * ExampleListOperationsService#rightPopAndLeftPush(String, String)} 메소드와 동일하게 작동한다.
   *
   * <p>참고로 제한시간에 0을 넣으면, 시간 제한 없이 값이 들어올 때까지 무한정 기다린다.
   *
   * @param originKey 원본 List의 키
   * @param destKey 대상 List의 키
   * @param timeOut 제한시간
   * @param timeUnit 제한시간 단위
   */
  public String blockingRightPopAndLeftPush(
      String originKey, String destKey, long timeOut, TimeUnit timeUnit) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();

    return stringListOperations.rightPopAndLeftPush(originKey, destKey, timeOut, timeUnit);
  }

  /**
   * List 내 특정 번지에 있는 값을 반환하는 메소드
   *
   * <p>입력받은 번지수에 값이 없다면 null을 반환한다.
   *
   * <p>이 메소드를 활용해 조회 하더라도, 해당 리스트의 값은 삭제되지 않는다.
   *
   * @param key List의 키
   * @param index 찾고자 하는 번지수
   * @return 번지수에 존재하는 값
   */
  public String index(String key, long index) {

    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();

    return stringListOperations.index(key, index);
  }

  /**
   * List 자료형 내의 특정 번지수에 특정 값을 덮어씌우는 메소드
   *
   * @param key List의 키
   * @param index 덮어씌우고자 하는 번지수
   * @param value 덮어씌우고자 하는 값
   */
  public void setValue(String key, long index, String value) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    stringListOperations.set(key, index, value);
  }

  /**
   * List 자료형의 길이를 구하는 메소드
   *
   * <p>존재하지 않는 List의 길이를 조회할 경우, null이 반환됨
   *
   * @param key List의 키
   * @return List 자료형의 길이
   */
  public Long listSize(String key) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    return stringListOperations.size(key);
  }

  /**
   * List 자료형의 번지수 범위 내에 있는 모든 자료를 조회하는 메소드
   *
   * @param key List의 키
   * @param start 번지 수 범위조건 최소값
   * @param end 번지 수 범위조건 최대값
   * @return 조건에 해당하는 값의 목록
   */
  public List<String> listRange(String key, long start, long end) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();

    return stringListOperations.range(key, start, end);
  }

  /**
   * List 자료형 내의 값 중, 번지 수 조건에 맞지 않는 값은 삭제하는 메소드
   * @param key List의 키
   * @param start 번지 수 범위조건 최소값
   * @param end 번지 수 범위조건 최대값
   */
  public void trim(String key, long start, long end) {
    ListOperations<String, String> stringListOperations = stringRedisTemplate.opsForList();
    stringListOperations.trim(key, start, end);
  }
}
