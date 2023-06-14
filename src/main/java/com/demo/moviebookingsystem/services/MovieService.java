package com.demo.moviebookingsystem.services;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.entities.TheatreMovieSchedule;
import com.demo.moviebookingsystem.repositories.MovieRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Movie save(Movie movie) {
        Optional<Movie> movieOptional = movieRepository.findByName(movie.getName());
        if (movieOptional.isPresent()) {
            return movieOptional.get();
        } else {
            log.info("Saving record: {}", movie);
            movie = movieRepository.saveAndFlush(movie);
            log.info("Saved record: {}", movie);
            return movie;
        }
    }

    @Transactional
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Transactional
    public List<Movie> findByName(String name) {
        return movieRepository.findByNameContainingIgnoreCaseOrderByName(name);
    }

    @Transactional
    public Set<TheatreMovieSchedule> listTheatres(Long movieId) throws NotFoundException {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("No movie found having id: " + movieId);
        }
        return movieOptional.get().getMovieSchedules();
    }
}
