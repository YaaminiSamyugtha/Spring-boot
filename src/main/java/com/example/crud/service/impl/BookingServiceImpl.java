package com.example.crud.service.impl;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Booking;
import com.example.crud.model.Journey;
import com.example.crud.model.Passenger;
import com.example.crud.repository.BookingRepository;
import com.example.crud.repository.JourneyRepository;
import com.example.crud.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private JourneyRepository journeyRepository;
    public BookingServiceImpl(BookingRepository bookingRepository, JourneyRepository journeyRepository){
        super();
        this.bookingRepository=bookingRepository;
        this.journeyRepository=journeyRepository;
    }

    @Override
    public Booking getBookingById(long bookingId){
        Optional<Booking> booking =bookingRepository.findById(bookingId);
        if(booking.isPresent()) {
            return booking.get();
        }
        else{
            throw new ResourceNotFoundException("Booking","Id",bookingId);
        }
    }

//    @Override
//    public Booking createBookingBooking(long jid,List<Passenger> passengers){
//        Journey exisitingJourney=journeyRepository.getById(jid);
//
//        Booking booking=new Booking();
//        booking.setPassenger(passengers);
//        booking.setJourney(exisitingJourney);
//        return bookingRepository.save(booking);
//    }
    @Override
    public void deleteBooking(long id){
        bookingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Booking","Id",id));
        bookingRepository.deleteById(id);
    }
}
