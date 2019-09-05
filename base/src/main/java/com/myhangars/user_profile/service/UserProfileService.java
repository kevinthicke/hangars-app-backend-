package com.myhangars.user_profile.service;

import com.myhangars.user_profile.model.UserProfile;

import java.util.Optional;

public interface UserProfileService {

    public UserProfile getById(long id);
    public UserProfile insert(UserProfile userProfile);
}
