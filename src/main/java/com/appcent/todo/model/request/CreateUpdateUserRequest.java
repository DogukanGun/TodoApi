package com.appcent.todo.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateUserRequest {

    @NotNull
    @NotBlank
    @Length(min = 2,max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 2,max = 50)
    private String surname;

    @NotNull
    @NotBlank
    @Length(min = 2,max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Length(min = 8,max = 50)
    private String password;
}
