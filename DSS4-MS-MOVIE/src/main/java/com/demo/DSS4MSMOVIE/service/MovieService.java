package com.demo.DSS4MSMOVIE.service;

import com.demo.DSS4MSMOVIE.model.Movie;
import com.demo.DSS4MSMOVIE.model.MovieRequestModel;

import java.util.ArrayList;

public interface MovieService {
    void save(MovieRequestModel requestModel);

    ArrayList<Movie> findAll();

    void delete(MovieRequestModel requestModel);
}
