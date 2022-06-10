package com.example.cinema.repository;

import com.example.cinema.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMovieId(Long movieId);

    @Transactional
    void deleteByMovieId(long movieId);

    @Transactional
    void deleteByRoomId(long roomId);
}
