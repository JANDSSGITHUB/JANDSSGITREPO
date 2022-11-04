package com.demo.DSS5MSREVIEW.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewRequestModel {

    private String description;
    private Date postedDate;
    private int rating;
    private int movieId;

    public ReviewRequestModel(
            String description,
            Date postedDate,
            int rating,
            int movieId
    ){
        this.description = description;
        this.postedDate = postedDate;
        this.rating = rating;
        this.movieId = movieId;
    }
}
