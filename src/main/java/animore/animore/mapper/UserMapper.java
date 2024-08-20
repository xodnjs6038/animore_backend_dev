package animore.animore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import animore.animore.dto.user.UserDto;
import animore.animore.dto.user.request.CreateUserDto;
import animore.animore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto toDto(User user);

	User toEntity(UserDto userDto);

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
