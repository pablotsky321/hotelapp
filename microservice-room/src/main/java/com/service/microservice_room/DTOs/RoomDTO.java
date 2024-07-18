package com.service.microservice_room.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoomDTO {

    private long id;
    private short capacity;
    private int roomNumber;
    private int floor;
    private String description;
    private String roomType;
    private String state;

}
