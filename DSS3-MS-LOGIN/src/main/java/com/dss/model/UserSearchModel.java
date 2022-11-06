package com.dss.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchModel {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public UserSearchModel(String email,String firstName,String lastName,String phoneNumber){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
