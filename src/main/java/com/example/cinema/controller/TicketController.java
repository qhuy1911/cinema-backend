package com.example.cinema.controller;

import com.example.cinema.exception.ResourceNotFoundException;
import com.example.cinema.model.Schedule;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.ScheduleRepository;
import com.example.cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/schedules/{scheduleId}/tickets")
    public ResponseEntity<List<Ticket>> getAllTicketByScheduleId(@PathVariable("scheduleId") long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new ResourceNotFoundException("Not found SCHEDULE with id = " + scheduleId);
        }
        List<Ticket> tickets = ticketRepository.findByScheduleId(scheduleId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found TICKET with id = " + id));
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/schedules/{scheduleId}/tickets")
    public ResponseEntity<Ticket> createTicket(@PathVariable("scheduleId") long scheduleId, @RequestBody Ticket ticket) {
        Ticket _ticket = scheduleRepository.findById(scheduleId).map(schedule -> {
            ticket.setSchedule(schedule);
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found SCHEDULE with id = " + scheduleId));
        return new ResponseEntity<>(_ticket, HttpStatus.CREATED);
    }
}
