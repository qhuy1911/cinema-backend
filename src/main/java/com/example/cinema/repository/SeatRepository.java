package com.example.cinema.repository;

import com.example.cinema.model.Schedule;
import com.example.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScheduleId(Long scheduleId);

    @Transactional
    void deleteByScheduleId(Long scheduleId);
}
