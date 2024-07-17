package com.service.microservice_user.repositories;

import com.service.microservice_user.entities.TypeDocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeDocRepository extends JpaRepository<TypeDocEntity,Long> {

    Optional<TypeDocEntity> findByTypeDoc(String typeDoc);


}
