package com.demo.DSS4MSMOVIE.controller;

import com.demo.DSS4MSMOVIE.model.Movie;
import com.demo.DSS4MSMOVIE.model.MovieRequestModel;
import com.demo.DSS4MSMOVIE.model.MovieSearchModel;
import com.demo.DSS4MSMOVIE.service.MovieService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {

    @Autowired
    MovieService movieService;


    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody MovieRequestModel requestModel){
        Movie movie = movieService.save(requestModel);
        return "Added new Movie";
    }

    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Movie> findAll(@RequestBody MovieSearchModel searchModel){
        return movieService.findAll(searchModel);
    }

    @DeleteMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestBody MovieRequestModel requestModel){
         movieService.delete(requestModel);
         return "Movie delete";
    }

    @PutMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody MovieRequestModel requestModel){
        movieService.update(requestModel);
        return "Updated movie details";
    }


    @GetMapping("/findByMovieId/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Success")})
    @ResponseStatus(HttpStatus.OK)
    public Movie findById(@PathVariable int id){
        return movieService.findById(id);
    }




}
