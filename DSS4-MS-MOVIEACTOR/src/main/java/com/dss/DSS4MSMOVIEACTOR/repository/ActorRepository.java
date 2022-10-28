package com.dss.DSS4MSMOVIEACTOR.repository;

import com.dss.DSS4MSMOVIEACTOR.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor save(Actor actor);
}
