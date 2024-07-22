package com.service.microservice_booking.controllers;

import com.service.microservice_booking.exceptions.FieldEmptyException;
import com.service.microservice_booking.exceptions.NotFreeException;
import com.service.microservice_booking.requests.CreateBookingRequest;
import com.service.microservice_booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/allBookings")
    public ResponseEntity<?> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/bookingById/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable(value = "id") long id){
        try{
            return new ResponseEntity<>(bookingService.getBookingById(id),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bookingsByRoom/{roomNumber}")
    public ResponseEntity<?> getBookingsByRoom(@PathVariable(value = "roomNumber") int roomNumber){
        try {
            return new ResponseEntity<>(bookingService.getBookingsByRoom(roomNumber),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException | NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookingsByDate")
    public ResponseEntity<?> getBookingsByDate(@RequestBody LocalDate date){
        try{
            return new ResponseEntity<>(bookingService.getBookingsByDate(date),HttpStatus.ACCEPTED);
        }catch(NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookingsByUser/{userNumDoc}")
    public ResponseEntity<?> getBookingsByUser(@PathVariable(value = "userNumDoc") String userNumDoc){
        try {
            return new ResponseEntity<>(bookingService.getBookingsByUser(userNumDoc),HttpStatus.ACCEPTED);
        }catch (ClassNotFoundException | NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addBooking")
    public ResponseEntity<?> addBooking(@RequestBody CreateBookingRequest request){
        try {
            return new ResponseEntity<>(bookingService.addBooking(request),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException | FieldEmptyException | NotFreeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteBooking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable(value = "id") long id){
        try {
            return new ResponseEntity<>(bookingService.deleteBooking(id),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
