package com.demo.DSS4MSMOVIE.service;

import com.demo.DSS4MSMOVIE.model.Movie;
import com.demo.DSS4MSMOVIE.model.MovieRequestModel;
import com.demo.DSS4MSMOVIE.model.MovieSearchModel;

import java.util.ArrayList;
import java.util.List;

public interface MovieService {
    Movie save(MovieRequestModel requestModel);

    ArrayList<Movie> findAll(MovieSearchModel searchModel);

    void delete(MovieRequestModel requestModel);

    void update(MovieRequestModel requestModel);

    Movie findById(int id);


}
