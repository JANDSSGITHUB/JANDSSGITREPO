package com.demo.DSS5MSREVIEW.repository;

import com.demo.DSS5MSREVIEW.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieId(int movieId);
}
