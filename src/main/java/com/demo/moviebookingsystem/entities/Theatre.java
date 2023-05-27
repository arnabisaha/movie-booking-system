package com.demo.moviebookingsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "movies")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "theatre_movies",
            joinColumns = @JoinColumn(name = "theatre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @JsonIgnore
    private Set<Movie> movies;   // owning side

    public void addMovie(Movie movie) {
        if (Objects.isNull(movies)) {
            this.movies = new LinkedHashSet<>();
        }
        this.movies.add(movie);
    }

}
