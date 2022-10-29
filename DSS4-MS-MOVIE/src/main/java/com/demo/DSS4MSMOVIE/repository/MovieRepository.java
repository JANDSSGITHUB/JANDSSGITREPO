package com.demo.DSS4MSMOVIE.repository;

import com.demo.DSS4MSMOVIE.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie save(Movie movie);

    Movie findByMovieId(int movieId);
}
