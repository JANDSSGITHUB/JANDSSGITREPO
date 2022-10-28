package com.dss.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="admin")
@Getter
@Setter
public class User {

    @Id
    private String emailId;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;


}
