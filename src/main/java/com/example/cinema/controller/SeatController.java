package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Seat;
import com.example.cinema.repository.RoomRepository;
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
    RoomRepository roomRepository;

    @GetMapping("/rooms/{roomId}/seats")
    public ResponseEntity<List<Seat>> getAllSeatsByRoomId(@PathVariable("roomId") long roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new ResourceNotFoundException("Not found ROOM with id = " + roomId);
        }
        List<Seat> seats = seatRepository.findByRoomId(roomId);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("id") long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SEAT with id = " + id));
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @PostMapping("/rooms/{roomId}/seats")
    public ResponseEntity<Seat> createSchedule(@PathVariable("roomId") long roomId, @RequestBody Seat seat) {
        Seat _seat = roomRepository.findById(roomId).map(room -> {
            seat.setRoom(room);
            return seatRepository.save(seat);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found ROOM with id = " + roomId));

        return new ResponseEntity<>(_seat, HttpStatus.CREATED);
    }
}
