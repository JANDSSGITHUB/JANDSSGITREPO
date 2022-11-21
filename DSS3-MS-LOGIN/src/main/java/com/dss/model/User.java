package com.dss.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

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

    @Transient
    String token;

    public User(String emailId, String firstName, String lastName, String phoneNumber){
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public User(String emailId, String firstName, String lastName,String password, String phoneNumber){
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    public User(){}


}
