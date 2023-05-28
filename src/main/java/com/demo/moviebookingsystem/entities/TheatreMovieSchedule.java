package com.demo.moviebookingsystem.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TheatreMovieSchedule {

    @EmbeddedId
    private TheatreMovieScheduleKey id;

    @ManyToOne
    @MapsId("theatreId")
    private Theatre theatre;

    @ManyToOne
    @MapsId("movieId")
    private Movie movie;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @PrePersist
    public void initId() {
        this.id = new TheatreMovieScheduleKey();
        this.id.setTheatreId(this.theatre.getId());
        this.id.setMovieId(this.movie.getId());
    }

}
