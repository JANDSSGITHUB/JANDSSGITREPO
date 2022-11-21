package com.demo.DSS5MSACTOR;

import com.demo.DSS5MSACTOR.exception.CustomErrorException;
import com.demo.DSS5MSACTOR.exception.ErrorMessage;
import com.demo.DSS5MSACTOR.exception.NullValuesException;
import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;
import com.demo.DSS5MSACTOR.model.ActorSearchModel;
import com.demo.DSS5MSACTOR.model.MovieActor;
import com.demo.DSS5MSACTOR.repository.ActorRepository;
import com.demo.DSS5MSACTOR.repository.MovieActorRepository;
import com.demo.DSS5MSACTOR.service.ActorService;
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
class Dss5MsActorApplicationTests {

	@MockBean
	ActorRepository actorRepository;

	@Autowired
	ActorService actorService;

	@MockBean
	MovieActorRepository movieActorRepository;


	@Test
	void addActorSucc() {
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						"pat",
						"ramirez",
						'M',
						23,
						1
				);
		Actor actor =
				new Actor(
						1,
						"pat",
						"ramirez",
						'M',
						23
				);
		when(actorRepository.findByActorId(actor.getActorId())).thenReturn(actor);
		actorService.save(actorRequestModel);
		when(actorRepository.save(actor)).thenReturn(actor);
	}
	@Test
	void updateActor() {
		ActorRequestModel actorRequestModel = new ActorRequestModel(1
				, "Patrick"
				, "Ramirez"
				,'M'
				, 23
				, 1);
		Actor actor =
				new Actor(
						1,
						"pat",
						"ramirez",
						'M',
						23
				);
		when(actorRepository.findByActorId(actor.getActorId())).thenReturn(actor);
		actorService.update(actorRequestModel);
	}

	@Test
	void findById(){
		Actor actor =
				new Actor(
						1,
						"pat",
						"ramirez",
						'M',
						23
				);
		Mockito.when(actorRepository.findByActorId(actor.getActorId())).thenReturn(actor);
		Assertions.assertEquals(actor,actorService.findById(actor.getActorId()));
	}

	@Test
	void addActorNullFirstName(){
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						null,
						"ramirez",
						'M',
						23,
						1
				);
		Assertions.assertThrows(NullValuesException.class,()->actorService.save(actorRequestModel));
	}
	@Test
	void addActorNullLastName(){
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						"patrick",
						null,
						'M',
						23,
						1
				);
		Assertions.assertThrows(NullValuesException.class,()->actorService.save(actorRequestModel));
	}
	@Test
	void addActorNullGender(){
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						"patrick",
						null,
						'\0',
						23,
						1
				);
		Assertions.assertThrows(NullValuesException.class,()->actorService.save(actorRequestModel));
	}
	@Test
	void addActorNullAge(){
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						"patrick",
						null,
						'\0',
						0,
						1
				);
		Assertions.assertThrows(NullValuesException.class,()->actorService.save(actorRequestModel));
	}
	@Test
	void actorDeleteWithAssociateMovie(){
		List<MovieActor> movieActorList = new ArrayList<>();
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						null,
						"ramirez",
						'M',
						23,
						1
				);
		MovieActor movieActor = new MovieActor(
				1,
				1
		);
		movieActorList.add(movieActor);
		Mockito.when(movieActorRepository.findByActorId(actorRequestModel.getActorId())).thenReturn(movieActorList);
		Assertions.assertThrows(CustomErrorException.class,()->actorService.delete(actorRequestModel));
	}

	@Test
	void deleteActor(){
		List<MovieActor> movieActorList = new ArrayList<>();
		ActorRequestModel actorRequestModel =
				new ActorRequestModel(
						1,
						"AmbiPur",
						"ramirez",
						'M',
						23,
						1
				);
		Actor actor =
				new Actor(
						"ramirez",
						"Ramirez",
						'M',
						23
				);
		Mockito.when(actorRepository.findByActorId(actorRequestModel.getActorId())).thenReturn(actor);
		actorService.delete(actorRequestModel);
		doNothing().when(actorRepository).delete(actor);
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
	void setActorManually(){
		Actor actor = new Actor();
		actor.setFirstName("Patrick");
		actor.setLastName("Ramirez");
		actor.setGender('M');
		actor.setAge(24);

		ActorSearchModel actorSearchModel = new ActorSearchModel();
		actorSearchModel.setFirstName("Patrick");
		actorSearchModel.setLastName("Ramirez");
		actorSearchModel.setGender('M');
		actorSearchModel.setAge(24);


	}

	@Test
	void findAllMovie() {

		List<Actor> actors = new ArrayList<>();
		ActorSearchModel actorSearchModel = new ActorSearchModel();
		actorSearchModel.setFirstName("Pat");
		actorSearchModel.setLastName("Ramirez");
		actorSearchModel.setAge(23);
		actorSearchModel.setGender('M');
		when(actorRepository.findAll((Specification<Actor>) any())).thenReturn(actors);
		Mockito.when(actorRepository.findAll()).thenReturn(actors);
		Assertions.assertEquals(actors, actorService.findAll(actorSearchModel));
	}


	@Test
	void setMovieActor(){
		MovieActor movieActor = new MovieActor();
		movieActor.setActorId(1);
		movieActor.setMovieId(1);
		int actorId = movieActor.getActorId();
		int movieId = movieActor.getMovieId();
	}

}
