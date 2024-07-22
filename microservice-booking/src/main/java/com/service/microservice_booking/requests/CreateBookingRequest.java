 package com.service.microservice_booking.requests;

import com.service.microservice_booking.DTOs.RoomDTO;
import com.service.microservice_booking.DTOs.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBookingRequest {

    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int nights;
    private int roomNumber;
    private String userNumDoc;

}
