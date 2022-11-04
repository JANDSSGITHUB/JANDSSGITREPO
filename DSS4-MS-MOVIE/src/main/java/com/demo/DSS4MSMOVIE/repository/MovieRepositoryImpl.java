package com.demo.DSS4MSMOVIE.repository;

import com.demo.DSS4MSMOVIE.model.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl  {
    public static Specification<Movie> createCriteria(Movie criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getMovieTitle()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("movieTitle"), criteria.getMovieTitle()));
            }
            if (criteria.getMovieCost()!=0) {
                predicates.add(criteriaBuilder.equal(root.get("movieCost"), criteria.getMovieCost()));
            }
            if (criteria.getMovieYear()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("movieYear"), criteria.getMovieYear()));
            }
            if (criteria.getImage()!=null) {
                predicates.add(criteriaBuilder.equal(root.get("image"), criteria.getImage()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
