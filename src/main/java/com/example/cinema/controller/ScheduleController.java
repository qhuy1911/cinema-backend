package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Movie;
import com.example.cinema.model.Room;
import com.example.cinema.model.Schedule;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.RoomRepository;
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
    ScheduleRepository scheduleRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RoomRepository roomRepository;

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

    @PostMapping("/movies/{movieId}/rooms/{roomId}/schedules")
    public ResponseEntity<Schedule> createSchedule(@PathVariable("movieId") long movieId, @PathVariable("roomId") long roomId, @RequestBody Schedule schedule) {
        // Get Movie
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found MOVIE with id = " + movieId));
        // Get Room
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found ROOM with id = " + roomId));
        // Save schedule
        schedule.setMovie(movie);
        schedule.setRoom(room);
        Schedule _schedule = scheduleRepository.save(schedule);

        return new ResponseEntity<>(_schedule, HttpStatus.CREATED);
    }

}
