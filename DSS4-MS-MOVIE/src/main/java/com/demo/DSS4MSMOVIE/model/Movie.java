package com.demo.DSS4MSMOVIE.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "actor_id" )
    private Actor actor;

    public Movie(){}

    public Movie(String movieTitle, int movieCost,Date movieYear, String image, Actor actor){
        this.movieTitle = movieTitle;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
        this.image = image;
       this.actor = actor;
    }

}
