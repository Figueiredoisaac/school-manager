package com.figueiredoisaac.schoolmanager.domain.course.usecase;

import com.figueiredoisaac.schoolmanager.commons.CourseStatus;
import com.figueiredoisaac.schoolmanager.commons.exceptions.BadRequestException;
import com.figueiredoisaac.schoolmanager.commons.exceptions.ResourceNotFoundException;
import com.figueiredoisaac.schoolmanager.domain.course.model.Course;
import com.figueiredoisaac.schoolmanager.repository.entities.CourseEntity;
import com.figueiredoisaac.schoolmanager.repository.jpa.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
@Service
public class CourseUseCase {

    private Logger logger = Logger.getLogger(CourseUseCase.class.getName());

    @Autowired
    CourseRepository repository;

    public Course create(Course course){
        repository.save(CourseEntity.from(course));
        return repository.findByCode(course.getCode()).map(CourseEntity::toModel).orElseThrow(() -> new ResourceNotFoundException("Erro ao cadastrar Curso"));
    }

    public Course findByCode(String Code){
       return repository.findByCode(Code).orElseThrow(() -> new ResourceNotFoundException("Curso não Encontrado")).toModel();
    }

    public Page<Course> findAllByStatus(CourseStatus status, Pageable page) {
        return repository.findAllByStatus(status, page).map(CourseEntity::toModel);
    }

    public Page<Course> findAll(Pageable page){
        return repository.findAll(page).map(CourseEntity::toModel);
    }

    public Course changeStatus(String code, Boolean active) {
        Course course = repository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Curso Não Encontrado")).toModel();
        if (active) {
            if (course.getStatus() == CourseStatus.ATIVO ) { throw new BadRequestException("Curso já encontra-se Ativo");}
            course.setStatus(CourseStatus.ATIVO);
            course.setDisabledAt(null);
            return repository.save(CourseEntity.from(course)).toModel();
        } else {
            if (course.getStatus() == CourseStatus.INATIVO ) { throw new BadRequestException("Curso já encontra-se Inativo");}
            course.setStatus(CourseStatus.INATIVO);
            course.setDisabledAt(LocalDateTime.now());
            return repository.save(CourseEntity.from(course)).toModel();
        }
    }
}
