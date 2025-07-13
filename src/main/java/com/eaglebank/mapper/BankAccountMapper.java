package com.eaglebank.mapper;

import com.eaglebank.model.BankAccount;
import com.eaglebank.model.BankAccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
  BankAccount toEntity(BankAccountResponse dto);

  BankAccountResponse toDto(BankAccount entity);
}