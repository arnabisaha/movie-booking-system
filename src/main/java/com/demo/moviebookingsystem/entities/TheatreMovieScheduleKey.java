package com.demo.moviebookingsystem.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class TheatreMovieScheduleKey implements Serializable {

    private Long theatreId;
    private Long movieId;

}
