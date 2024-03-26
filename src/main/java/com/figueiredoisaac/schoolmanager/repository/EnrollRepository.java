package com.figueiredoisaac.schoolmanager.repository;

import com.figueiredoisaac.schoolmanager.domain.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollEntity, Long> {
}
