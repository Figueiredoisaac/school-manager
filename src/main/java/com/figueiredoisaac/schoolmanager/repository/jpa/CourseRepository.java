package com.figueiredoisaac.schoolmanager.repository.jpa;

import com.figueiredoisaac.schoolmanager.repository.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
