package com.example.crud.service.impl;

import com.example.crud.DTO.UserDTO;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Booking;
import com.example.crud.model.Journey;
import com.example.crud.model.Passenger;
import com.example.crud.repository.JourneyRepository;
import org.springframework.stereotype.Service;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private JourneyRepository journeyRepository;


    public UserServiceImpl(UserRepository userRepository,JourneyRepository journeyRepository) {
        super();
        this.userRepository = userRepository;
        this.journeyRepository=journeyRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(UserDTO userDTO){
        User newUser = new User();
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setEmail(userDTO.getEmail());


        // Save the new user to the database
        newUser = userRepository.save(newUser);
        return newUser;

    }

    @Override
    public Journey saveJourney(Journey journey) {
        return journeyRepository.save(journey);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user =userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        else{
            throw new ResourceNotFoundException("User","Id",id);
        }
    }

    @Override
    public UserDTO getUserDTOById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return new UserDTO(user.getId(), user.getFirstName(),user.getLastName(),user.getEmail());
        }
        return null;
    }


    @Override
    public User updateUser(User user, long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User","Id",id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        userRepository.save(existingUser);
        return existingUser;
    }
     @Override
     public Booking bookTicket(Booking booking,long id){
         User existingUser = userRepository.findById(id).orElseThrow(
                 ()-> new ResourceNotFoundException("User","Id",id));
         List<Booking> ebooking=existingUser.getBooking();
         ebooking.add(booking);
         existingUser.setBooking(ebooking);
         userRepository.save(existingUser);
         return booking;
     }

     @Override
     public Booking bookTicketJourney(List<Passenger> passengers,long uid,long jid){
         User existingUser = userRepository.findById(uid).orElseThrow(
                 ()-> new ResourceNotFoundException("User","Id",uid));
         Journey existingJourney= journeyRepository.findById(jid).orElseThrow(
                 ()-> new ResourceNotFoundException("Journey","Id",jid));
         List<Booking> existingBooking=existingUser.getBooking();
         Booking booking=new Booking();
         booking.setJourney(existingJourney);
         booking.setPassenger(passengers);
         existingBooking.add(booking);
         existingUser.setBooking(existingBooking);
         userRepository.save(existingUser);
         return booking;
     }

//    public User updatePassengerFood(String  food,long uid,long bid,long pid){
//        User existingUser = userRepository.findById(uid).orElseThrow(
//                ()-> new ResourceNotFoundException("User","Id",uid));
//        List<Booking> ebooking=existingUser.getBooking();
//        return null;
//    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        userRepository.deleteById(id);
    }

//    @Override
//    public boolean createBookingUser(long uid,Booking booking){
//        User user=userRepository.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User","Id",uid));
//        List<Booking> bookings=user.getBooking();
//        bookings.add(booking);
//        user.setBooking(bookings);
//        return true;
//    }
}
