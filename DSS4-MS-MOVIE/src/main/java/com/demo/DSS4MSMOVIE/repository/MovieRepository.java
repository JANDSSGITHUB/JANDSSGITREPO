package com.demo.DSS4MSMOVIE.repository;

import com.demo.DSS4MSMOVIE.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRepository extends JpaRepository<Movie,Integer>, JpaSpecificationExecutor<Movie> {

    Movie findByMovieId(int movieId);

    Movie findByMovieTitle(String movieTitle);
}
