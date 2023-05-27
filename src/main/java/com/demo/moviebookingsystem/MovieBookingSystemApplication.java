package com.demo.moviebookingsystem;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.entities.Theatre;
import com.demo.moviebookingsystem.services.MovieService;
import com.demo.moviebookingsystem.services.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MovieBookingSystemApplication implements CommandLineRunner {

	@Autowired
	private MovieService movieService;

	@Autowired
	private TheatreService theatreService;

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
		bolakar.addMovie(jw3);
		bolakar.addMovie(ipMan);
		theatreService.saveMovies(bolakar);
		rupasi.addMovie(thePlatform);
		rupasi.addMovie(jw4);
		theatreService.saveMovies(rupasi);

		// Extract all saved records
		log.info("Saved movies from db: {}", movieService.listAll());
		log.info("Saved theatres from db: {}", theatreService.listAll());
	}
}
