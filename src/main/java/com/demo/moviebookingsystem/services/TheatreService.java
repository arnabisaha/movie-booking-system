package com.demo.moviebookingsystem.services;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.entities.TheatreMovieSchedule;
import com.demo.moviebookingsystem.repositories.MovieRepository;
import com.demo.moviebookingsystem.repositories.TheatreMovieScheduleRepository;
import com.demo.moviebookingsystem.repositories.TheatreRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class TheatreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private TheatreMovieScheduleService theatreMovieScheduleService;

    @Transactional
    public Theatre save(Theatre theatre) {
        Optional<Theatre> theatreOptional = theatreRepository.findByName(theatre.getName());
        if (theatreOptional.isPresent()) {
            return theatreOptional.get();
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
    public TheatreMovieSchedule addMovieInTheatre(Long theatreId, Long movieId,
                                                  LocalDateTime startTime, LocalDateTime endTime
    ) throws NotFoundException {
        return theatreMovieScheduleService.addMovieInTheatre(theatreId, movieId, startTime, endTime);
    }

    @Transactional
    public Set<TheatreMovieSchedule> listAllMovies(Long theatreId) throws NotFoundException {
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isEmpty()) {
            throw new NotFoundException("No theatre found having id: " + theatreId);
        }
        return theatreMovieScheduleService.findMovies(theatreOptional.get());
    }
}
