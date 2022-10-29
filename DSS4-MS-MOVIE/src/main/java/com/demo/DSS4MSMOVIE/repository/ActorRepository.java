package com.demo.DSS4MSMOVIE.repository;

import com.demo.DSS4MSMOVIE.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor findByActorId(int actorId);
}
