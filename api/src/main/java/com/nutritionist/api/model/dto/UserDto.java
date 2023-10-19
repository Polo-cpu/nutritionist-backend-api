package com.nutritionist.api.model.dto;


import com.nutritionist.api.model.enums.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull
    @Size(min = 5, max = 25, message = "Username length should be between 5 and 25 characters")
    private String username;
    @NotNull
    private String email;
    @NotNull
    @Size(min = 5, max = 25, message = "Password length should be between 5 and 25 characters")
    private String password;
}
