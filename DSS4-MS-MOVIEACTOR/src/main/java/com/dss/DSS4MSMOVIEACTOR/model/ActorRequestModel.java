package com.dss.DSS4MSMOVIEACTOR.model;

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

}
