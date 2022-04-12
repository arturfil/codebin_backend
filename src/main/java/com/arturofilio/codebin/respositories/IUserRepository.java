package com.arturofilio.codebin.respositories;

import com.arturofilio.codebin.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findByEmail(String email);
}
