package com.dss.DSS4MSMOVIEACTOR.service;

import com.dss.DSS4MSMOVIEACTOR.model.ActorRequestModel;

public interface ActorService {

    void save(ActorRequestModel requestModel);

    void delete(ActorRequestModel requestModel);
}
