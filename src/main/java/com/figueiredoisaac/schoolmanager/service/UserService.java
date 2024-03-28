package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.dto.UserDto;
import com.figueiredoisaac.schoolmanager.domain.dto.output.UserDtoOutput;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository repository;
    @Autowired
    ModelMapper modelMapper;

    public UserDtoOutput findByUsername(String username) {
        return modelMapper.map(repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado")), UserDtoOutput.class);

    }

    public void create(UserDto userDTO) {
        if (repository.findByUsername(userDTO.getUsername()).isEmpty()) {
            UserEntity user = modelMapper.map(userDTO, UserEntity.class);
            repository.save(user);
        } else {
            throw new BadRequestException("Usuário já possui um cadastro");
        }
    }


}
