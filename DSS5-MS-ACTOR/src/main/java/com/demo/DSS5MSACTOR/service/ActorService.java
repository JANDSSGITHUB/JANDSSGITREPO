package com.demo.DSS5MSACTOR.service;

import com.demo.DSS5MSACTOR.model.Actor;
import com.demo.DSS5MSACTOR.model.ActorRequestModel;

import java.util.ArrayList;

public interface ActorService {

    void save(ActorRequestModel requestModel);

    void delete(ActorRequestModel requestModel);

    ArrayList<Actor> findAll();

    void update(ActorRequestModel requestModel);
}
