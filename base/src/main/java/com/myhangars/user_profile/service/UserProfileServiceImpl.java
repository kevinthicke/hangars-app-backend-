package com.myhangars.user_profile.service;

import com.myhangars.exception.GenericException;
import com.myhangars.user_profile.dao.UserProfileDao;
import com.myhangars.user_profile.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements  UserProfileService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public UserProfile getById(long id) {

        return this.userProfileDao
                .findById(id)
                .orElseThrow(() -> new GenericException.NotFound(id));
    }

    @Override
    public UserProfile insert(UserProfile userProfile) {
        return this.userProfileDao
                .save(userProfile);
    }
}
