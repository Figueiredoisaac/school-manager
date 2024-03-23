package com.figueiredoisaac.schoolmanager.repository.jpa;

import com.figueiredoisaac.schoolmanager.repository.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

}
