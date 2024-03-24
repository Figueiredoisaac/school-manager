package com.figueiredoisaac.schoolmanager.domain.enroll.usecase;

import com.figueiredoisaac.schoolmanager.domain.enroll.model.Enroll;
import com.figueiredoisaac.schoolmanager.repository.entities.EnrollEntity;
import com.figueiredoisaac.schoolmanager.repository.jpa.EnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
@Service
public class EnrollUseCase {


    private Logger logger = Logger.getLogger(EnrollUseCase.class.getName());

    @Autowired
    EnrollRepository repository;

    public Enroll enrollment(Enroll enroll){
        return repository.save(EnrollEntity.from(enroll)).toModel();
    }
}
