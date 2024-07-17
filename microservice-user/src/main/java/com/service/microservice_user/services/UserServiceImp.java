package com.service.microservice_user.services;

import com.service.microservice_user.DTOs.UserDTO;
import com.service.microservice_user.entities.UserEntity;
import com.service.microservice_user.repositories.UserRepository;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    /**
     * @return
     */
    @Override
    public List<UserEntity> getAllUsers() {
        if(userRepository.findAll().isEmpty()){
            return new ArrayList<UserEntity>();
        }
            return userRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UserDTO getUserById(long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        return UserDTO
                .builder()
                .numDoc(user.get().getNumDoc())
                .email(user.get().getEmail())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .build();

    }

    /**
     * @param email
     * @return
     */
    @Override
    public UserDTO getUserByEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        return UserDTO
                .builder()
                .numDoc(user.get().getNumDoc())
                .email(user.get().getEmail())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .build();
    }

    /**
     * @param user
     * @return
     */
    @Override
    public int createUser(UserDTO user) {
        return 0;
    }

    /**
     * @param user
     * @return int
     */
    @Override
    public int updateUser(UserDTO user) {
        return 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteUser(long id) {
        return 0;
    }
}
