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
            roomDTOS.add(buildRoomDTO(room));
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
        roomReturn.setDescription(room.getDescription());
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
     * @param roomTypeId long
     * @param idState long
     * @return List : RoomDTO
     * @throws ClassNotFoundException if state or room type not found
     */
    @Override
    public List<RoomDTO> getRoomsByTypeAndState(long roomTypeId, long idState) throws ClassNotFoundException {
        Optional<StateEntity> state = stateRepository.findById(idState);
        Optional<TypeRoomEntity> typeRoom = typeRoomRepository.findById(roomTypeId);
        if(state.isEmpty() || typeRoom.isEmpty()){
           throw new ClassNotFoundException("state or room type not found");
        }
        return buildList(roomRepository.findByStateAndRoomType(state.get(),typeRoom.get()));
    }

    /**
     * @param id long
     * @return RoomDTO
     * @throws ClassNotFoundException if room not found
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
     * @throws ClassNotFoundException if room not found
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
        if(room.getRoomNumber() <= 0 || room.getCapacity() <= 0 || room.getDescription() == null ){
            throw new FieldEmptyException("all fields must be filled");
        }
        if(room.getDescription().isEmpty()){
            throw new FieldEmptyException("all fields must be filled");
        }
        Optional<TypeRoomEntity> typeRoomFind = typeRoomRepository.findById(room.getRoomType());
        Optional<StateEntity> stateFind = stateRepository.findById(room.getState());
        if(typeRoomFind.isEmpty() || stateFind.isEmpty()){
            throw new ClassNotFoundException("state or room type not found");
        }
        if(roomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()){
            throw new DataAlreadyExistException("room already exists");
        }

        int floor;
        String subFloor = String.valueOf(String.valueOf(room.getRoomNumber()).charAt(0));
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
     * @param roomNumber
     * @param idState
     * @return
     */
    @Override
    public int changeState(int roomNumber, long idState) {
        return 0;
    }

    /**
     * @param id long
     * @return int
     * @throws ClassNotFoundException if room not found
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
