package com.nutritionist.api.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.management.relation.Role;

@Data
public class UserDto {
    @NotNull
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Role role;
}
