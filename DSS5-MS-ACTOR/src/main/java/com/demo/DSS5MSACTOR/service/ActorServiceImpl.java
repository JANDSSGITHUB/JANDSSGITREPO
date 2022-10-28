package com.demo.DSS5MSACTOR.service;


import com.demo.DSS5MSACTOR.exception.CustomErrorException;
import com.demo.DSS5MSACTOR.exception.NullValuesException;
import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;
import com.demo.DSS5MSACTOR.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Override
    public void save(ActorRequestModel requestModel) {
        Actor actor = new Actor();
        validate(requestModel);
        actor.setFirstName(requestModel.getFirstName());
        actor.setLastName(requestModel.getLastName());
        actor.setGender(requestModel.getGender());
        actor.setAge(requestModel.getAge());
        actorRepository.save(actor);
    }

    private void validate(ActorRequestModel requestModel){
        if(Character.isWhitespace(requestModel.getGender())
                || requestModel.getFirstName().isEmpty()
                || requestModel.getLastName().isEmpty()
                || requestModel.getAge() == 0
        ){
            throw new NullValuesException("Please fill up all the values");
        }
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

    @Override
    public ArrayList<Actor> findAll() {
        return new ArrayList<>(actorRepository.findAll());
    }

    @Override
    public void update(ActorRequestModel requestModel) {
        Actor actor = findByActorId(requestModel);
           if(!requestModel.getFirstName().isEmpty()){
               actor.setFirstName(requestModel.getFirstName());
           }
           if(!requestModel.getLastName().isEmpty()){
               actor.setLastName(requestModel.getLastName());
           }
           if(!Character.isWhitespace(requestModel.getGender())){
               actor.setGender(requestModel.getGender());
           }
           if(requestModel.getAge() != 0){
               actor.setAge(requestModel.getAge());
           }
           actorRepository.save(actor);
    }

    private boolean findActor(int actorId){
        Optional<Actor> actor = actorRepository.findById(actorId);
        return actor.isPresent();
    }

    private Actor findByActorId(ActorRequestModel requestModel){
        Actor actor = actorRepository.findByActorId(requestModel.getActorId());
        if(actor!=null){
            return actor;
        }else throw new CustomErrorException("Actor not found");
    }
}
