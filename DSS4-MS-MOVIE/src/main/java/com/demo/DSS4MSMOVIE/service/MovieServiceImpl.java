package com.demo.DSS4MSMOVIE.service;

import com.demo.DSS4MSMOVIE.exception.CustomErrorException;
import com.demo.DSS4MSMOVIE.model.Actor;
import com.demo.DSS4MSMOVIE.model.Movie;
import com.demo.DSS4MSMOVIE.model.MovieRequestModel;
import com.demo.DSS4MSMOVIE.repository.ActorRepository;
import com.demo.DSS4MSMOVIE.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ActorRepository actorRepository;


    @Override
    public void save(MovieRequestModel requestModel) {
        validate(requestModel);
        Movie movie = setValues(requestModel);
        movieRepository.save(movie);
    }

    @Override
    public ArrayList<Movie> findAll() {
        return new ArrayList<>(movieRepository.findAll());
    }

    private Movie findById(MovieRequestModel requestModel){
        Movie movie = movieRepository.findByMovieId(requestModel.getMovieId());
        if(movie!=null){
            return movie;
        }else throw new CustomErrorException("Movie not found");
    }

    @Override
    public void delete(MovieRequestModel requestModel) {
        Movie movie = findById(requestModel);
        LocalDate today = LocalDate.now();
        LocalDate movieYear = movie.getMovieYear().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if(!movieYear.isBefore(today.minusYears(1))){
            throw new CustomErrorException("Movies that are not older than 1 year cannot be deleted");
        }

        movieRepository.delete(movie);
    }

    private void validate (MovieRequestModel requestModel){
//        if(requestModel.getMovieTitle().isEmpty()
//        || requestModel.getImage().isEmpty()
//        || requestModel.getMovieYear() == null
//                || requestModel.getMovieCost() == 0 || requestModel.getActorId() == 0){
//            throw new CustomErrorException("Please fill up all the values.");
//        }
        Actor actor = actorRepository.findByActorId(requestModel.getActorId());
        if(actor==null){
            throw new CustomErrorException("Actor not found");
        }
    }

    private Movie setValues(MovieRequestModel requestModel){
        Actor actor = actorRepository.findByActorId(requestModel.getActorId());
        return new Movie(requestModel.getMovieTitle(),
                requestModel.getMovieCost(), requestModel.getMovieYear(),
        requestModel.getImage(),actor);
    }
}
