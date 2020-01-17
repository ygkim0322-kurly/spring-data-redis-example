package com.redis.example.redistemplate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.example.redistemplate.dto.ValueDto;
import com.redis.example.redistemplate.service.ExampleStringsOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ExampleStringsOperationsRestController {
  private final ExampleStringsOperationsService exampleStringsOperationsService;
  private final ObjectMapper objectMapper;

  @PostMapping("/simple-strings")
  public String saveNewSimpleStrings() {
    String uuidString = UUID.randomUUID().toString();
    exampleStringsOperationsService.setStringValue(uuidString, getRandomString(50));
    return uuidString;
  }

  @GetMapping("/simple-strings/{uuid}")
  public String getSimpleStrings(@PathVariable String uuid) {
    return exampleStringsOperationsService.getStringValue(uuid);
  }

  @DeleteMapping("/simple-strings/{uuid}")
  public boolean deleteSimpleStrings(@PathVariable String uuid) {
    return exampleStringsOperationsService.deleteStringValue(uuid);
  }

  @PostMapping("/dto-strings")
  public String saveNewDtoStrings() {
    String uuidString = UUID.randomUUID().toString();
    ValueDto.ValueDtoBuilder valueDtoBuilder = ValueDto.builder();

    valueDtoBuilder.firstExampleValue(getRandomString(20));
    valueDtoBuilder.secondExampleValue(getRandomString(20));
    exampleStringsOperationsService.setValueDtoString(uuidString, valueDtoBuilder.build());
    return uuidString;
  }

  @GetMapping("/dto-strings/{uuid}")
  public String getDtoStrings(@PathVariable String uuid) throws JsonProcessingException {
    ValueDto exampleDto = exampleStringsOperationsService.getValueDtoString(uuid);

    return objectMapper.writerWithDefaultPrettyPrinter()
        .writeValueAsString(exampleDto);
  }

  @DeleteMapping("/dto-strings/{uuid}")
  public boolean deleteDtoStrings(@PathVariable String uuid) {
    return exampleStringsOperationsService.deleteValueDtoString(uuid);
  }


  private static String getRandomString(int length) {
    StringBuffer buffer = new StringBuffer();
    Random random = new Random();

    String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");

    for (int i = 0; i < length; i++) {
      buffer.append(chars[random.nextInt(chars.length)]);
    }
    return buffer.toString();
  }
}
