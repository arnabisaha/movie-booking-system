package com.demo.moviebookingsystem.repositories;

import com.demo.moviebookingsystem.entities.Movie;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(@NonNull String name);

    List<Movie> findByNameContainingIgnoreCaseOrderByName(@NonNull String name);

}
