package com.service.microservice_room.repositories;

import com.service.microservice_room.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<StateEntity,Long> {


}
