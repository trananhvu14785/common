package com.kane.common.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {
  @Id @GeneratedValue private UUID uuid;

  @Column(nullable = false, name = "first_name")
  private String firstName;

  @Column(nullable = false, name = "last_name")
  private String lastName;

  @Email
  @Column(nullable = false, unique = true)
  private String email;

  @Email
  @Column(nullable = false, unique = true, name = "backup_email")
  private String backupEmail;

  @Column(nullable = false, unique = true, name = "mobile_phone_number")
  private String mobilePhoneNumber;

  @Column(nullable = false, name = "is_active")
  private boolean isActive;

  @Column(nullable = false, unique = true)
  private String ssn;

  @Column(name = "created_time")
  private OffsetDateTime createdTime = OffsetDateTime.now();

  @Column(name = "updated_time")
  private OffsetDateTime updatedTime = OffsetDateTime.now();

  @OneToOne(cascade = CascadeType.ALL)
  private Address address;
}
