package com.demo.DSS5MSACTOR.service;


import com.demo.DSS5MSACTOR.exception.CustomErrorException;
import com.demo.DSS5MSACTOR.exception.NullValuesException;
import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;
import com.demo.DSS5MSACTOR.model.ActorSearchModel;
import com.demo.DSS5MSACTOR.model.MovieActor;
import com.demo.DSS5MSACTOR.repository.ActorRepository;
import com.demo.DSS5MSACTOR.repository.ActorRepositoryImpl;
import com.demo.DSS5MSACTOR.repository.MovieActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieActorRepository movieActorRepository;


    @Override
    public void save(ActorRequestModel requestModel) {
        validate(requestModel);
        Actor actor = setValues(requestModel);

        actorRepository.save(actor);
    }

    private Actor setValues(ActorRequestModel requestModel){
        return new Actor(requestModel.getFirstName(),requestModel.getLastName(),requestModel.getGender(),requestModel.getAge());
    }

    private void validate(ActorRequestModel requestModel){
        if(requestModel.getGender()== '\0'
                || requestModel.getFirstName() == null
                || requestModel.getLastName()== null
                || requestModel.getAge() == 0
        ){
            throw new NullValuesException("Please fill up all the values");
        }
    }

    @Override
    public void delete(ActorRequestModel requestModel) {
        findByActorId(requestModel);
        List<MovieActor> movieActor=movieActorRepository.findByActorId(requestModel.getActorId());
        if(!movieActor.isEmpty()){
            throw new CustomErrorException("Cannot delete actor with associated movie");
        }
        actorRepository.deleteById(requestModel.getActorId());
    }

    @Override
    public ArrayList<Actor> findAll(ActorSearchModel actorSearchModel) {
        Actor actor = new Actor(actorSearchModel.getFirstName(),actorSearchModel.getLastName(),
                actorSearchModel.getGender(),actorSearchModel.getAge());
       List<Actor> actorList = actorRepository.findAll(ActorRepositoryImpl.createCriteria(actor));
       return new ArrayList<>(actorList);
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
           if(requestModel.getGender()!= '\0'){
               actor.setGender(requestModel.getGender());
           }
           if(requestModel.getAge() != 0){
               actor.setAge(requestModel.getAge());
           }
           actorRepository.save(actor);
    }

    @Override
    public Actor findById(Integer id) {
        return actorRepository.findByActorId(id);
    }


    private Actor findByActorId(ActorRequestModel requestModel){
        Actor actor = actorRepository.findByActorId(requestModel.getActorId());

        if(actor!=null){
            return actor;
        }else throw new CustomErrorException("Actor not found");
    }
}
