package com.myhangars.user_profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private long id;
    private String name;
    private String surname;
    private boolean state;
    private String address;
    private String email;
    private String phone;

}
