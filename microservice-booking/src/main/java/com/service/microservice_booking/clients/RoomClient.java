package com.service.microservice_booking.clients;

import com.service.microservice_booking.DTOs.RoomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "microservice-room",url = "${microservices.room-uri}")
public interface RoomClient {

    @GetMapping("/api/v1/room/roomById/{id}")
    Optional<RoomDTO> getRoomById(@PathVariable("id") long id);

    @GetMapping("/api/v1/room/roomByNumber/{number}")
    Optional<RoomDTO> getRoomByRoomNumber(@PathVariable("number") int roomNumber);

    @PutMapping("/api/v1/room/changeState")
    Optional<Integer> changeState(@RequestParam(value = "roomNumber") int roomNumber, @RequestParam(value = "idState") long idState);

}
