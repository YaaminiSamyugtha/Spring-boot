package com.example.crud.service.impl;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Passenger;
import com.example.crud.model.User;
import com.example.crud.repository.PassengerRepository;
import com.example.crud.service.PassengerService;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {
    private PassengerRepository passengerRepository;
    public PassengerServiceImpl(PassengerRepository passengerRepository){
        super();
        this.passengerRepository= passengerRepository;
    }
    public Passenger updatePassengerFood(String food,long id){
        Passenger existingPassenger = passengerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","Id",id));
        existingPassenger.setFood(food);

        passengerRepository.save(existingPassenger);
        return existingPassenger;
    }

    public void deletePassengerBooking(long id){
        passengerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Passenger","Id",id));
        passengerRepository.deleteById(id);
    }
}
