package com.demo.moviebookingsystem.repositories;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.entities.TheatreMovieSchedule;
import com.demo.moviebookingsystem.entities.TheatreMovieScheduleKey;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TheatreMovieScheduleRepository extends JpaRepository<TheatreMovieSchedule, TheatreMovieScheduleKey> {

    Optional<TheatreMovieSchedule> findByTheatreAndMovie(@NonNull Theatre theatre, @NonNull Movie movie);

    Set<TheatreMovieSchedule> findByTheatre(Theatre theatre);

}
