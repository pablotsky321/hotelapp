package com.service.microservice_user.services;

import com.service.microservice_user.DTOs.UserDTO;
import com.service.microservice_user.entities.TypeDocEntity;
import com.service.microservice_user.entities.UserEntity;
import com.service.microservice_user.requests.RegisterRequest;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email) throws ClassNotFoundException;
    UserDTO getUserByNumDoc(String numDoc) throws ClassNotFoundException;
    int createUser(RegisterRequest user, long typeDocId) throws Exception;
    int updateUser(UserDTO user);
    int deleteUser(String numDoc) throws ClassNotFoundException;

}
