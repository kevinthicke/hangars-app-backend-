package com.myhangars.security.service;

import com.myhangars.security.user.builder.UserEntityBuilder;
import com.myhangars.security.user.dao.UserEntityDao;
import com.myhangars.security.user.dto.UserDto;
import com.myhangars.security.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired private PasswordEncoder bcryptEncoder;

    @Autowired private UserEntityDao userEntityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userEntityDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("javainuse".equals(username)) {
            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    */



    public UserEntity save(UserDto userDto) {

        String encryptedPassword = bcryptEncoder.encode(userDto.getPassword());
        userDto.setPassword(encryptedPassword);

        UserEntity userEntity = new UserEntityBuilder(userDto).getUserEntity();

        return this.userEntityDao.save(userEntity);

    }
}