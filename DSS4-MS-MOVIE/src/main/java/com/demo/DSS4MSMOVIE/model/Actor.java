package com.demo.DSS4MSMOVIE.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "actor")
    private Movie movie;

}

