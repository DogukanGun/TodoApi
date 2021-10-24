package com.appcent.todo.service;


import com.appcent.todo.model.dto.UserDto;
import com.appcent.todo.model.entity.User;
import com.appcent.todo.model.request.CreateUpdateUserRequest;
import com.appcent.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.appcent.todo.model.mapper.UserMapper.USER_MAPPER;

import com.appcent.todo.model.request.CreateUpdateUserRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SecurityService securityService;
    private final UserRepository userRepository;
    public UserDto registerUser(CreateUpdateUserRequest createUpdateUserRequest){
        User user = USER_MAPPER.createUser(createUpdateUserRequest);

        String hashedPassword = securityService.generateHashedPassword(user.getPassword());
        user.setPassword(hashedPassword);

        return  USER_MAPPER.convertToUserDto(userRepository.save(user));

    }
}
