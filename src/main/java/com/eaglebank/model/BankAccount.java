package com.eaglebank.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @ManyToOne
  @JoinColumn(
      name = "userId",
      referencedColumnName = "Id",
      nullable = false
  )
  private User user;

  @NotBlank
  private String name;

  @NotBlank
  @Enumerated(EnumType.STRING)
  private BankAccountType accountType;

  @Valid
  @NotNull
  private BigDecimal balance;

  @NotBlank
  private String currency;

  @Valid
  @NotNull
  private OffsetDateTime createdTimestamp;

  @Valid
  @NotNull
  private OffsetDateTime updateTimestamp;
}
