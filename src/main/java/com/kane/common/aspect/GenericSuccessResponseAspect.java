package com.kane.common.aspect;

import com.kane.common.response.GenericSuccessResponseData;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class GenericSuccessResponseAspect {
  private static final String SUCCESS_MESSAGE = "success";

  @Around("execution(* com.kane..controller..*(..))")
  public Object wrapResponse(ProceedingJoinPoint pjp) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object result = pjp.proceed();

    if (result instanceof ResponseEntity<?> responseEntity) {
      Object body = responseEntity.getBody();

      if (body instanceof GenericSuccessResponseData) {
        return result;
      }

      GenericSuccessResponseData wrapped =
          GenericSuccessResponseData.builder()
              .statusCode(responseEntity.getStatusCode().value())
              .statusMessage(HttpStatus.OK.name())
              .cufxMessage(SUCCESS_MESSAGE)
              .timestamp(new Date())
              .timeElapsedInMS(System.currentTimeMillis() - startTime)
              .data(body)
              .build();

      return ResponseEntity.status(responseEntity.getStatusCode())
          .headers(responseEntity.getHeaders())
          .body(wrapped);
    }
    return result;
  }
}
