package com.service.microservice_room.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {

    private short capacity;
    private int roomNumber;
    private String description;
    private long roomType;
    private long state;

}
