package com.service.microservice_room.controllers;

import com.service.microservice_room.exceptions.DataAlreadyExistException;
import com.service.microservice_room.exceptions.FieldEmptyException;
import com.service.microservice_room.requests.CreateRoomRequest;
import com.service.microservice_room.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/allRooms")
    public ResponseEntity<?> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/roomsByState/{idState}")
    public ResponseEntity<?> getRoomsByState(@PathVariable(value = "idState") long idState){
        try {
            return new ResponseEntity<>(roomService.getRoomsByState(idState), HttpStatus.ACCEPTED);
        }catch (ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/roomsByType/{roomTypeId}")
    public ResponseEntity<?> getRoomsType(@PathVariable(value = "roomTypeId") long roomTypeId){
        try {
            return new ResponseEntity<>(roomService.getRoomsByRoomType(roomTypeId),HttpStatus.ACCEPTED);
        }catch (ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/roomsByTypeAndState")
    public ResponseEntity<?> getRoomsByTypeAndState(@RequestParam(value = "roomTypeId") long roomTypeId,@RequestParam(value = "idState") long idState){
        try {
            return new ResponseEntity<>(roomService.getRoomsByTypeAndState(roomTypeId,idState),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/roomById/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable(value = "id") long id){
        try {
            return new ResponseEntity<>(roomService.getRoomById(id),HttpStatus.ACCEPTED);
        }catch (ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/roomByNumber/{number}")
    public ResponseEntity<?> getRoomByNumber(@PathVariable(value = "number") int number){
        try {
            return new ResponseEntity<>(roomService.getRoomByRoomNumber(number),HttpStatus.ACCEPTED);
        }catch (ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addRoom")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequest request){
        try {
            return new ResponseEntity<>(roomService.createRoom(request),HttpStatus.CREATED);
        }catch(DataAlreadyExistException | ClassNotFoundException | FieldEmptyException | NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/changeState")
    public ResponseEntity<?> changeState(@RequestParam(value = "roomNumber") int roomNumber,@RequestParam(value = "idState") long idState){
        try {
            return new ResponseEntity<>(roomService.changeState(roomNumber,idState),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@RequestParam(value = "id") long id){
        try {
            return new ResponseEntity<>(roomService.deleteRoom(id),HttpStatus.ACCEPTED);
        }catch(ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
