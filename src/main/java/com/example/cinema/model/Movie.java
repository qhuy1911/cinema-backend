package com.example.cinema.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "duration")
    private int duration;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "trailer")
    private String trailer;

    public Movie(){}

    public Movie(String name, String description, String director, int duration, Date startDate, String trailer) {
        this.name = name;
        this.description = description;
        this.director = director;
        this.duration = duration;
        this.startDate = startDate;
        this.trailer = trailer;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", trailer='" + trailer + '\'' +
                '}';
    }
}
