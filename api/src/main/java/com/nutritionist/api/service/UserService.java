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
    public UserEntity getByUserName(String name){
        return userRepository.getByName(name);
    }
    public UserEntity createUser(UserEntity user){
        return userRepository.save(user);
    }
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


}
