package com.dss.DSS4MSMOVIEACTOR.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="ACTORS")
@Setter
@Getter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int actor_id;
    private String firstName;
    private String lastName;
    private char gender;
    private int age;

}
