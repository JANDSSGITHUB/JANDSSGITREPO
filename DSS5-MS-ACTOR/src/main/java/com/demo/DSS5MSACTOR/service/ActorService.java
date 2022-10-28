package com.demo.DSS5MSACTOR.service;

import com.demo.DSS5MSACTOR.model.ActorRequestModel;

public interface ActorService {

    void save(ActorRequestModel requestModel);

    void delete(ActorRequestModel requestModel);
}
