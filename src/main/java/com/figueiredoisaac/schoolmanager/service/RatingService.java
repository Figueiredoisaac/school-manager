package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.EnrollEntity;
import com.figueiredoisaac.schoolmanager.domain.RatingEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.record.input.EnrollRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.input.RatingRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.output.EnrollRecordOutput;
import com.figueiredoisaac.schoolmanager.domain.record.output.RatingRecordOutput;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.CourseRepository;
import com.figueiredoisaac.schoolmanager.repository.EnrollRepository;
import com.figueiredoisaac.schoolmanager.repository.RatingRepository;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import com.figueiredoisaac.schoolmanager.service.sender.EmailSender;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class RatingService {


    private Logger logger = Logger.getLogger(RatingService.class.getName());
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ModelMapper modelMapper;

    public void rating(RatingRecordInput ratingRecordInput) {

        UserEntity user = userRepository.findByUsername(ratingRecordInput.user())
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado"));
        CourseEntity course = courseRepository.findByCode(ratingRecordInput.course())
                .orElseThrow(() -> new NotFoundException("Curso não Encontrado"));
        UserEntity instructor = userRepository.findByUsername(course.getInstructor().getUsername())
                .orElseThrow(() -> new NotFoundException("Instrutor não Encontrado"));
        RatingEntity rating = modelMapper.map(ratingRecordInput, RatingEntity.class);

        try {
            rating.setUser(user);
            rating.setCourse(course);
            ratingRepository.save(rating);
            EmailSender.send(instructor.getEmail(),"Avaliação do Curso: "+course.getName(), ("""
                Nota: %s
                Descrição: %s
                """).formatted(rating.getRating(), rating.getDescription()));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }


}
