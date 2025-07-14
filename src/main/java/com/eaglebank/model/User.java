package com.eaglebank.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private String id;

  @NotBlank
  private String name;

  @Embedded
  @Valid
  @NotNull(message = "Address is required")
  private UserAddress address;

  @NotBlank
  private String phoneNumber;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Valid
  @NotNull
  private OffsetDateTime createdTimestamp;

  @Valid
  @NotNull
  private OffsetDateTime updatedTimestamp;
}
