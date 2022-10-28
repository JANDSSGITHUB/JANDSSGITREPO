package com.demo.DSS5MSACTOR.repository;

import com.demo.DSS5MSACTOR.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor save(Actor actor);
}
