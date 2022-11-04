package com.demo.DSS4MSMOVIE.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class MovieRequestModel {

    private int movieId;
    private String movieTitle;
    private int movieCost;
    private Date movieYear;
    private Set<Actor> actorSet;
    private String image;
    private int actorId;

    public MovieRequestModel(int movieId
    ,String movieTitle,int movieCost, Date movieYear,String image
    ,Set<Actor> actorSet){
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
        this.image = image;
        this.actorSet = actorSet;
    }
}
