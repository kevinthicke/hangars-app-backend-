package com.myhangars.security.user.builder;

import com.myhangars.security.user.dto.UserDto;
import com.myhangars.security.user.model.UserEntity;

public class UserEntityBuilder {

    private UserEntity userEntity;

    public UserEntityBuilder(UserDto userDto) {
        this.userEntity = new UserEntity();

        this.userEntity.setUsername(userDto.getUsername());
        this.userEntity.setPassword(userDto.getPassword());
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
