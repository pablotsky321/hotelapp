package com.service.microservice_user.services;

import com.service.microservice_user.DTOs.UserDTO;
import com.service.microservice_user.entities.UserEntity;
import com.service.microservice_user.repositories.UserRepository;
import jakarta.ws.rs.NotSupportedException;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        }else{
            return userRepository.findAll();
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public UserDTO getUserById(int id) {
        throw new NotImplementedException("method is not implemented yet");
    }

    /**
     * @param email
     * @return
     */
    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
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
     * @return
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
    public int deleteUser(int id) {
        return 0;
    }
}
