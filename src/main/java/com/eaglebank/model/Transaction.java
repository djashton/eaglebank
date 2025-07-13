package com.eaglebank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  @Id
  private String id;

  @NotBlank
  private BigDecimal amount;

  @NotBlank
  private String currency;

  @NotBlank
  private TransactionType transactionType;

  private String reference;

  private String userId;

  @NotNull
  private OffsetDateTime createdTimestamp;
}
