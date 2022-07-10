package com.huyraw.demo.repository;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private  UserRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findUserByEmailTest_giveEmail_whenUserExist() {
        String email = "huyraw@gmail.com";
        //given
        User user = User.builder().
                fullName("Lu Trong Thang")
                .email(email)
                .dob(LocalDate.of(2001,9,19))
                .password("12345678")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();
        underTest.save(user);
        //when
       User expect = underTest.findUserByEmail(email).get();
        //then
        assertThat(expect).usingRecursiveComparison().isEqualTo(user);
    }


    @Test
    void isExistUserByEmailTest_giveEmail_whenUserExist() {
        String email = "huyraw@gmail.com";
        //given
        User user = User.builder().
                fullName("Lu Trong Thang")
                .id(UUID.randomUUID().toString())
                .email(email)
                .dob(LocalDate.of(2001,9,19))
                .password("123456789")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();
        underTest.save(user);
        //when
        boolean exist = underTest.isExistUserByEmail(email);
        //then
        assertThat(exist).isTrue();
    }
}