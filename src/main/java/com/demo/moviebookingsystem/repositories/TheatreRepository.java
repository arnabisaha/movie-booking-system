package com.demo.moviebookingsystem.repositories;

import com.demo.moviebookingsystem.entities.Theatre;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    Optional<Theatre> findByName(@NonNull String name);

    List<Theatre> findByNameContainingIgnoreCaseOrderByName(@NonNull String name);

}
