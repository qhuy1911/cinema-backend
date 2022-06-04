package com.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "datetime")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date datetime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", datetime=" + datetime +
                '}';
    }
}
