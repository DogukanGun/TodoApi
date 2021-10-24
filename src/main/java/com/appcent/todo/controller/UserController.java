package com.appcent.todo.controller;


import com.appcent.todo.model.dto.UserDto;
import com.appcent.todo.model.request.CreateUpdateUserRequest;
import com.appcent.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("user")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody CreateUpdateUserRequest createUpdateUserRequest){
        return userService.registerUser(createUpdateUserRequest);
    }
}
