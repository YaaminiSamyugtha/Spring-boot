package com.example.crud.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long bookingId;
//    @Column(name="num_tickets")
//    private int numTickets;
    //journey
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_journey_id")
    private Journey journey;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_booking_id",referencedColumnName = "id")
    private List<Passenger> passenger;



    public long getBookingId() {
        return bookingId;
    }
}
