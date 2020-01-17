package com.redis.example.redistemplate.service;

import com.redis.example.redistemplate.dto.ValueDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class ExampleValueDtoStringsOperationsService {
  @Resource
  private RedisTemplate<String, ValueDto> customizedValueDtoRedisTemplate;

  /**
   * DTO를 Strings 데이터로 직렬화하여 저장
   * @param key 직렬화 후 저장할 DTO Strings 데이터 키
   * @param valueDto 직렬화할 DTO 데이터
   */
  public void setValueDtoString(final String key, final ValueDto valueDto) {
    ValueOperations<String, ValueDto> valueOperations =
        customizedValueDtoRedisTemplate.opsForValue();
    valueOperations.set(key, valueDto);
  }

  /**
   * 직렬화 저장한 Strings 데이터 조회
   * @param key 직렬화 후 저장한 DTO Strings의 데이터 키
   * @return 조회 후 역직렬화한 DTO 객체
   */
  public ValueDto getValueDtoString(final String key) {
    ValueOperations<String, ValueDto> valueOperations =
        customizedValueDtoRedisTemplate.opsForValue();
    return valueOperations.get(key);
  }

  /**
   * 직렬화 저장한 Strings 데이터 단일 건 삭제
   * @param key 직렬화 후 저장한 DTO Strings의 데이터 키
   * @return 삭제 성공여부
   */
  public boolean deleteValueDtoString(final String key) {
    return customizedValueDtoRedisTemplate.delete(key);
  }

  /**
   * 직렬화 저장한 Strings 데이터 여러 건 삭제
   * @param keys 직렬화 후 저장한 DTO Strings 데이터 키 콜렉션
   * @return 삭제 성공한 데이터 갯수
   */
  public long deleteValueDtoStrings(final Collection<String> keys) {

    return customizedValueDtoRedisTemplate.delete(keys);
  }
}
