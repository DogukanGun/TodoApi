package com.appcent.todo.controller;


import com.appcent.todo.model.dto.UserDto;
import com.appcent.todo.model.request.CreateUpdateUserRequest;
import com.appcent.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<UserDto> getAllUsers(){
       return userService.getUsers();
    }
}
