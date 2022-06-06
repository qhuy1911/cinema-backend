package com.example.cinema.repository;

import com.example.cinema.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {
    List<BookingDetail> findByBookingId(long bookingId);
}
