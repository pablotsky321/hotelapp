package com.service.microservice_user.repositories;

import com.service.microservice_user.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNumDoc(String numDoc);

    @Modifying
    @Transactional
    @Query(value = "delete from UserEntity u where u.numDoc=:numDoc")
    void deleteByNumDoc(@Param("numDoc") String numDoc);

}
