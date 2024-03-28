package com.figueiredoisaac.schoolmanager.repository.jpa;

import com.figueiredoisaac.schoolmanager.commons.CourseStatus;
import com.figueiredoisaac.schoolmanager.repository.entities.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    Optional<CourseEntity> findByCode(String code);

    Page<CourseEntity>  findAllByStatus(CourseStatus status, Pageable page);

    @Override
    Page<CourseEntity>  findAll(Pageable page);

}
