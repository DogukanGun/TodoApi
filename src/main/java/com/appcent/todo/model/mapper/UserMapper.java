package com.appcent.todo.model.mapper;

import com.appcent.todo.model.dto.UserDto;
import com.appcent.todo.model.entity.User;
import com.appcent.todo.model.request.CreateUpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User createUser(CreateUpdateUserRequest createUpdateUserRequest);

    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    List<UserDto> convertToUserDtoList(List<User> users);
}
