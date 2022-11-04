package com.demo.DSS5MSREVIEW.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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


    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews= new HashSet<>();

    public Movie(){}

    public Movie(int movieId,
                 String movieTitle,
                 int movieCost,
                 Date movieYear,
                 String image){
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieCost = movieCost;
        this.movieYear = movieYear;
        this.image = image;
    }

}
