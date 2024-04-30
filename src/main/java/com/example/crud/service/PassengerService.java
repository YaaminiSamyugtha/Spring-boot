package com.example.crud.service;


import com.example.crud.model.Passenger;

public interface PassengerService {

    Passenger updatePassengerFood(String food, long id);
    void deletePassengerBooking(long id);
}
