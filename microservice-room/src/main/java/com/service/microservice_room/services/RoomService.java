package com.service.microservice_room.services;

import com.service.microservice_room.DTOs.RoomDTO;
import com.service.microservice_room.exceptions.DataAlreadyExistException;
import com.service.microservice_room.exceptions.FieldEmptyException;
import com.service.microservice_room.requests.CreateRoomRequest;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRooms();
    List<RoomDTO> getRoomsByState(long idState) throws ClassNotFoundException;
    List<RoomDTO> getRoomsByRoomType(long roomTypeId) throws ClassNotFoundException;
    List<RoomDTO> getRoomsByTypeAndState(long roomTypeId,long idState) throws ClassNotFoundException;
    RoomDTO getRoomById(long id) throws ClassNotFoundException;
    RoomDTO getRoomByRoomNumber(int number) throws ClassNotFoundException;
    int createRoom(CreateRoomRequest room) throws FieldEmptyException, ClassNotFoundException, DataAlreadyExistException;
    int updateRoom(RoomDTO room);
    int changeState(int roomNumber,long idState);
    int deleteRoom(long id) throws ClassNotFoundException;


}
