package com.demo.DSS5MSREVIEW.service;


import com.demo.DSS5MSREVIEW.exception.CustomErrorException;
import com.demo.DSS5MSREVIEW.model.Movie;
import com.demo.DSS5MSREVIEW.model.Review;
import com.demo.DSS5MSREVIEW.model.ReviewRequestModel;
import com.demo.DSS5MSREVIEW.repository.MovieRepository;
import com.demo.DSS5MSREVIEW.repository.ReviewRepository;
import com.demo.DSS5MSREVIEW.util.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    FeignService feignService;

    @Override
    public void save(ReviewRequestModel requestModel) {
        validate(requestModel);
        Review review = setValues(requestModel);
        reviewRepository.save(review);
    }

    @Override
    public ArrayList<Review> findAll() {
        return new ArrayList<>(reviewRepository.findAll());
    }



    private void validate(ReviewRequestModel requestModel){
        if(requestModel.getDescription() == null || requestModel.getPostedDate() == null || requestModel.getRating() == 0){
            throw new CustomErrorException("Please fill up all the values");
        }
        Movie movie = findByMovieId(requestModel.getMovieId());
        if(movie==null){
            throw new CustomErrorException("Movie not found");
        }

    }
    private Review setValues(ReviewRequestModel requestModel){
        Movie movie = findByMovieId(requestModel.getMovieId());
        return new Review(requestModel.getDescription(),new Date(),requestModel.getRating(),movie);
    }
    private Movie findByMovieId(int movieId){
        return feignService.findMovie(movieId);
    }



}
