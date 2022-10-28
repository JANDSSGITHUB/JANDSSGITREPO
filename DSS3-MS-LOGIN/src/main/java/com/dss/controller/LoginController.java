package com.dss.controller;


import com.dss.model.LoginRequestModel;
import com.dss.model.User;
import com.dss.service.LoginService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public boolean add(@RequestBody LoginRequestModel requestModel){
        return loginService.validate(requestModel);
    }

}
