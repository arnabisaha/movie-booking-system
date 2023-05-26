package com.demo.moviebookingsystem.services;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Movie save(Movie movie) {
        if (movieRepository.existsByNameIgnoreCase(movie.getName())) {
            return movieRepository.findByName(movie.getName()).get();
        } else {
            log.info("Saving record: {}", movie);
            movie = movieRepository.saveAndFlush(movie);
            movie.getTheatreList();
            log.info("Saved record: {}", movie);
            return movie;
        }
    }

    @Transactional
    public List<Movie> listAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        allMovies.forEach(Movie::getTheatreList);
        return allMovies;
    }

    @Transactional
    public List<Movie> findByName(String name) {
        return movieRepository.findByNameContainingIgnoreCaseOrderByName(name);
    }
}
