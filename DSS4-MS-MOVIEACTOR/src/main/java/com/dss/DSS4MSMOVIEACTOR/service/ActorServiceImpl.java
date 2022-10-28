package com.dss.DSS4MSMOVIEACTOR.service;

import com.dss.DSS4MSMOVIEACTOR.exception.CustomErrorException;
import com.dss.DSS4MSMOVIEACTOR.model.Actor;
import com.dss.DSS4MSMOVIEACTOR.model.ActorRequestModel;
import com.dss.DSS4MSMOVIEACTOR.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
