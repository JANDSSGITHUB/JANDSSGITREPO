package com.demo.DSS5MSACTOR.model;


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
    private int actorId;
    private String firstName;
    private String lastName;
    private char gender;
    private int age;

}
