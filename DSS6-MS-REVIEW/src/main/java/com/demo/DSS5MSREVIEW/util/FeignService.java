package com.demo.DSS5MSREVIEW.util;

import com.demo.DSS5MSREVIEW.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="feign-service", url="localhost:9004/movie")
public interface FeignService {
    @RequestMapping(method = RequestMethod.GET, value = "/findByMovieId/{id}", consumes = "application/json", produces="application/json")
    public Movie findMovie(@PathVariable("id") int id);
}
