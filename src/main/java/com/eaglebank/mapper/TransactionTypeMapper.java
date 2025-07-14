package com.eaglebank.mapper;

import com.eaglebank.model.TransactionResponse;
import com.eaglebank.model.TransactionType;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface TransactionTypeMapper {
  @ValueMapping(target = "deposit", source = "DEPOSIT")
  @ValueMapping(target = "withdraw", source = "WITHDRAWAL")
  TransactionType toEntity(TransactionResponse.TypeEnum dto);

  @ValueMapping(source = "deposit", target = "DEPOSIT")
  @ValueMapping(source = "withdraw", target = "WITHDRAWAL")
  TransactionResponse.TypeEnum toDto(TransactionType entity);
}
