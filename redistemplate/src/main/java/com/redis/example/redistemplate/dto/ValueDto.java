package com.redis.example.redistemplate.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ValueDto {
  private String firstExampleValue;
  private String secondExampleValue;
}
