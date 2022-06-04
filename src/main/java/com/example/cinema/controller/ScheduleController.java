package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Schedule;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies/{movieId}/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedulesByMovieId(@PathVariable("movieId") long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Not found MOVIE with id = " + movieId);
        }
        List<Schedule> schedules = scheduleRepository.findByMovieId(movieId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SCHEDULE with id = " + id));
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

}
