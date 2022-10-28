package com.demo.DSS5MSACTOR.repository;

import com.demo.DSS5MSACTOR.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor save(Actor actor);

    Actor findByActorId(int actorId);
}
