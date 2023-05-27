package com.demo.moviebookingsystem.controller;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.services.TheatreService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/theatre")
public class TheatreController extends GenericRestControllerAdvice {

    @Autowired
    private TheatreService theatreService;

    @GetMapping("/list/{name}")
    public ResponseEntity<List<Theatre>> listTheatres(@PathVariable String name) {
        List<Theatre> theatres = theatreService.findByName(name);
        HttpStatus status = theatres.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(theatres, status);
    }

    @PostMapping("/save")
    public ResponseEntity<Theatre> saveTheatre(@RequestBody Theatre theatre) {
        return new ResponseEntity<>(theatreService.save(theatre), HttpStatus.CREATED);
    }

    @PostMapping("/add-movie/{theatreId}/{movieId}")
    public ResponseEntity<Theatre> addMovieInTheatre(
            @PathVariable Long theatreId, @PathVariable Long movieId) throws NotFoundException {
        return new ResponseEntity<>(theatreService.addMovieInTheatre(theatreId, movieId), HttpStatus.OK);
    }

    @GetMapping("/list-movies/{theatreId}")
    public ResponseEntity<Set<Movie>> listMovie(@PathVariable Long theatreId) throws NotFoundException {
        return new ResponseEntity<>(theatreService.listAllMovies(theatreId), HttpStatus.OK);
    }
}
