package com.demo.DSS5MSACTOR.repository;

import com.demo.DSS5MSACTOR.model.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieActorRepository extends JpaRepository<MovieActor, Integer> {

    List<MovieActor> findByActorId(int actorId);
}
