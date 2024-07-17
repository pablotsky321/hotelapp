package com.service.microservice_user.controllers;

import com.service.microservice_user.DTOs.UserDTO;
import com.service.microservice_user.entities.UserEntity;
import com.service.microservice_user.exceptions.DataAlreadyExistException;
import com.service.microservice_user.exceptions.FieldEmptyException;
import com.service.microservice_user.requests.RegisterRequest;
import com.service.microservice_user.services.UserService;
import jakarta.ws.rs.NotFoundException;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable(value = "email") String email) {
        try {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.ACCEPTED);
        }catch (ClassNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userByNumDoc")
    public ResponseEntity<?> findByNumDoc(@RequestParam(value = "numDoc") String numDoc){
            try {
                return new ResponseEntity<>(userService.getUserByNumDoc(numDoc), HttpStatus.ACCEPTED);
            }catch (ClassNotFoundException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest user) {
        try {
            return new ResponseEntity<>(userService.createUser(user,user.getTypeDoc()), HttpStatus.CREATED);
        }catch (DataAlreadyExistException | FieldEmptyException | NullPointerException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.ACCEPTED);
        }catch (NotImplementedException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "numDoc") String numDoc) {
        try {
            return new ResponseEntity<>(userService.deleteUser(numDoc), HttpStatus.ACCEPTED);
        } catch (ClassNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


}
