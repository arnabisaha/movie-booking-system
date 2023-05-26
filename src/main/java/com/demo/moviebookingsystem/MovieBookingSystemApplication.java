package com.demo.moviebookingsystem;

import com.demo.moviebookingsystem.entities.Movie;
import com.demo.moviebookingsystem.services.MovieService;
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

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Save records in db
		movieService.save(Movie.builder().name("John Wick 4").build());
		movieService.save(Movie.builder().name("John Wick 3").build());
		movieService.save(Movie.builder().name("The Platform").build());  // movie without ending
		movieService.save(Movie.builder().name("IP Man").build());

		// Extract all saved records
		log.info("Saved records from db: {}", movieService.listAllMovies());
	}
}
