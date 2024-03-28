package com.figueiredoisaac.schoolmanager.repository.jpa;

import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import com.figueiredoisaac.schoolmanager.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByName(String name);

}
