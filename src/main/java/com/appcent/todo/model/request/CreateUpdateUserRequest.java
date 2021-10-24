package com.appcent.todo.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateUserRequest {

    private String name;

    private String surname;

    private String username;

    private String password;
}
