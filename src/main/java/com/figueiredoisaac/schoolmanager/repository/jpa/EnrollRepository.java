package com.figueiredoisaac.schoolmanager.repository.jpa;

import com.figueiredoisaac.schoolmanager.repository.entities.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollEntity, Long> {
}
