package com.example.cinema.repository;

import com.example.cinema.model.Schedule;
import com.example.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByRoomId(Long roomId);
}
