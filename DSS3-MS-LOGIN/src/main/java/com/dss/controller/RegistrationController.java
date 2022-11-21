package com.dss.controller;
import com.dss.model.User;
import com.dss.model.UserSearchModel;
import com.dss.service.RegistrationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    Environment environment;

    @PostMapping("/registrations")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody User admin){
        registrationService.save(admin);
        return "Added new admin";
    }


    @GetMapping("/registrations/validate/{emailId}/{password}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public User authenticate(@PathVariable(value="emailId") String emailId, @PathVariable( value ="password") String password) {
        return registrationService.validate(emailId,password);
    }

    @GetMapping("/registrations/findAllWithCriteria")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public List<User> authenticate(@RequestBody UserSearchModel searchModel) {
        return registrationService.findAllUsers(searchModel);
    }
    @GetMapping("/instance")
    public String getInstancePort(){return environment.getProperty("local.server.port");}



}
