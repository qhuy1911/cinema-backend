package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Room;
import com.example.cinema.model.Schedule;
import com.example.cinema.model.Seat;
import com.example.cinema.repository.ScheduleRepository;
import com.example.cinema.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatController {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/schedules/{scheduleId}/seats")
    public ResponseEntity<List<Seat>> getAllSeatsByScheduleId(@PathVariable("scheduleId") long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ResourceNotFoundException("Not found SCHEDULE with id = " + scheduleId);
        }
        List<Seat> seats = seatRepository.findByScheduleId(scheduleId);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SEAT with id = " + id));
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @PostMapping("/schedules/{schedulesId}/seats")
    public ResponseEntity<Seat> createSeat(@PathVariable("schedulesId") long scheduleId, @RequestBody Seat seat) {
        Seat _seat = scheduleRepository.findById(scheduleId).map(schedule -> {
            seat.setSchedule(schedule);
            return seatRepository.save(seat);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found SCHEDULE with id = " + scheduleId));

        return new ResponseEntity<>(_seat, HttpStatus.CREATED);
    }

    @PutMapping("/schedules/{scheduleId}/seats/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable("id") long id, @PathVariable("scheduleId") long scheduleId, @RequestBody Seat seat) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SCHEDULE with id = " + scheduleId));
        Seat _seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SEAT with id = " + id));
        _seat.setSchedule(schedule);
        _seat.setName(seat.getName());
        _seat.setStatus(seat.isStatus());

        return new ResponseEntity<>(seatRepository.save(_seat), HttpStatus.OK);
    }

    @PutMapping("/seats/{id}")
    public ResponseEntity<Seat> updateStatusSeat(@PathVariable("id") long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SEAT with id = " + id));

        seat.setStatus(!seat.isStatus());
        return new ResponseEntity<>(seatRepository.save(seat), HttpStatus.OK);
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Seat> deleteSeatById(@PathVariable("id") long id) {
        seatRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/rooms/{roomId}/seats")
    public ResponseEntity<Seat> deleteAllSeatByRoomId(@PathVariable("roomId") long roomId) {
        seatRepository.deleteByScheduleId(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
