package com.demo.moviebookingsystem;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.services.MovieService;
import com.demo.moviebookingsystem.services.TheatreMovieScheduleService;
import com.demo.moviebookingsystem.services.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class MovieBookingSystemApplication implements CommandLineRunner {

	@Autowired
	private MovieService movieService;

	@Autowired
	private TheatreService theatreService;

	@Autowired
	private TheatreMovieScheduleService theatreMovieScheduleService;

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Save records in db
		Movie jw4 = movieService.save(Movie.builder().name("John Wick 4").build());
		Movie jw3 = movieService.save(Movie.builder().name("John Wick 3").build());
		Movie thePlatform = movieService.save(Movie.builder().name("The Platform").build()); // movie without ending
		Movie ipMan = movieService.save(Movie.builder().name("IP Man").build());

		// Create and save theatre
		Theatre bolakar = theatreService.save(Theatre.builder().name("Bolakar").build());
		Theatre rupasi = theatreService.save(Theatre.builder().name("Rupasi").build());

		// Save movies in theatre
		theatreMovieScheduleService.addMovieInTheatre(
				bolakar.getId(), jw4.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(90));
		theatreMovieScheduleService.addMovieInTheatre(
				bolakar.getId(), jw3.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(90));
		theatreMovieScheduleService.addMovieInTheatre(
				bolakar.getId(), ipMan.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(120));
		theatreMovieScheduleService.addMovieInTheatre(
				rupasi.getId(), thePlatform.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(120));
		theatreMovieScheduleService.addMovieInTheatre(
				rupasi.getId(), jw4.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(120));

		// Extract all saved records
		log.info("Saved movies from db: {}", movieService.listAll());
		log.info("Saved theatres from db: {}", theatreService.listAll());
		log.info("Saved movie schedules from db: {}", theatreMovieScheduleService.listAll());
	}
}
