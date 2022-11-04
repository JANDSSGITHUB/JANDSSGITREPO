package com.demo.DSS4MSMOVIE.service;

import com.demo.DSS4MSMOVIE.exception.CustomErrorException;

import com.demo.DSS4MSMOVIE.model.Actor;
import com.demo.DSS4MSMOVIE.model.Movie;
import com.demo.DSS4MSMOVIE.model.MovieRequestModel;
import com.demo.DSS4MSMOVIE.model.MovieSearchModel;
import com.demo.DSS4MSMOVIE.repository.MovieRepository;
import com.demo.DSS4MSMOVIE.repository.MovieRepositoryImpl;
import com.demo.DSS4MSMOVIE.util.FeignServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    FeignServiceUtil feignServiceUtil;


    @Override
    public Movie save(MovieRequestModel requestModel) {
            validate(requestModel);
            Movie movie = setValues(requestModel);
            return movieRepository.save(movie);
    }

    @Override
    public ArrayList<Movie> findAll(MovieSearchModel searchModel) {
        Movie movie = new Movie(searchModel.getMovieTitle(),searchModel.getMovieCost(),
        searchModel.getMovieYear(),searchModel.getImage(),searchModel.getActorList());
        List<Movie> movieList = movieRepository.findAll(MovieRepositoryImpl.createCriteria(movie));
        return new ArrayList<>(movieList);
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
        movie.setActorList(null);
        movieRepository.delete(movie);
    }

    @Override
    public void update(MovieRequestModel requestModel) {
        Movie movie = findById(requestModel);
        if(requestModel.getMovieTitle()!=null || requestModel.getMovieYear()!=null
                || requestModel.getActorSet()!=null){
            throw new CustomErrorException("Only image and movie cost can be updated");
        }else{
            if(requestModel.getImage()!=null){
                movie.setImage(requestModel.getImage());
            }
            if(requestModel.getMovieCost()!=0){
                movie.setMovieCost(requestModel.getMovieCost());
            }
        }
        movieRepository.save(movie);
    }

    @Override
    public Movie findById(int id) {
        return movieRepository.findByMovieId(id);
    }


    private void validate (MovieRequestModel requestModel){
        if(requestModel.getMovieTitle() == null
        || requestModel.getImage() ==null
        || requestModel.getMovieYear() == null
                || requestModel.getActorSet() == null
                || requestModel.getMovieCost() == 0){
            throw new CustomErrorException("Please fill up all the values.");
        }
        if(requestModel.getMovieTitle()!=null){
            Movie movie = movieRepository.findByMovieTitle(requestModel.getMovieTitle());
            if(movie!=null){
                throw new CustomErrorException("Movie title has already been added");
            }
        }

        for(Actor actorList : requestModel.getActorSet()){
            Actor actor = feignServiceUtil.findActor(actorList.getActorId());
            if(actor==null){
                throw new CustomErrorException("Actor not found");
            }
        }
    }

    private Movie setValues(MovieRequestModel requestModel){
      return new Movie(requestModel.getMovieTitle(),
                requestModel.getMovieCost(), requestModel.getMovieYear(),
                requestModel.getImage(),requestModel.getActorSet());

    }


}
