package com.figueiredoisaac.schoolmanager.service;

import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;
import com.figueiredoisaac.schoolmanager.domain.record.input.UserRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.output.UserRecordOutput;
import com.figueiredoisaac.schoolmanager.exception.BadRequestException;
import com.figueiredoisaac.schoolmanager.exception.NotFoundException;
import com.figueiredoisaac.schoolmanager.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class UserService {

    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository repository;
    @Autowired
    ModelMapper modelMapper;

    public UserRecordOutput findByUsername(String username) {
        return modelMapper.map(repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Usuário não Encontrado")), UserRecordOutput.class);

    }

    public void create(UserRecordInput userRecordInput) {
        if (repository.findByUsername(userRecordInput.username()).isEmpty()) {
            System.out.println(userRecordInput);
           UserEntity user = modelMapper.map(userRecordInput, UserEntity.class);
           user.setCreatedAt(LocalDateTime.now());
            System.out.println(repository.save(user));
            //repository.save(user);
        } else {
            throw new BadRequestException("Usuário já possui um cadastro");
        }
    }


}
