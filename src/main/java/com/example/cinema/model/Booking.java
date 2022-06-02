package com.example.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Booking() {}

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                '}';
    }
}
