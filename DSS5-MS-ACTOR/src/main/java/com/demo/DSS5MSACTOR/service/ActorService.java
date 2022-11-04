package com.demo.DSS5MSACTOR.service;

import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;
import com.demo.DSS5MSACTOR.model.ActorSearchModel;
import com.demo.DSS5MSACTOR.model.MovieActor;

import java.util.ArrayList;

public interface ActorService {

    void save(ActorRequestModel requestModel);

    void delete(ActorRequestModel requestModel);

    ArrayList<Actor> findAll(ActorSearchModel searchModel);

    void update(ActorRequestModel requestModel);


    Actor findById(Integer id);
}
