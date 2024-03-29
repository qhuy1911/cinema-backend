package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Movie;
import com.example.cinema.model.Room;
import com.example.cinema.model.Schedule;
import com.example.cinema.model.Theater;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.repository.RoomRepository;
import com.example.cinema.repository.ScheduleRepository;
import com.example.cinema.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        scheduleRepository.findAll().forEach(schedules::add);
        if (schedules.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/theaters/{theaterId}/schedules")
    public ResponseEntity<List<Schedule>> getAllSchedulesByTheaterId(@PathVariable("theaterId") long theaterId) {
        if (!theaterRepository.existsById(theaterId)) {
            throw new ResourceNotFoundException("Not found THEATER with id = " + theaterId);
        }
        List<Schedule> schedules = scheduleRepository.findByTheaterId(theaterId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

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

    @PostMapping("/movies/{movieId}/rooms/{roomId}/theaters/{theatersId}/schedules")
    public ResponseEntity<Schedule> createSchedule(@PathVariable("movieId") long movieId, @PathVariable("roomId") long roomId, @PathVariable("theatersId") long theatersId, @RequestBody Schedule schedule) {
        // Get Movie
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found MOVIE with id = " + movieId));
        // Get Room
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found ROOM with id = " + roomId));
        // Get theater
        Theater theater = theaterRepository.findById(theatersId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found THEATER with id = " + theatersId));
        // Save schedule
        schedule.setMovie(movie);
        schedule.setRoom(room);
        schedule.setTheater(theater);
        Schedule _schedule = scheduleRepository.save(schedule);

        return new ResponseEntity<>(_schedule, HttpStatus.CREATED);
    }

    @PutMapping("/movies/{movieId}/rooms/{roomId}/theaters/{theatersId}/schedules/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable("id") long id, @PathVariable("movieId") long movieId, @PathVariable("roomId") long roomId, @PathVariable("theatersId") long theatersId, @RequestBody Schedule schedule) {
        // Get Movie
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found MOVIE with id = " + movieId));
        // Get Room
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found ROOM with id = " + roomId));
        // Get THeater
        Theater theater = theaterRepository.findById(theatersId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found THEATER with id = " + theatersId));
        // Get Schedule
        Schedule _schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SCHEDULE with id = " + id));

        // Update Schedule
        _schedule.setMovie(movie);
        _schedule.setRoom(room);
        _schedule.setTheater(theater);
        _schedule.setDatetime(schedule.getDatetime());
        return new ResponseEntity<>(scheduleRepository.save(_schedule), HttpStatus.OK);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable("id") long id) {
        scheduleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/movies/{movieId}/schedules")
    public ResponseEntity<Schedule> deleteAllSchedulesByMovieId(@PathVariable("movieId") long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("Not found MOVIE with id = " + movieId);
        }
        scheduleRepository.deleteByMovieId(movieId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/rooms/{roomId}/schedules")
    public ResponseEntity<Schedule> deleteAllScheduleByRoomId(@PathVariable("movieId") long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new ResourceNotFoundException("Not found ROOM with id = " + roomId);
        }
        scheduleRepository.deleteByRoomId(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
