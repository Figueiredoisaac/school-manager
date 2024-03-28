package com.figueiredoisaac.schoolmanager.repository;

import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_Save_ReturnSavedUser() {

        //Arrange
        UserEntity userEntity = Mockito.mock(UserEntity.class);

        //Act
        UserEntity savedUserEntity = userRepository.save(userEntity);

        //Assert
        Assertions.assertThat(savedUserEntity).isNotNull();
        Assertions.assertThat(savedUserEntity.getId()).isGreaterThan(0);
    }

    @Test
    void UserRepository_findByUsername_ReturnUserEntity() {

        UserEntity userEntity = Mockito.mock(UserEntity.class);

        userRepository.save(userEntity);

        UserEntity userEntityList = userRepository.findByUsername(userEntity.getUsername()).get();

        Assertions.assertThat(userEntityList).isNotNull();
    }


}