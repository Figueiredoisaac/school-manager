package com.figueiredoisaac.schoolmanager.domain.user.usecase;

import com.figueiredoisaac.schoolmanager.commons.exceptions.ResourceNotFoundException;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import com.figueiredoisaac.schoolmanager.repository.entities.UserEntity;
import com.figueiredoisaac.schoolmanager.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
@Service
public class UserUseCase {

    private Logger logger = Logger.getLogger(UserUseCase.class.getName());

    @Autowired
    UserRepository repository;

    public User findByUsername(String username) throws Exception {
       return repository.findByUsername(username).map(UserEntity::toModel).orElseThrow(() -> new ResourceNotFoundException("Usuário não Encontrado"));
    }

    public User create(User user){
       if (repository.findByUsername(user.getUsername()).isEmpty()) {
           return repository.save(UserEntity.from(user)).toModel();
       }
        throw new ResourceNotFoundException("Erro ao cadastrar usuário");
    }


}
