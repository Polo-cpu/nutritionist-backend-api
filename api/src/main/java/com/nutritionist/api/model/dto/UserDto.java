package com.nutritionist.api.model.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.management.relation.Role;

@Data
public class UserDto {
    @NotNull
    @Size(min = 5, max = 25, message = "Username length should be between 5 and 25 characters")
    private String userName;
    @NotNull
    private String email;
    @NotNull
    @Size(min = 5, max = 25, message = "Password length should be between 5 and 25 characters")
    private String password;
    @NotNull
    private Role role;
}
