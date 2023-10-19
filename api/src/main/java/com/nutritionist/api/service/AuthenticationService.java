package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.AuthRequest;
import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.dto.UserResponse;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.model.enums.Role;
import com.nutritionist.api.repository.UserRepository;
import com.nutritionist.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserDto userDto) {
        UserEntity user = UserEntity.builder().username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.USER).build();
        userRepository.save(user);
        var token = jwtService.generateToken(user.getUsername());
        return UserResponse.builder().token(token).build();

    }

    public UserResponse auth(AuthRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        UserEntity user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user.getUsername());
        return UserResponse.builder().token(token).build();
    }
}
