package com.demo.DSS5MSREVIEW;

import com.demo.DSS5MSREVIEW.exception.CustomErrorException;
import com.demo.DSS5MSREVIEW.model.Movie;
import com.demo.DSS5MSREVIEW.model.Review;
import com.demo.DSS5MSREVIEW.model.ReviewRequestModel;
import com.demo.DSS5MSREVIEW.repository.MovieRepository;
import com.demo.DSS5MSREVIEW.repository.ReviewRepository;
import com.demo.DSS5MSREVIEW.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@SpringBootTest
class Dss5MsReviewApplicationTests {

	@MockBean
	ReviewRepository reviewRepository;

	@Autowired
	ReviewService reviewService;

	@MockBean
	MovieRepository movieRepository;

	@Test
	void findAllReview() {
		List<Review> reviewList = new ArrayList<>();
		Mockito.when(reviewRepository.findAll()).thenReturn(reviewList);
		Assertions.assertEquals(reviewList,reviewService.findAll());

	}

	@Test
	void addReviewNullValues(){
		ReviewRequestModel requestModel = new ReviewRequestModel(
				null,
				new Date(),
				100,
				1
		);
		Assertions.assertThrows(CustomErrorException.class,()->reviewService.save(requestModel));
	}

	@Test
	void addReviewMovieNotFound(){
		ReviewRequestModel requestModel = new ReviewRequestModel(
				null,
				new Date(),
				100,
				2
		);
		Movie movie = new Movie(1,
				"Shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
		);
		Mockito.when(movieRepository.findByMovieId(movie.getMovieId())).thenReturn(movie);
		Assertions.assertThrows(CustomErrorException.class,()->reviewService.save(requestModel));
	}
}
