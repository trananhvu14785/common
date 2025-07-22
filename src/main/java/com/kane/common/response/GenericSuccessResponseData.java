package com.kane.common.response;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericSuccessResponseData {
  private int statusCode;
  private String statusMessage;
  private String cufxMessage;
  private Date timestamp;
  private Long timeElapsedInMS;
  private Object data;
}
