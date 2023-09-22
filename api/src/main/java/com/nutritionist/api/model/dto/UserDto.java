package com.nutritionist.api.model.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private String userNameDto;
    @NotNull
    private String emailDto;
    @NotNull
    private String passwordDto;
}
