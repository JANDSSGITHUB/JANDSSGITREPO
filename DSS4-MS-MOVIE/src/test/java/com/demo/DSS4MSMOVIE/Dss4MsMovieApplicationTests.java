package com.demo.DSS4MSMOVIE;

import com.demo.DSS4MSMOVIE.exception.CustomErrorException;
import com.demo.DSS4MSMOVIE.model.Actor;
import com.demo.DSS4MSMOVIE.model.Movie;
import com.demo.DSS4MSMOVIE.model.MovieRequestModel;
import com.demo.DSS4MSMOVIE.model.MovieSearchModel;
import com.demo.DSS4MSMOVIE.repository.MovieRepository;
import com.demo.DSS4MSMOVIE.repository.MovieRepositoryImpl;
import com.demo.DSS4MSMOVIE.service.MovieService;
import com.demo.DSS4MSMOVIE.util.FeignServiceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class Dss4MsMovieApplicationTests {

	@MockBean
	private MovieRepository movieRepository;

	@Autowired
	private MovieService movieService;

	@MockBean
	private FeignServiceUtil feignServiceUtil;


	@Test
	void addMovieSucc() {
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		MovieRequestModel movieRequestModel = new MovieRequestModel(1
				, "Shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Movie movie = new Movie(2
				, "Shrek"
				, 181
				,new Date()
				, "Shrek.jpg"
				, actors);
		when(movieRepository.save(movie)).thenReturn(movie);
		when(feignServiceUtil.findActor(actor.getActorId())).thenReturn(actor);
		Assertions.assertNull(movieService.save(movieRequestModel));
	}

	@Test
	void findAllMovie() {
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		List<Movie> movies = new ArrayList<>();
		MovieSearchModel movieSearchModel = new MovieSearchModel(
				"Shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		when(movieRepository.findAll((Specification<Movie>) any())).thenReturn(movies);
		Mockito.when(movieRepository.findAll()).thenReturn(movies);
		Assertions.assertEquals(movies, movieService.findAll(movieSearchModel));
	}



	@Test
	void findById(){
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		Movie movie = new Movie(1,
				"Shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Mockito.when(movieRepository.findByMovieId(movie.getMovieId())).thenReturn(movie);
		Assertions.assertEquals(movie, movieService.findById(movie.getMovieId()));
	}

	@Test
	void deleteMovieNotFound(){
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		MovieRequestModel movieRequestModel = new MovieRequestModel(2,
				"Shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Movie movie = new Movie(1,
				"Shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Mockito.when(movieRepository.findByMovieId(movie.getMovieId())).thenReturn(movie);
		Assertions.assertThrows(CustomErrorException.class,()-> movieService.delete(movieRequestModel));
	}

	@Test
	void addMovieNullValues(){
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		MovieRequestModel movieRequestModel = new MovieRequestModel(2,
				null
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);

		Assertions.assertThrows(CustomErrorException.class,()->movieService.save(movieRequestModel));
	}

	@Test
	void addMovieDuplicateTitle(){
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		Movie mockObject = new Movie(2,
				"shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Mockito.when(movieRepository.findByMovieTitle(mockObject.getMovieTitle())).thenReturn(mockObject);
		MovieRequestModel movieRequestModel = new MovieRequestModel(2,
				"shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Assertions.assertThrows(CustomErrorException.class,()-> movieService.save(movieRequestModel));

	}

	@Test
	void addMovieActorNotFound(){
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		Movie mockObject = new Movie(2,
				"shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actors);
		Mockito.when(movieRepository.findByMovieTitle(mockObject.getMovieTitle())).thenReturn(mockObject);
		Set<Actor> actorRequestSet = new HashSet<>();
		Actor actorRequest = new Actor(2,"Pat", "Ramirez", 'M', 24);
		actorRequestSet.add(actorRequest);
		MovieRequestModel movieRequestModel = new MovieRequestModel(2,
				"shrek"
				, 180
				,new Date()
				, "Shrek.jpg"
				, actorRequestSet);
		Assertions.assertThrows(CustomErrorException.class,()-> movieService.save(movieRequestModel));

	}




}
