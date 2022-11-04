package com.demo.DSS5MSREVIEW.controller;

import com.demo.DSS5MSREVIEW.model.Review;
import com.demo.DSS5MSREVIEW.model.ReviewRequestModel;
import com.demo.DSS5MSREVIEW.service.ReviewService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/review")
@CrossOrigin
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody ReviewRequestModel requestModel){
        reviewService.save(requestModel);
        return "Added new movie review";
    }

    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Review> findAll(){
        return reviewService.findAll();
    }

}
