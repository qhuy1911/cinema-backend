package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Booking;
import com.example.cinema.repository.BookingRepository;
import com.example.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{userId}/bookings")
    public ResponseEntity<List<Booking>> getAllBookingsByUserId(@PathVariable("userId") long userId) {
        if (userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found USER with id =" + userId);
        }
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found BOOKING with id =" + id));
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/bookings")
    public ResponseEntity<Booking> createBooking(@PathVariable("userId") long userId, @RequestBody Booking booking) {
        Booking _booking = userRepository.findById(userId).map(user -> {
            booking.setUser(user);
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found USER with id = " + userId));

        return new ResponseEntity<>(_booking, HttpStatus.OK);
    }
}
