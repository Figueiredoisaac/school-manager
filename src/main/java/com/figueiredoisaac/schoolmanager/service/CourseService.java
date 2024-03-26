package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.record.CourseRecord;
import com.figueiredoisaac.schoolmanager.domain.record.input.CourseRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.output.CourseRecordOutput;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.CourseRepository;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class CourseService {

    private Logger logger = Logger.getLogger(CourseService.class.getName());

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public void create(CourseRecordInput courseRecordInput) {
        UserEntity instructor = userRepository.findByUsername(courseRecordInput.instructor())
                .orElseThrow(() -> new NotFoundException("Instrutor não Encontrado"));
        if (instructor.getRole() != UserRoles.INSTRUTOR) {
            throw new BadRequestException("Usuário informado não é Instrutor");
        }
        try {
            CourseEntity course = modelMapper.map(courseRecordInput, CourseEntity.class);
            course.setInstructor(instructor);
            courseRepository.save(course);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    public CourseRecordOutput findByCode(String Code) {
        return modelMapper.map(courseRepository.findByCode(Code)
                .orElseThrow(() -> new NotFoundException("Curso não Encontrado")), CourseRecordOutput.class);
    }

    public Page<CourseRecordOutput> findAllByStatus(CourseStatus status, Pageable page) {
        return modelMapper.map(courseRepository.findAllByStatus(status, page), Page.class);
    }

    public Page<CourseRecordOutput> findAll(Pageable page) {
        return modelMapper.map(courseRepository.findAll(page), Page.class);
    }

    public CourseStatus changeStatus(String code, Boolean active) {
        CourseEntity course = courseRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Curso Não Encontrado"));
        if (active) {
            if (course.getStatus() == CourseStatus.ATIVO) {
                throw new BadRequestException("Curso já encontra-se Ativo");
            }
            course.setStatus(CourseStatus.ATIVO);
            course.setDisabledAt(null);
            return courseRepository.save(course).getStatus();
        } else {
            if (course.getStatus() == CourseStatus.INATIVO) {
                throw new BadRequestException("Curso já encontra-se Inativo");
            }
            course.setStatus(CourseStatus.INATIVO);
            course.setDisabledAt(LocalDateTime.now());
            return courseRepository.save(course).getStatus();
        }
    }
}
