package com.figueiredoisaac.schoolmanager.repository;

import com.figueiredoisaac.schoolmanager.domain.RatingEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
}
