package com.example.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "booking_details")
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public BookingDetail() {}

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "id=" + id +
                '}';
    }
}
