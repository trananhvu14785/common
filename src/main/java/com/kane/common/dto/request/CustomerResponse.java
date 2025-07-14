package com.kane.common.dto.request;

import com.kane.common.model.Address;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {
  private UUID uuid;

  private String firstName;

  private String lastName;

  private String email;

  private String backupEmail;

  private String mobilePhoneNumber;

  private boolean isActive;

  private String ssn;

  private OffsetDateTime createdTime;

  private OffsetDateTime updatedTime;

  private Address address;
}
