package com.demo.moviebookingsystem.controller;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.entities.TheatreMovieSchedule;
import com.demo.moviebookingsystem.services.MovieService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController extends GenericRestControllerAdvice {

    @Autowired
    private MovieService movieService;

    @GetMapping("/list/{name}")
    public ResponseEntity<List<Movie>> listMovies(@PathVariable String name) {
        List<Movie> movies = movieService.findByName(name);
        HttpStatus status = movies.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }

    @GetMapping("/list-theatre/{movieId}")
    public ResponseEntity<Set<TheatreMovieSchedule>> listTheatres(@PathVariable Long movieId) throws NotFoundException {
        return new ResponseEntity<>(movieService.listTheatres(movieId), HttpStatus.OK);
    }
}
