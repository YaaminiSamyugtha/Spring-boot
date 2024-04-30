package com.example.crud.service;

import com.example.crud.DTO.UserDTO;
import com.example.crud.model.Booking;
import com.example.crud.model.Journey;
import com.example.crud.model.Passenger;
import com.example.crud.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(long id);
    UserDTO getUserDTOById(long id);
    User createUser(UserDTO userDTO);

    User updateUser(User user, long id);
    //User updatePassengerFood(String food,long uid,long bid,long pid);
    Booking bookTicket(Booking booking,long id);

    Booking bookTicketJourney(List<Passenger> passengers,long uid, long jid);

    Journey saveJourney(Journey journey);
    void deleteUser(long id);

//    boolean createBookingUser(long uid,Booking booking);
}
