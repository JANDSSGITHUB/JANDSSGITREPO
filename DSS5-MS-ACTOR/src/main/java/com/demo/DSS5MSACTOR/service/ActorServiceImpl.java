package com.demo.DSS5MSACTOR.service;


import com.demo.DSS5MSACTOR.exception.CustomErrorException;
import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;
import com.demo.DSS5MSACTOR.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Override
    public void save(ActorRequestModel requestModel) {
        Actor actor = new Actor();
        actor.setFirstName(requestModel.getFirstName());
        actor.setLastName(requestModel.getLastName());
        actor.setGender(requestModel.getGender());
        actor.setAge(requestModel.getAge());
        actorRepository.save(actor);
    }

    @Override
    public void delete(ActorRequestModel requestModel) {
        boolean hasActor = findActor(requestModel.getActorId());
        if(!hasActor){
            throw new CustomErrorException("No Actors Found");
        }else{
            actorRepository.deleteById(requestModel.getActorId());
        }

    }

    private boolean findActor(int actorId){
        Optional<Actor> actor = actorRepository.findById(actorId);
        return actor.isPresent();
    }
}
