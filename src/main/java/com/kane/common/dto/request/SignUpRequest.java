package com.kane.common.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
  private String username;
  private String password;
  private String nameProfile;
}
