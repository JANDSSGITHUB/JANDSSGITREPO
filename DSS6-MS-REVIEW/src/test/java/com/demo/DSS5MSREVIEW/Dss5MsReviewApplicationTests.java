package com.demo.DSS5MSREVIEW;

import com.demo.DSS5MSREVIEW.exception.CustomErrorException;
import com.demo.DSS5MSREVIEW.exception.ErrorMessage;
import com.demo.DSS5MSREVIEW.model.Movie;
import com.demo.DSS5MSREVIEW.model.Review;
import com.demo.DSS5MSREVIEW.model.ReviewRequestModel;
import com.demo.DSS5MSREVIEW.repository.MovieRepository;
import com.demo.DSS5MSREVIEW.repository.ReviewRepository;
import com.demo.DSS5MSREVIEW.service.ReviewService;
import com.demo.DSS5MSREVIEW.util.FeignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class Dss5MsReviewApplicationTests {

	@MockBean
	ReviewRepository reviewRepository;

	@Autowired
	ReviewService reviewService;

	@MockBean
	MovieRepository movieRepository;

	@MockBean
	FeignService feignService;

	@Test
	void findAllReview() {
		List<Review> reviewList = new ArrayList<>();
		when(reviewRepository.findAll()).thenReturn(reviewList);
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
		when(movieRepository.findByMovieId(movie.getMovieId())).thenReturn(movie);
		Assertions.assertThrows(CustomErrorException.class,()->reviewService.save(requestModel));
	}

	@Test
	void setErrorMessage(){
		ErrorMessage errorMessage = new ErrorMessage("Error",505,"Error.html");
		ErrorMessage errorMessage1 = new ErrorMessage("error",404);
		ErrorMessage errorMessage2 = new ErrorMessage();
		errorMessage2.setErrorMessage("Error");
		errorMessage2.setErrorCode(505);
		errorMessage2.setDocumentation("ErrorDocs");
	}

	@Test
	void addReview(){
		ReviewRequestModel reviewRequestModel = new ReviewRequestModel(
				"Very Nice Movie",
				new Date(),
				100,
				1
		);

		Movie movie = new Movie(1,
				"test title",100,new Date(),"image.jpg");
		Review review = new Review(
				1,
				"Very nice",
				new Date(),
				100,
				movie
				);
		when(feignService.findMovie(reviewRequestModel.getMovieId())).thenReturn(movie);
		reviewService.save(reviewRequestModel);
	}
}
