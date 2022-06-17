package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Booking;
import com.example.cinema.model.BookingDetail;
import com.example.cinema.model.Seat;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.BookingDetailRepository;
import com.example.cinema.repository.BookingRepository;
import com.example.cinema.repository.SeatRepository;
import com.example.cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookingDetailController {

    @Autowired
    BookingDetailRepository bookingDetailRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SeatRepository seatRepository;

    @GetMapping("/bookings/{bookingId}/details")
    public ResponseEntity<List<BookingDetail>> getAllBookingDetailsByBookingId(@PathVariable("bookingId") long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new ResourceNotFoundException("Not found BOOKING with id = " + bookingId);
        }
        List<BookingDetail> bookingDetails = bookingDetailRepository.findByBookingId(bookingId);
        return new ResponseEntity<>(bookingDetails, HttpStatus.OK);
    }

    @GetMapping("/booking-details/{id}")
    public ResponseEntity<BookingDetail> getBookingDetailById(@PathVariable("id") long id) {
        BookingDetail bookingDetail = bookingDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found BOOKING DETAIL with id = " + id));
        return new ResponseEntity<>(bookingDetail, HttpStatus.OK);
    }

    @PostMapping("/bookings/{bookingId}/tickets/{ticketId}/seats/{seatId}/details")
    public ResponseEntity<BookingDetail> createBookingDetail(@PathVariable("bookingId") long bookingId,
                                                             @PathVariable("ticketId") long ticketId,
                                                             @PathVariable("seatId") long seatId,
                                                             @RequestBody() BookingDetail bookingDetail) {
        // Get Booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found BOOKING with id = " + bookingId));

        // Get Ticket
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found TICKET with id = " + ticketId));

        // Get Seat
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found SEAT with id = " + seatId));

        // Save booking detail
        bookingDetail.setBooking(booking);
        bookingDetail.setTicket(ticket);
        bookingDetail.setSeat(seat);

        BookingDetail _bookingDetail = bookingDetailRepository.save(bookingDetail);

        return new ResponseEntity<>(_bookingDetail, HttpStatus.OK);
    }
}
