package com.nutritionist.api.controller;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> findAll(){
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<List<UserEntity>>(users,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        UserEntity add = userService.addUser(user);
        return new ResponseEntity<UserEntity>(add, HttpStatus.CREATED);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{}")
    public ResponseEntity<Void> deleteUser(Long id){
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }




}
