package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Room;
import com.example.cinema.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        if (rooms.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found ROOM with id = " + id));
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room _room = roomRepository.save(new Room(
                room.getName()
        ));
        return  new ResponseEntity<>(_room, HttpStatus.OK);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
        Room _room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found ROOM with id = " + id));

        _room.setName(room.getName());
        return new ResponseEntity<>(roomRepository.save(_room), HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable("id") long id ) {
        roomRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/rooms")
    public ResponseEntity<Room> deleteAllRooms() {
        roomRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
