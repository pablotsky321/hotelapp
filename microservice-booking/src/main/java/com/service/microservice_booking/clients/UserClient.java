package com.service.microservice_booking.clients;

import com.service.microservice_booking.DTOs.RoomDTO;
import com.service.microservice_booking.DTOs.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "microservice-user",url = "${microservices.user-uri}")
public interface UserClient {

    @GetMapping(value = "/api/v1/user/userByNumDoc")
    Optional<UserDTO> getUser(@RequestParam(value = "numDoc")  String numDoc);


}
