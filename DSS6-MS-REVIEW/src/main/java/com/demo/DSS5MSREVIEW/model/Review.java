package com.demo.DSS5MSREVIEW.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name="review")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewId;
    private Date datePosted;
    private String description;
    private int rating;

    @ManyToOne
    @JoinColumn(name="movie_id", referencedColumnName = "movieId")
    private Movie movie;


    public Review(String description, Date datePosted, int rating,Movie movie){
        this.description = description;
        this.datePosted = datePosted;
        this.rating = rating;
        this.movie = movie;
    }
    public Review(int id,String description, Date datePosted, int rating,Movie movie){
        this.description = description;
        this.datePosted = datePosted;
        this.rating = rating;
        this.movie = movie;
        this.reviewId = id;
    }

    public Review(){}
}
