package com.nutritionist.api.service;

import com.nutritionist.api.exception.IncorrectPasswordException;
import com.nutritionist.api.exception.ProductNotFoundException;
import com.nutritionist.api.exception.UserNotFoundException;
import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.enums.MessageCodes;
import com.nutritionist.api.model.enums.Role;
import com.nutritionist.api.model.mapper.UserMapperImpl;
import com.nutritionist.api.repository.UserRepository;
import com.nutritionist.api.security.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.nutritionist.api.service.UserService.class")
@Slf4j
@AllArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private final UserMapperImpl userMapper;
    public List<UserEntity> getAll(Language language){
        try {
            log.info("all products are showing");
            return userRepository.findAll();
        }catch (Exception e){
            throw new UserNotFoundException(language, MessageCodes.USER_NOT_FOUND);
        }
    }
    public UserEntity getUserByUsername(Language language, String username){
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserNotFoundException(language, MessageCodes.USER_NOT_FOUND);
        }
        return user.get();
    }
    public UserEntity create(Language language, UserDto userDTO){
        try {
            UserEntity user = userMapper.toUser(userDTO);
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }catch (Exception e){
            throw new UserNotFoundException(language, MessageCodes.USER_NOT_FOUND);
        }
    }
    public UserEntity changePassword(Language language, String username, String oldPassword, String newPassword){
        Optional<UserEntity> user= userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserNotFoundException(language,MessageCodes.USER_NOT_FOUND);
        }
        else {
            if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
                user.get().setPassword(passwordEncoder.encode(newPassword));
            } else {
                throw new IncorrectPasswordException(language,MessageCodes.INCORRECT_PASSWORD);
            }
            return userRepository.save(user.get());
        }
    }
    public UserEntity deleteById(Language language, Long id){
        Optional<UserEntity> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new UserNotFoundException(language,MessageCodes.USER_NOT_FOUND);
        }
        else{
            log.error("Product deleted successfully!");
            userRepository.delete(byId.get());
        }
        return byId.get();
    }
    public List<String> getAllEmails(Language language){
        try {
            log.info("all products are showing");
            return userRepository.getAllEmails();
        }catch (Exception e){
            throw new UserNotFoundException(language, MessageCodes.USER_NOT_FOUND);
        }
    }
}
