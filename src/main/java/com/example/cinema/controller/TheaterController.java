package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Theater;
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
public class TheaterController {
    @Autowired
    TheaterRepository theaterRepository;

    @GetMapping("/theaters")
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = new ArrayList<>();
        theaterRepository.findAll().forEach(theaters::add);
        if (theaters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(theaters, HttpStatus.OK);
    }

    @GetMapping("/theaters/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable long id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found THEATER with id = " + id));
        return new ResponseEntity<>(theater, HttpStatus.OK);
    }

    @PostMapping("/theaters")
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater _theater = theaterRepository.save(new Theater(
                theater.getName(),
                theater.getAddress(),
                theater.getImage()
        ));
        return new ResponseEntity<>(_theater, HttpStatus.CREATED);
    }

    @DeleteMapping("/theaters/{id}")
    public ResponseEntity<Theater> deleteTheater(@PathVariable long id) {
        theaterRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
