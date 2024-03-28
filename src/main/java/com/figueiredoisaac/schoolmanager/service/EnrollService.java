package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.EnrollEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.dto.EnrollDto;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.CourseRepository;
import com.figueiredoisaac.schoolmanager.repository.EnrollRepository;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class EnrollService {


    private Logger logger = Logger.getLogger(EnrollService.class.getName());
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    EnrollRepository enrollRepository;
    @Autowired
    ModelMapper modelMapper;

    public void enrollment(EnrollDto enrollDTO) {
        UserEntity user = userRepository.findByUsername(enrollDTO.getUser())
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado"));
        CourseEntity course = courseRepository.findByCode(enrollDTO.getCourse())
                .orElseThrow(() -> new NotFoundException("Curso não Encontrado"));
        if (course.getStatus() == CourseStatus.INATIVO) {
            throw new BadRequestException("Curso não permite matrículas novas");
        }
        enrollRepository.save(new EnrollEntity(user,course,LocalDateTime.now()));
    }


}
