package com.eaglebank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "bank_accounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

  @Id
  private String accountNumber;

  @NotBlank
  private String sortCode;

  @NotBlank
  private String name;

  @NotBlank
  @Enumerated(EnumType.STRING)
  private BankAccountType accountType;

  private BigDecimal balance;

  private String currency;

  private OffsetDateTime createdTimestamp;

  private OffsetDateTime updateTimestamp;

}
