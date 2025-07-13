package com.eaglebank.mapper;

import com.eaglebank.model.CreateUserRequestAddress;
import com.eaglebank.model.UserAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {
  UserAddress toEntity(CreateUserRequestAddress dto);

  CreateUserRequestAddress toDto(UserAddress entity);
}
