package com.arturofilio.codebin.services;

import java.util.ArrayList;
import java.util.UUID;

import com.arturofilio.codebin.entities.UserEntity;
import com.arturofilio.codebin.exceptions.EmailExistsException;
import com.arturofilio.codebin.respositories.IUserRepository;
import com.arturofilio.codebin.shared.Dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new RuntimeException("User already exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setEncryptedPassword(
            bCryptPasswordEncoder.encode(user.getPassword())
        );
        UUID userId = UUID.randomUUID();
        userEntity.setUserId(userId.toString());
        UserEntity storedUserDetails = userRepository.save(userEntity);
        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, userToReturn);
        return userToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);
        
        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(userEntity, userToReturn);
        return userToReturn;
    }
    
    
}
