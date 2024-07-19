package com.service.microservice_booking.clients;

import com.service.microservice_booking.DTOs.RoomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "microservice-room",url = "${microservices.room-uri}")
public interface RoomClient {

    @GetMapping("/api/v1/room/roomByNumber/{number}")
    Optional<RoomDTO> getRoom(@PathVariable("number") int number);

}
