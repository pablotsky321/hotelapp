package com.service.microservice_room.repositories;

import com.service.microservice_room.entities.RoomEntity;
import com.service.microservice_room.entities.StateEntity;
import com.service.microservice_room.entities.TypeRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {

    List<RoomEntity> findByRoomType(TypeRoomEntity roomType);

    List<RoomEntity> findByState(StateEntity state);

    Optional<RoomEntity> findByRoomNumber(int roomNumber);

}
