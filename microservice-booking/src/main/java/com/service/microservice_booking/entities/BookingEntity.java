package com.service.microservice_booking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate bookingDate;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private int nights;
    private String userNumDoc;
    private long idRoom;

}
