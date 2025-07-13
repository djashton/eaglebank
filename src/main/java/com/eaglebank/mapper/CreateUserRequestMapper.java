package com.eaglebank.mapper;

import com.eaglebank.model.CreateUserRequest;
import com.eaglebank.model.CreateUserRequestAddress;
import com.eaglebank.model.User;
import com.eaglebank.model.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreateUserRequestMapper {
  User toEntity(CreateUserRequest dto);

  CreateUserRequest toDto(User entity);

  default CreateUserRequestAddress toDto(UserAddress userAddress) {
    return Mappers.getMapper(UserAddressMapper.class).toDto(userAddress);
  }

  default UserAddress toEntity(CreateUserRequestAddress createUserRequestAddress) {
    return Mappers.getMapper(UserAddressMapper.class).toEntity(createUserRequestAddress);
  }
}
