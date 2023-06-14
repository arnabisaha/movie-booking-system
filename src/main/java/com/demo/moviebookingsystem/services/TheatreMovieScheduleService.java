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
public class TheatreMovieScheduleService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private TheatreMovieScheduleRepository theatreMovieScheduleRepository;

    @Transactional
    public TheatreMovieSchedule save(TheatreMovieSchedule theatreMovieSchedule) {
        return theatreMovieScheduleRepository.saveAndFlush(theatreMovieSchedule);
    }

    @Transactional
    public List<TheatreMovieSchedule> listAll() {
        return theatreMovieScheduleRepository.findAll();
    }

    @Transactional
    public TheatreMovieSchedule addMovieInTheatre(
            Long theatreId, Long movieId, LocalDateTime startTime, LocalDateTime endTime) throws NotFoundException {
        // Fetch movie and theatre by id
        Optional<Theatre> theatreOptional = theatreRepository.findById(theatreId);
        if (theatreOptional.isEmpty()) {
            throw new NotFoundException("No theatre found having id: " + theatreId);
        }
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("No movie found having id: " + movieId);
        }
        TheatreMovieSchedule theatreMovieSchedule = TheatreMovieSchedule
                .builder()
                .theatre(theatreOptional.get())
                .movie(movieOptional.get())
                .startTime(startTime)
                .endTime(endTime)
                .build();
        return theatreMovieScheduleRepository.saveAndFlush(theatreMovieSchedule);
    }

    @Transactional
    public Set<TheatreMovieSchedule> findMovies(Theatre theatre) {
        return theatreMovieScheduleRepository.findByTheatre(theatre);
    }
}
