package com.example.cinema.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "datetime")
    private Date datetime;

    public Schedule () {}

    public Schedule(Date datetime) {
        this.datetime = datetime;
    }

    public long getId() {
        return id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", datetime=" + datetime +
                '}';
    }
}
