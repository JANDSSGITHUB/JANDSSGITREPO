package com.demo.DSS4MSMOVIE;

import com.demo.DSS4MSMOVIE.controller.MovieController;
import com.demo.DSS4MSMOVIE.exception.CustomErrorException;
import com.demo.DSS4MSMOVIE.exception.ErrorMessage;
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
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;



import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	@Autowired
	private MovieRepositoryImpl movieRepositoryImpl;


	@MockBean
	private MovieService movieServiceController;

	@InjectMocks
	MovieController movieController;




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
	void updateMovie() {
		MovieRequestModel movieRequestModel = new MovieRequestModel(2
				, null
				, 180
				,null
				, "shrek.jpg"
				, null);
		Movie movie = new Movie(2
				, null
				, 181
				,null
				, "shrek.jpg"
				, null);
		Mockito.when(movieRepository.findByMovieId(movie.getMovieId())).thenReturn(movie);
		movieService.update(movieRequestModel);
	}

	@Test
	void deleteMovie() throws ParseException {
		Set<Actor> actors = new HashSet<>();
		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
		actors.add(actor);
		String dateString = "2020-1-26";
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		MovieRequestModel movieRequestModel = new MovieRequestModel(1,
				"Shrek"
				, 180
				,date = formatter.parse(dateString)
				, "Shrek.jpg"
				, actors);
		Movie movie = new Movie(1,
				"Shrek"
				, 180
				,date = formatter.parse(dateString)
				, "Shrek.jpg"
				, actors);
		Mockito.when(movieRepository.findByMovieId(movie.getMovieId())).thenReturn(movie);
		movieService.delete(movieRequestModel);
		doNothing().when(movieRepository).delete(movie);
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
		Movie movie = new Movie("Shrek",100,new Date(),"hello",actors);
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

	@Test
	void setErrorMessage(){
		ErrorMessage errorMessage = new ErrorMessage("Error",505,"Error.html");
		ErrorMessage errorMessage1 = new ErrorMessage("error",404);
		ErrorMessage errorMessage2 = new ErrorMessage();
		errorMessage2.setErrorMessage("Error");
		errorMessage2.setErrorCode(505);
		errorMessage2.setDocumentation("ErrorDocs");
		String errorMessageString = errorMessage2.getErrorMessage();
		int errorCode = errorMessage2.getErrorCode();
		String errorDocs = errorMessage2.getDocumentation();
	}

	@Test
	void setActor(){
		Actor actor = new Actor();
		actor.setFirstName("Pat");
		actor.setLastName("Ramirez");
		actor.setAge(23);
		actor.setGender('M');
		String fName = actor.getFirstName();
		String lName = actor.getLastName();
		int age = actor.getAge();
		char gender = actor.getGender();
	}




//	@Test
//	void movieController() {
//		Set<Actor> actors = new HashSet<>();
//		Actor actor = new Actor(1,"Pat", "Ramirez", 'M', 24);
//		actors.add(actor);
//		when(movieServiceController.findAll(new MovieSearchModel())).thenReturn(new ArrayList<Movie>());
//		movieController.findAll(new MovieSearchModel("movieTitle",0,new Date(),"null",actors));
//	}







}
