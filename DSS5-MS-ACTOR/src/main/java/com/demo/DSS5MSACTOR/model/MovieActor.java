package com.demo.DSS5MSACTOR.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.Id;

@Entity(name="movieActor")
@Getter
@Setter
public class MovieActor {

    @Id
    private int movieId;
    private int actorId;

    public MovieActor(int movieId,int actorId){
        this.movieId = movieId;
        this.actorId = actorId;
    }
}
