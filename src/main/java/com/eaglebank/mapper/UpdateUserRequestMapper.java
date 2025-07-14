package com.eaglebank.mapper;

import com.eaglebank.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UpdateUserRequestMapper {
  User toEntity(UpdateUserRequest dto);

  UpdateUserRequest toDto(User entity);

  default CreateUserRequestAddress toDto(UserAddress userAddress) {
    return Mappers.getMapper(UserAddressMapper.class).toDto(userAddress);
  }

  default UserAddress toEntity(CreateUserRequestAddress createUserRequestAddress) {
    return Mappers.getMapper(UserAddressMapper.class).toEntity(createUserRequestAddress);
  }
}
