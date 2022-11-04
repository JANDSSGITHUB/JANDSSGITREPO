package com.demo.DSS5MSACTOR.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorRequestModel {

    private int actorId;
    private String firstName;
    private String lastName;
    private char gender;
    private int age;
    private int movieId;

    public ActorRequestModel(
            int actorId,
            String firstName,
            String lastName,
            char gender,
            int age,
            int movieId
    ){
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.movieId = movieId;
    }

}
