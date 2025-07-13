package com.eaglebank.mapper;

import com.eaglebank.model.Transaction;
import com.eaglebank.model.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
  Transaction toEntity(TransactionResponse dto);

  TransactionResponse toDto(Transaction entity);
}
