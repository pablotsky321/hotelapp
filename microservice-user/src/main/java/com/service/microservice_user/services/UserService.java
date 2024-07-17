package com.service.microservice_user.services;

import com.service.microservice_user.DTOs.UserDTO;
import com.service.microservice_user.entities.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();
    UserDTO getUserById(long id);
    UserDTO getUserByEmail(String email);
    int createUser(UserDTO user);
    int updateUser(UserDTO user);
    int deleteUser(long id);

}
