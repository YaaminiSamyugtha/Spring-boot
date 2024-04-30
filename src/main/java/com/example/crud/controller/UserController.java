package com.example.crud.controller;

import com.example.crud.DTO.UserDTO;
import com.example.crud.model.Booking;
import com.example.crud.model.Journey;
import com.example.crud.model.Passenger;
import com.example.crud.service.BookingService;
import com.example.crud.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.model.User;
import com.example.crud.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {
    private UserService userService;
    private BookingService bookingService;
    private PassengerService passengerService;

    public UserController(UserService userService, BookingService bookingService, PassengerService passengerService) {
        super();
        this.userService = userService;
        this.bookingService = bookingService;
        this.passengerService = passengerService;
    }

    //create REST API
    @PostMapping("users/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("journey/save")
    public ResponseEntity<Journey> saveJourney(@RequestBody Journey journey) {
        return new ResponseEntity<Journey>(userService.saveJourney(journey), HttpStatus.CREATED);
    }

    //get REST API
    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //get by id REST API
    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long userId) {
        return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("userDetails/{id}")
    public ResponseEntity<UserDTO> getUserDTOById(@PathVariable("id") long id) {
//        UserDTO userDTO = userService.getUserDTOById(id);
//        if (userDTO != null) {
//            return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
//        }
//        return ResponseEntity.notFound().build();
        return new ResponseEntity<UserDTO>(userService.getUserDTOById(id), HttpStatus.OK);
    }

    @PostMapping("create/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        UserDTO newUserDTO = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(newUserDTO);
    }

//    @PostMapping("book/user/{uid}/journey/{jid}")
//    public ResponseEntity<Booking> createBooking(@PathVariable("uid") long uid, @PathVariable("jid") long jid, @RequestBody List<Passenger> passengers) {
//        Booking booking = bookingService.createBookingBooking(jid, passengers);
//        if (userService.createBookingUser(uid, booking)) {
//            return new ResponseEntity<Booking>(booking, HttpStatus.OK);
//        }
//        else return null;
//    }

    @GetMapping("booking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long bookingId){
        return new ResponseEntity<Booking>(bookingService.getBookingById(bookingId),HttpStatus.OK);
    }

    //get bookings of a user
    @GetMapping("users/{id}/bookings")
    public List<Booking> getBookingByUser(@PathVariable("id") long userId){
        User user=userService.getUserById(userId);
        List<Booking> booking=user.getBooking();
        return booking;
        //return (List<Booking>) new ResponseEntity<List<Booking>>(booking,HttpStatus.OK);
    }
    //get booking
    @GetMapping("users/{id}/booking/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long userId,@PathVariable("bookingId") long bookingId){
        User user=userService.getUserById(userId);
        List<Booking> bookings=user.getBooking();
        Booking result=null;
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                result = booking;
                break; // Stop searching once the object is found
            }
        }
        if (result != null) {
            return ResponseEntity.ok(result); // Return found booking
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if booking not found
        }
    }


    //update Rest API
    @PutMapping("users/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,@RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user,id),HttpStatus.OK);
    }

    //book ticket
    @PutMapping("users/{id}/book")
    public ResponseEntity<Booking> bookTicket(@PathVariable("id") long id,@RequestBody Booking booking){
        return new ResponseEntity<Booking>(userService.bookTicket(booking,id),HttpStatus.OK);
    }

    //book ticket for a journey
    @PostMapping("users/{uid}/book/{jid}")
    public ResponseEntity<Booking> bookTicketJourney(@PathVariable("uid") long uid,@PathVariable("jid") long jid,@RequestBody List<Passenger> passengers){
        return new ResponseEntity<Booking>(userService.bookTicketJourney(passengers,uid,jid),HttpStatus.OK);
    }

    //cancel ticket
    @DeleteMapping("booking/delete/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") long id){

        bookingService.deleteBooking(id);
        return  new ResponseEntity<String>("Deleted",HttpStatus.OK);
    }
    @DeleteMapping("passenger/delete/{id}")
    public ResponseEntity<String> deletePassengerBooking(@PathVariable("id") long id){

        passengerService.deletePassengerBooking(id);
        return  new ResponseEntity<String>("Deleted",HttpStatus.OK);
    }

    //update passenger food preference
//    @PutMapping("users/{uid}/passengers/{pid}")
//    public ResponseEntity<User> updatePassengerFood(@PathVariable("uid") long uid,@PathVariable("pid") long pid,@RequestBody String food){
//        return null;
//    }

    //update passenger food preference
    @PutMapping("passengers/updateFood/{id}")
    public  ResponseEntity<Passenger> updatePassengerFood(@PathVariable("id") long id,@RequestBody String  food){
        return new ResponseEntity<Passenger>(passengerService.updatePassengerFood(food,id),HttpStatus.OK);
    }

    //delete REST API
    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){

        userService.deleteUser(id);
        return  new ResponseEntity<String>("Deleted",HttpStatus.OK);
    }

}
