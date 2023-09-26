package com.nutritionist.api.service;

import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity createUser(UserEntity user){
        return userRepository.save(user);
    }
    public UserEntity login(UserEntity user){

    }


}
