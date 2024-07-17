package com.service.microservice_user.services;

import com.service.microservice_user.DTOs.UserDTO;
import com.service.microservice_user.entities.TypeDocEntity;
import com.service.microservice_user.entities.UserEntity;
import com.service.microservice_user.exceptions.DataAlreadyExistException;
import com.service.microservice_user.exceptions.FieldEmptyException;
import com.service.microservice_user.repositories.TypeDocRepository;
import com.service.microservice_user.repositories.UserRepository;
import com.service.microservice_user.requests.RegisterRequest;
import jakarta.ws.rs.NotFoundException;
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
    @Autowired
    private TypeDocRepository typeDocRepository;

    /**
     * @return List : UserEntity
     */
    @Override
    public List<UserDTO> getAllUsers() {
        if(userRepository.findAll().isEmpty()){
            return new ArrayList<UserDTO>();
        }
            List<UserDTO> usersList = new ArrayList<>();
            for(UserEntity user:userRepository.findAll()){
                usersList.add(buildUserDTO(user));
            }
            return usersList;
    }

    /**
     * @param email String
     * @return UserDTO
     * @throws ClassNotFoundException if user not found
     */
    @Override
    public UserDTO getUserByEmail(String email) throws ClassNotFoundException {
        Optional<UserEntity> user = findUserByEmail(email);
        if(user.isEmpty()){
            throw new ClassNotFoundException("User not found");
        }
        return buildUserDTO(user.get());
    }

    /**
     * @param numDoc String
     * @return UserDTO
     * @throws ClassNotFoundException if user not found
     */
    @Override
    public UserDTO getUserByNumDoc(String numDoc) throws ClassNotFoundException {
        Optional<UserEntity> user = findUserByNumDoc(numDoc);
        if(numDoc.isEmpty() || user.isEmpty()){
            throw new ClassNotFoundException("User not found");
        }
        return buildUserDTO(user.get());
    }

    /**
     * @param user UserDTO
     * @param typeDocId long
     * @return int
     * @throws FieldEmptyException if information is missing
     * @throws DataAlreadyExistException if user already exist
     */
    @Override
    public int createUser(RegisterRequest user, long typeDocId) throws DataAlreadyExistException,FieldEmptyException {
        if(user == null){
            throw new NullPointerException("User is null");
        }
        if(findUserByEmail(user.getEmail()).isPresent() || findUserByNumDoc(user.getNumDoc()).isPresent()){
            throw new DataAlreadyExistException("User already exists");
        }
        if(user.getEmail() == null || user.getNumDoc() == null || user.getFirstName() == null || user.getLastName() == null){
            throw new FieldEmptyException("All fields must be filled");
        }
        if(user.getEmail().isEmpty() || user.getNumDoc().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty()){
            throw new FieldEmptyException("All fields must be filled");
        }
        Optional<TypeDocEntity> typeDocFind = typeDocRepository.findById(typeDocId);

        if(typeDocFind.isEmpty()){
            throw new FieldEmptyException("type of document must be selected");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setNumDoc(user.getNumDoc());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setTypeDoc(typeDocFind.get());
        userRepository.save(userEntity);
        return 1;

    }

    private Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Optional<UserEntity> findUserByNumDoc(String numDoc) {
        return userRepository.findByNumDoc(numDoc);
    }

    private UserDTO buildUserDTO(UserEntity user) {
        return UserDTO
                .builder()
                .numDoc(user.getNumDoc())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .typeDoc(user.getTypeDoc().getTypeDoc())
                .build();
    }

    /**
     * @param user UserDTO
     * @return int
     */
    @Override
    public int updateUser(UserDTO user) {
        throw new NotImplementedException("method is not implemented");
    }

    /**
     * @param numDoc String
     * @return int
     */
    @Override
    public int deleteUser(String numDoc) throws ClassNotFoundException {
        if(userRepository.findByNumDoc(numDoc).isEmpty()){
            throw new ClassNotFoundException("User not found");
        }
        userRepository.deleteByNumDoc(numDoc);
        return 1;
    }
}
