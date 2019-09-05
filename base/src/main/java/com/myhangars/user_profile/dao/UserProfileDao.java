package com.myhangars.user_profile.dao;

import com.myhangars.user_profile.model.UserProfile;

import java.util.Optional;

public interface UserProfileDao {

    public Optional<UserProfile> findById(long id);
    public UserProfile save(UserProfile userProfile);
}
