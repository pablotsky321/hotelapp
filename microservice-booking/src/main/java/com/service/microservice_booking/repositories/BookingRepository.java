package com.service.microservice_booking.repositories;

import com.service.microservice_booking.entities.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {

    List<BookingEntity> findByBookingDate(LocalDate bookingDate);

    List<BookingEntity> findByIdRoom(long idRoom);

    List<BookingEntity> findByUserNumDoc(String userNumDoc);

}