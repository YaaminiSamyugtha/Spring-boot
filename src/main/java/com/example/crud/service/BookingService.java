package com.example.crud.service;

import com.example.crud.model.Booking;
import com.example.crud.model.Passenger;

import java.util.List;

public interface BookingService {

    Booking getBookingById(long bookingId);
    void deleteBooking(long id);
//    Booking createBookingBooking(long jid,List<Passenger> passengers);
}
