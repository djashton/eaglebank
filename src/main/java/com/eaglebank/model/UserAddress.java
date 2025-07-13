package com.eaglebank.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
  @NotBlank
  private String line1;

  private String line2;

  private String line3;

  @NotBlank
  private String town;

  @NotBlank
  private String county;

  @NotBlank
  private String postcode;
}
