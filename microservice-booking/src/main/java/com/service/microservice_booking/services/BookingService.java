package com.service.microservice_booking.services;

import com.service.microservice_booking.DTOs.BookingDTO;
import com.service.microservice_booking.requests.CreateBookingRequest;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    List<BookingDTO> getAll();
    BookingDTO getBookingById(long id) throws ClassNotFoundException;
    List<BookingDTO> getBookingsByRoom(int roomNumber) throws ClassNotFoundException, NullPointerException;
    List<BookingDTO> getBookingsByDate(LocalDate date) throws NullPointerException;
    List<BookingDTO> getBookingsByUser(String userNumDoc) throws ClassNotFoundException, NullPointerException;
    int addBooking(CreateBookingRequest request);
    int updateBooking();
    int deleteBooking(long id);


}