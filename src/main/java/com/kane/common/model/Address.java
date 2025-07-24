package com.kane.common.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
  @Id @GeneratedValue private UUID uuid;

  @Column(nullable = false, name = "address_line1")
  private String addressLine1;

  @Column(nullable = false, name = "address_line2")
  private String addressLine2;

  @Column(nullable = false, unique = true, name = "zip_code")
  private String zipCode;

  @Column(nullable = false)
  private String city;

  @OneToOne(mappedBy = "address")
  private Customer customer;
}
