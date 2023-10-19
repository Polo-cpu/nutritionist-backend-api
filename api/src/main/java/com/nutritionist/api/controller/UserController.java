package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.AuthRequest;
import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.dto.UserResponse;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.security.JwtService;
import com.nutritionist.api.service.AuthenticationService;
import com.nutritionist.api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;
    private final Language language = Language.EN;
    @Autowired
    private AuthenticationManager authenticationManager;
    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = userService.getAll(language);
        return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserDto user) {
        UserEntity add = userService.create(language, user);
        return new ResponseEntity<UserEntity>(add, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteById(language, id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        UserEntity user = userService.getUserByUsername(language, username);
        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/changePassword")
    public ResponseEntity<UserEntity> changePassword(@RequestBody String username,
                                                     @RequestBody String password) {
        UserEntity user = userService.getUserByUsername(language, username);
        user.setPassword(password);
        return new ResponseEntity<UserEntity>(user, HttpStatus.ACCEPTED);
    }
    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody AuthRequest userRequest) {
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

}