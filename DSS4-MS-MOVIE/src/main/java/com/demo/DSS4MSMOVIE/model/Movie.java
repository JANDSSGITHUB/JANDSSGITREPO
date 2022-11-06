package com.demo.DSS4MSMOVIE.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity(name="movies")
@Getter
@Setter

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int movieId;
    private String movieTitle;
    private int movieCost;
    private Date movieYear;
    private String image;


    @ManyToMany
    @JoinTable(name="movie_actor",
            joinColumns = @JoinColumn(name="movie_id",referencedColumnName = "movieId"),
            inverseJoinColumns = @JoinColumn(name="actor_id",referencedColumnName = "actorId",nullable = true))
    private Set<Actor> actorList = new HashSet<>();

    public Movie(){}

    public Movie(String movieTitle, int movieCost,Date movieYear, String image, Set<Actor> actor){
        this.movieTitle = movieTitle;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
        this.image = image;
        this.actorList = actor;
    }
    public Movie(int id , String movieTitle, int movieCost,Date movieYear, String image, Set<Actor> actor){
        this.movieTitle = movieTitle;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
        this.image = image;
        this.actorList = actor;
        this.movieId = id;
    }

}
