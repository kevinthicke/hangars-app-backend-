package com.myhangars.security.user.dao;

import com.myhangars.security.user.model.UserEntity;
import com.myhangars.security.user.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEntityDao {

    @Autowired private UserEntityRepository userEntityRepository;

    public UserEntity findByUsername(String username) {
        return this.userEntityRepository.findByUsername(username).get();
    }

    public UserEntity save(UserEntity userEntity) {
        return this.userEntityRepository.save(userEntity);
    }
}
