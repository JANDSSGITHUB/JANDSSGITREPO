package com.dss.DSS4MSMOVIEACTOR.controller;

import com.dss.DSS4MSMOVIEACTOR.model.ActorRequestModel;
import com.dss.DSS4MSMOVIEACTOR.service.ActorService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actor")
@CrossOrigin
public class ActorController {

    @Autowired
    ActorService actorService;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody ActorRequestModel requestModel){
        actorService.save(requestModel);
        return "Added new Actor";
    }


    @DeleteMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestBody ActorRequestModel requestModel){
        actorService.delete(requestModel);
        return "Deleted Actor";
    }

}
