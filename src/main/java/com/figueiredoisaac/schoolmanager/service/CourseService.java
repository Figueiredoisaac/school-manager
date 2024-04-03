package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.dto.CourseDto;
import com.figueiredoisaac.schoolmanager.domain.dto.output.CourseDTtoOutput;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.CourseRepository;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public void create(CourseDto courseDTO) {
        UserEntity instructor = userRepository.findByUsername(courseDTO.getInstructor())
                .orElseThrow(() -> new NotFoundException("Instrutor não Encontrado"));
        if (instructor.getRole() != UserRoles.INSTRUTOR) {
            throw new BadRequestException("Usuário informado não é Instrutor");
        }
        try {
            CourseEntity course = modelMapper.map(courseDTO, CourseEntity.class);
            course.setInstructor(instructor);
            courseRepository.save(course);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    public CourseDTtoOutput findByCode(String Code) {
        return modelMapper.map(courseRepository.findByCode(Code)
                .orElseThrow(() -> new NotFoundException("Curso não Encontrado")), CourseDTtoOutput.class);
    }

    public Page<CourseDTtoOutput> findAllByStatus(CourseStatus status, Integer page) {
        Pageable response = createPageRequestUsing(page,"id");

        Page<CourseEntity> list = courseRepository.findAllByStatus(status, response);
        List<CourseDTtoOutput> content = list
                .stream()
                .map(courseEntity -> modelMapper.map(courseEntity, CourseDTtoOutput.class))
                .toList();

        return new PageImpl<>(content, response, list.getTotalElements());
    }

    public Page<CourseDTtoOutput> findAll(Integer page) {
        Pageable response = createPageRequestUsing(page, "id");

        Page<CourseEntity> list = courseRepository.findAll(response);
        List<CourseDTtoOutput> content = list
                .stream().map(courseEntity -> modelMapper.map(courseEntity, CourseDTtoOutput.class))
                .toList();

        return new PageImpl<>(content, response, list.getTotalElements());
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

    private Pageable createPageRequestUsing(Integer page, String proprieties) {
        return PageRequest.of((page-1), 30, Sort.Direction.DESC, proprieties);
    }
}
