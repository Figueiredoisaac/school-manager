package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.RatingEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.dto.RatingDto;
import com.figueiredoisaac.schoolmanager.domain.dto.RatingDtoNpc;
import com.figueiredoisaac.schoolmanager.domain.dto.output.CourseDTtoOutput;
import com.figueiredoisaac.schoolmanager.domain.dto.output.RatingDtoNpcOutput;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.CourseRepository;
import com.figueiredoisaac.schoolmanager.repository.RatingRepository;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import com.figueiredoisaac.schoolmanager.service.sender.EmailSender;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void rating(RatingDto ratingDTO) {

        UserEntity user = userRepository.findByUsername(ratingDTO.getUser())
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado"));
        CourseEntity course = courseRepository.findByCode(ratingDTO.getCourse())
                .orElseThrow(() -> new NotFoundException("Curso não Encontrado"));
        UserEntity instructor = userRepository.findByUsername(course.getInstructor().getUsername())
                .orElseThrow(() -> new NotFoundException("Instrutor não Encontrado"));
        RatingEntity rating = modelMapper.map(ratingDTO, RatingEntity.class);

        try {
            rating.setUser(user);
            rating.setCourse(course);
            ratingRepository.save(rating);
            if (rating.getRating() < 7) {
                EmailSender.send(
                        instructor.getEmail(),
                        "Avaliação do Curso: " + course.getName() + " requer atenção!",
                        ("""
                                Nota: %s
                                Descrição: %s
                                """).formatted(rating.getRating(), rating.getDescription()
                        )
                );
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<RatingDtoNpc> npcReport() {

        List<RatingDtoNpc> list = ratingRepository.calculateNpsByCourse(Sort.by(Sort.Direction.DESC, "nps"));

        return list;
    }


}
