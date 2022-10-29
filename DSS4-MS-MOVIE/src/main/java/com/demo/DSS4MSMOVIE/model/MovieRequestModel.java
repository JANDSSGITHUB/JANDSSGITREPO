package com.demo.DSS4MSMOVIE.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieRequestModel {

    private int movieId;
    private String movieTitle;
    private int movieCost;
    private Date movieYear;
    private int actorId;
    private String image;
}
