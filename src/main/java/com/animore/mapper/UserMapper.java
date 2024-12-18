package com.animore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.animore.dto.user.UserDto;
import com.animore.dto.user.request.CreateUserDto;
import com.animore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDto toDto(User user);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "type", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "userCode", ignore = true)
	@Mapping(target = "useCar", ignore = true)
	@Mapping(target = "subwayId", ignore = true)
	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "updatedDate", ignore = true)
	User toEntity(CreateUserDto createUserDto);

}
