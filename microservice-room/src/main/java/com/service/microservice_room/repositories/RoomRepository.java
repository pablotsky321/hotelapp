package com.service.microservice_room.repositories;

import com.service.microservice_room.entities.RoomEntity;
import com.service.microservice_room.entities.StateEntity;
import com.service.microservice_room.entities.TypeRoomEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {

    List<RoomEntity> findByRoomType(TypeRoomEntity roomType);

    List<RoomEntity> findByState(StateEntity state);

    List<RoomEntity> findByStateAndRoomType(StateEntity state,TypeRoomEntity typeRoom);

    Optional<RoomEntity> findByRoomNumber(int roomNumber);

    @Transactional
    @Modifying
    void deleteById(long id);


}
