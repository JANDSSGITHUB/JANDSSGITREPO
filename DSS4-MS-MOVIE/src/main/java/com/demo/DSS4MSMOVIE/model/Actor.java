package com.demo.DSS4MSMOVIE.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="ACTORS")
@Setter
@Getter
public class Actor {

    @Id
    private int actorId;
    private String firstName;
    private String lastName;
    private char gender;
    private int age;

    @JsonIgnore
    @ManyToMany(mappedBy = "actorList")
    private Set<Movie> moviesList = new HashSet<>();

    public Actor(int actorId, String firstName,String lastName,char gender,int age){
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public Actor(){}

}

