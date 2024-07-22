package com.service.microservice_booking.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDTO {

    private long id;
    private LocalDate bookingDate;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int nights;
    private RoomDTO room;
    private UserDTO user;

}
