package com.arturofilio.codebin.services;

import com.arturofilio.codebin.IUserRepository;
import com.arturofilio.codebin.entities.UserEntity;
import com.arturofilio.codebin.shared.Dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword("password123");
        userEntity.setUserId("testIdPublico");
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);
        return userToReturn;
    }
    
}
