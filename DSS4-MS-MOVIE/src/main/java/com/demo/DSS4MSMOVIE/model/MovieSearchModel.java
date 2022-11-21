package com.demo.DSS4MSMOVIE.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class MovieSearchModel {

    private String movieTitle;
    private String image;
    private int movieCost;
    private Date movieYear;
    private Set<Actor> actorList;

    public MovieSearchModel(
            String movieTitle,int movieCost, Date movieYear,String image
            ,Set<Actor> actorSet){
        this.movieTitle = movieTitle;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
        this.image = image;
        this.actorList = actorSet;
    }

    public MovieSearchModel(){}


}
