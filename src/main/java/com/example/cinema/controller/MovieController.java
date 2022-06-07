package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Movie;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

//    @Autowired
//    ScheduleRepository scheduleRepository;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        if (movies.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found MOVIE with id = " + id));
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping("/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie _movie = movieRepository.save(new Movie(
                movie.getName(),
                movie.getDescription(),
                movie.getDirector(),
                movie.getDuration(),
                movie.getStartDate(),
                movie.getTrailer()
        ));
        return new ResponseEntity<>(_movie, HttpStatus.CREATED);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Movie _movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found MOVIE with id = " + id));
        _movie.setName(movie.getName());
        _movie.setDescription(movie.getDescription());
        _movie.setDirector(movie.getDirector());
        _movie.setDuration(movie.getDuration());
        _movie.setStartDate(movie.getStartDate());
        _movie.setTrailer(movie.getTrailer());

        return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") long id) {
//        scheduleRepository.deleteByMovieId(id);
        movieRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/movies")
    public ResponseEntity<Movie> deleteAllMovies() {
        movieRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
