package com.myhangars.security.user.builder;

import com.myhangars.security.user.dto.UserDto;
import com.myhangars.security.user.model.UserEntity;

public class UserDtoBuilder {

    private UserDto userDto;

    public UserDtoBuilder(UserEntity userEntity) {
        this.userDto = new UserDto();
        this.userDto.setUsername(userEntity.getUsername());
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
