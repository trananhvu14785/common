package com.kane.common.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SignInRequest {
  private String clientId;
  private String clientSecret;
  private String username;
  private String password;
}
