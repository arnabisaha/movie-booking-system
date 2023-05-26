package com.demo.moviebookingsystem.controller;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/list-movies/{name}")
    public ResponseEntity<List<Movie>> listMovies(@PathVariable String name) {
        List<Movie> movies = movieService.findByName(name);
        HttpStatus status = movies.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    @PostMapping("/save-movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.save(movie), HttpStatus.CREATED);
    }
}