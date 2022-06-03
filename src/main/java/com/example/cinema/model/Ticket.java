package com.example.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price")
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Ticket() {}

    public Ticket(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
