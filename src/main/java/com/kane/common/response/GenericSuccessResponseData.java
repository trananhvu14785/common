package com.kane.common.response;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericSuccessResponseData<T> {
  private int statusCode;
  private String statusMessage;
  private String cufxMessage;
  private Date timestamp;
  private long timeElapsedInMS;
  private T data;
}
