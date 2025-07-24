package com.kane.common.dto.response;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponse {
  private String token;
  private String refreshToken;
  private Date expiredDate;
}
