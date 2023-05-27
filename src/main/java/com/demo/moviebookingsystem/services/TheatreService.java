package com.demo.moviebookingsystem.services;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.repositories.MovieRepository;
import com.demo.moviebookingsystem.repositories.TheatreRepository;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Theatre save(Theatre theatre) {
        if (theatreRepository.existsByNameIgnoreCase(theatre.getName())) {
            return theatreRepository.findByName(theatre.getName()).get();
        } else {
            log.info("Saving record: {}", theatre);
            theatre = theatreRepository.saveAndFlush(theatre);
            log.info("Saved record: {}", theatre);
            return theatre;
        }
    }

    @Transactional
    public List<Theatre> listAll() {
        return theatreRepository.findAll();
    }

    @Transactional
    public List<Theatre> findByName(String name) {
        return theatreRepository.findByNameContainingIgnoreCaseOrderByName(name);
    }

    @Transactional
    public void saveMovies(Theatre theatre) {
        theatreRepository.saveAndFlush(theatre);
    }

    @Transactional
    public Theatre addMovieInTheatre(Long theatreId, Long movieId) throws NotFoundException {
        // Fetch movie and theatre by id
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isEmpty()) {
            throw new NotFoundException("No theatre found having id: " + theatreId);
        }
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("No movie found having id: " + movieId);
        }

        // Add movie to theatre if it's not already added
        Theatre theatre = theatreOptional.get();
        if (!isTheatreContainsMovie(theatre, movieOptional.get())) {
            log.info("Adding movie having id: {} to theatre having id: {}",
                    movieOptional.get().getId(), theatre.getId());
            theatre.addMovie(movieOptional.get());
            saveMovies(theatre);
        }
        return theatre;
    }

    private boolean isTheatreContainsMovie(Theatre theatre, Movie movie) {
        return theatre.getMovies().contains(movie);
    }

    @Transactional
    public Set<Movie> listAllMovies(Long theatreId) throws NotFoundException {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isEmpty()) {
            throw new NotFoundException("No theatre found having id: " + theatreId);
        }
        return theatreOptional.get().getMovies();
    }
}
