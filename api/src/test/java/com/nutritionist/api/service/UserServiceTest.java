package com.nutritionist.api.service;

import com.nutritionist.api.model.dto.CustomerDto;
import com.nutritionist.api.model.dto.UserDto;
import com.nutritionist.api.model.entity.CustomerEntity;
import com.nutritionist.api.model.entity.ProductEntity;
import com.nutritionist.api.model.entity.UserEntity;
import com.nutritionist.api.model.enums.Language;
import com.nutritionist.api.model.enums.Role;
import com.nutritionist.api.model.mapper.ProductMapper;
import com.nutritionist.api.model.mapper.UserMapper;
import com.nutritionist.api.model.mapper.UserMapperImpl;
import com.nutritionist.api.repository.ProductRepository;
import com.nutritionist.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class UserServiceTest {
    @Autowired
    private final Language language = Language.EN;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapperImpl userMapper;
    @InjectMocks
    private UserService userService;
    @Test
    void getAllUsers(){
        List<UserEntity> sampleUsers = sampleUserList();
        Mockito.when(userService.getAll(language)).thenReturn(sampleUsers);
        List<UserEntity> actualUsers = userRepository.findAll();
        Assertions.assertEquals(sampleUsers.size(),actualUsers.size());
        for(int i = 0; i<actualUsers.size();i++){
            UserEntity toCompareUser1 = sampleUsers.get(i);
            UserEntity toCompareUser2 = actualUsers.get(i);
            Assertions.assertEquals(toCompareUser1.getUsername(),toCompareUser2.getUsername());
            Assertions.assertEquals(toCompareUser1.getPassword(),toCompareUser2.getPassword());
            Assertions.assertEquals(toCompareUser1.getEmail(),toCompareUser2.getEmail());
            Assertions.assertEquals(toCompareUser1.getRole(),toCompareUser2.getRole());
        }
    }
   @Test
   void getUserByUsername(){
        String userName = "John";
        UserEntity sampleUser = sampleUserList().get(1);
        Mockito.when(userService.getUserByUsername(language,"John")).thenReturn(sampleUser);
        Optional<UserEntity> actualUser = userRepository.findByUsername(userName);
        Assertions.assertEquals(actualUser.get(),sampleUser);
   }
   @Test
    void delete(){
        Long userId = 1L;
        UserEntity user = sampleUserList().get(0);
        Mockito.when(userService.deleteById(language,userId)).thenReturn(user);
        doNothing().when(userRepository).deleteById(userId);
        userService.deleteById(language, 1L);
        verify(userRepository,times(1)).deleteById(userId);
    }
    @Test
    void createUser(){
        UserEntity expectedUser = sampleUserList().get(0);
        expectedUser.setId(null);
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(expectedUser);
        UserDto userDto = new UserDto();
        userDto.setUsername((expectedUser.getUsername()));
        userDto.setEmail(expectedUser.getEmail());
        userDto.setPassword(expectedUser.getPassword());
        userService.create(language,userDto);
        verify(userRepository,times(1)).save(expectedUser);

        Assertions.assertEquals(expectedUser.getUsername(),userDto.getUsername());
        Assertions.assertEquals(expectedUser.getPassword(),userDto.getPassword());
        Assertions.assertEquals(expectedUser.getEmail(),userDto.getEmail());
    }

    public List<UserEntity> sampleUserList(){
        List<UserEntity> sampleUsers = new ArrayList<>();
        UserEntity user1 = new UserEntity(1L,"maria","maria@abc.com","123", Role.USER);
        UserEntity user2 = new UserEntity(2L,"berk","berk@abc.com","123123",Role.USER);
        UserEntity user3 = new UserEntity(3L,"muller","muller@abc.com","ronalda321",Role.USER);
        UserEntity user4 = new UserEntity(4L,"general","general@abc.com","abcabc1",Role.USER);
        sampleUsers.add(user1);
        sampleUsers.add(user2);
        sampleUsers.add(user3);
        sampleUsers.add(user4);
        return sampleUsers;
    }
}
