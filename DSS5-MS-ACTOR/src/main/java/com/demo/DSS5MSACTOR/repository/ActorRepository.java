package com.demo.DSS5MSACTOR.repository;

import com.demo.DSS5MSACTOR.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;

public interface ActorRepository extends JpaRepository<Actor, Integer>, JpaSpecificationExecutor<Actor> {
    Actor findByActorId(int actorId);
}
