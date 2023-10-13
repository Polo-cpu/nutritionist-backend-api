package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.dto.UserResponse;
import com.nutritionist.api.model.dto.UserRequest;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.model.enums.Role;
import com.nutritionist.api.repository.UserRepository;
import com.nutritionist.api.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@ComponentScan("com.nutritionist.api.service.UserService.class")
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserDto userDto) {
        UserEntity user = UserEntity.builder().username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();

    }

    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        UserEntity user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
