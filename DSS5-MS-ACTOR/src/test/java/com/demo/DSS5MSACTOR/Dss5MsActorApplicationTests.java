package com.demo.DSS5MSACTOR;

import com.demo.DSS5MSACTOR.exception.CustomErrorException;
import com.demo.DSS5MSACTOR.exception.NullValuesException;
import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;
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


import java.util.ArrayList;
import java.util.List;

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
		Mockito.when(actorRepository.save(actor)).thenReturn(actor);
		doNothing().when(actorService).save(actorRequestModel);
		verify(actorService,times(1)).save(actorRequestModel);
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
	void addActorNullValues(){
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

}
