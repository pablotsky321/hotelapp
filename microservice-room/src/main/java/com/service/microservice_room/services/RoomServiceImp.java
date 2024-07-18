package com.service.microservice_room.services;

import com.service.microservice_room.DTOs.RoomDTO;
import com.service.microservice_room.entities.RoomEntity;
import com.service.microservice_room.entities.StateEntity;
import com.service.microservice_room.entities.TypeRoomEntity;
import com.service.microservice_room.exceptions.DataAlreadyExistException;
import com.service.microservice_room.exceptions.FieldEmptyException;
import com.service.microservice_room.repositories.RoomRepository;
import com.service.microservice_room.repositories.StateRepository;
import com.service.microservice_room.repositories.TypeRoomRepository;
import com.service.microservice_room.requests.CreateRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImp implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TypeRoomRepository typeRoomRepository;

    @Autowired
    private StateRepository stateRepository;

    /**
     * @return List : RoomDTO
     */
    @Override
    public List<RoomDTO> getAllRooms() {
        if(roomRepository.findAll().isEmpty()){
            return new ArrayList<RoomDTO>();
        }
        return buildList(roomRepository.findAll());
    }

    private List<RoomDTO> buildList(List<RoomEntity> roomEntities){
        List<RoomDTO> roomDTOS = new ArrayList<RoomDTO>();
        for(RoomEntity room : roomEntities){
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setId(room.getId());
            roomDTO.setRoomNumber(room.getRoomNumber());
            roomDTO.setFloor(room.getFloor());
            roomDTO.setCapacity(room.getCapacity());
            roomDTO.setRoomType(room.getRoomType().getRoomType());
            roomDTO.setState(room.getState().getState());
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    private RoomDTO buildRoomDTO(RoomEntity room){
        RoomDTO roomReturn = new RoomDTO();
        roomReturn.setId(room.getId());
        roomReturn.setRoomNumber(room.getRoomNumber());
        roomReturn.setFloor(room.getFloor());
        roomReturn.setCapacity(room.getCapacity());
        roomReturn.setRoomType(room.getRoomType().getRoomType());
        roomReturn.setState(room.getState().getState());
        return roomReturn;
    }

    /**
     * @param idState long
     * @return List : RoomDTO
     * @throws ClassNotFoundException if state not found
     */
    @Override
    public List<RoomDTO> getRoomsByState(long idState) throws ClassNotFoundException {
        Optional<StateEntity> stateFind = stateRepository.findById(idState);
        if(stateFind.isEmpty()){
            throw new ClassNotFoundException("state not found");
        }
        if(roomRepository.findByState(stateFind.get()).isEmpty()){
            return new ArrayList<RoomDTO>();
        }
        return buildList(roomRepository.findByState(stateFind.get()));
    }

    /**
     * @param roomTypeId  long
     * @return List : RoomDTO
     * @throws ClassNotFoundException if room type not found
     */
    @Override
    public List<RoomDTO> getRoomsByRoomType(long roomTypeId) throws ClassNotFoundException {
        Optional<TypeRoomEntity> typeRoomFind = typeRoomRepository.findById(roomTypeId);
        if(typeRoomFind.isEmpty()){
            throw new ClassNotFoundException("room type not found");
        }
        if(roomRepository.findByRoomType(typeRoomFind.get()).isEmpty()){
            return new ArrayList<RoomDTO>();
        }
        return buildList(roomRepository.findByRoomType(typeRoomFind.get()));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public RoomDTO getRoomById(long id) throws ClassNotFoundException {
        if(roomRepository.findById(id).isEmpty()){
            throw new ClassNotFoundException("room not found");
        }
        return buildRoomDTO(roomRepository.findById(id).get());
    }

    /**
     * @param number long
     * @return RoomDTO
     */
    @Override
    public RoomDTO getRoomByRoomNumber(int number) throws ClassNotFoundException {
        if(roomRepository.findByRoomNumber(number).isEmpty()){
            throw new ClassNotFoundException("room not found");
        }
            return buildRoomDTO(roomRepository.findByRoomNumber(number).get());
    }

    /**
     * @param room RoomDTO
     * @return int
     * @throws FieldEmptyException if information is missing
     * @throws DataAlreadyExistException if room already exists
     */
    @Override
    public int createRoom(CreateRoomRequest room) throws FieldEmptyException, ClassNotFoundException, DataAlreadyExistException {
        if((Integer)room.getRoomNumber() == null || (Integer)room.getFloor() == null || (Short)room.getCapacity() == null || room.getDescription() == null ){
            throw new FieldEmptyException("All fields must be filled");
        }
        if(room.getDescription().isEmpty()){
            throw new FieldEmptyException("All fields must be filled");
        }
        Optional<TypeRoomEntity> typeRoomFind = typeRoomRepository.findById(room.getRoomType());
        Optional<StateEntity> stateFind = stateRepository.findById(room.getState());
        if(typeRoomFind.isEmpty() || stateFind.isEmpty()){
            throw new ClassNotFoundException("State or room type not found");
        }
        if(roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()){
            throw new DataAlreadyExistException("room already exists");
        }

        int floor;
        String subFloor = String.valueOf(String.valueOf(room.getFloor()).charAt(0));
        floor = Integer.parseInt(subFloor);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(room.getRoomNumber());
        roomEntity.setFloor(floor);
        roomEntity.setCapacity(room.getCapacity());
        roomEntity.setDescription(room.getDescription());
        roomEntity.setRoomType(typeRoomFind.get());
        roomEntity.setState(stateFind.get());
        roomRepository.save(roomEntity);
        return 1;

    }

    /**
     * @param room
     * @return
     */
    @Override
    public int updateRoom(RoomDTO room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteRoom(long id) throws ClassNotFoundException {
        if(roomRepository.findById(id).isEmpty()){
            throw new ClassNotFoundException("room not found");
        }
        roomRepository.deleteById(id);
        return 1;
    }
}
