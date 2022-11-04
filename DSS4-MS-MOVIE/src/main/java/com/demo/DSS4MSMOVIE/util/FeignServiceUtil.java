package com.demo.DSS4MSMOVIE.util;


import com.demo.DSS4MSMOVIE.model.Actor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Path;


@FeignClient(value="feign-service", url="localhost:9006/actor")
public interface FeignServiceUtil {

    @RequestMapping(method = RequestMethod.GET, value = "/findActorById/{id}", consumes = "application/json", produces="application/json")
    public Actor findActor(@PathVariable("id") int id);


}
