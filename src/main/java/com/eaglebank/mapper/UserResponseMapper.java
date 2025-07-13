package com.eaglebank.mapper;

import com.eaglebank.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
  @Mapping(target = "password", ignore = true)
  User toEntity(UserResponse dto);

  UserResponse toDto(User entity);

  default CreateUserRequestAddress toDto(UserAddress userAddress) {
    return Mappers.getMapper(UserAddressMapper.class).toDto(userAddress);
  }

  default UserAddress toEntity(CreateUserRequestAddress userRequestAddress) {
    return Mappers.getMapper(UserAddressMapper.class).toEntity(userRequestAddress);
  }
}
