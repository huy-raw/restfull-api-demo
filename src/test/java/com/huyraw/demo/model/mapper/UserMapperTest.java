package com.huyraw.demo.model.mapper;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.model.mapper.request.UpdateUserRequest;
import com.huyraw.demo.util.constants.UserRole;
import com.huyraw.demo.util.constants.UserStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperTest {
    UserMapper userMapper = UserMapper.INSTANCE;


    @Test
    void giveUserMapToUserDTO_whenMapper_thenCorrect() {

        User user = User.builder()
                .fullName("Nguyen Van A")
                .email("huyraw@mgmail.com")
                .password("verystrongpassword")
                .dob(LocalDate.of(2001,9,19))
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE).build();

        UserDTO expected = UserDTO.builder()
                .name("Nguyen Van A")
                .email("huyraw@mgmail.com")
                .dob("19/09/2001")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE).build();

        UserDTO actualUser = userMapper.toUserDTO(user);
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void giveUserDTOMapToUser_whenMapper_thenCorrect() {

        User expected = User.builder()
                .fullName("Nguyen Van A")
                .email("huyraw@mgmail.com")
                .password("verystrongpassword")
                .dob(LocalDate.of(2001, 9, 19))
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE).build();

        UserDTO userDTO = UserDTO.builder()
                .name("Nguyen Van A")
                .email("huyraw@mgmail.com")
                .password("verystrongpassword")
                .dob("19/09/2001")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE).build();

        User actualUser = userMapper.toUser(userDTO);
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void giveCreateRequestMapToUser_whenMapper_thenCorrect() {
        CreateUserRequest data = CreateUserRequest.builder()
                .name("Nguyen Duc Chung")
                .email("test@gmail.com")
                .password("verystrongpassword")
                .dob("19/09/2001")
                .build();

        User expected = User.builder()
                .fullName("Nguyen Duc Chung")
                .email("test@gmail.com")
                .password("verystrongpassword")
                .dob(LocalDate.of(2001, 9, 19))
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();

        User actual = userMapper.createUser(data);
        assertThat(actual).usingRecursiveComparison().ignoringFields(actual.getId()).isEqualTo(expected);
    }

    @Test
    void giveUpdateRequestMapToUser_whenMapper_thenCorrect(){

        User user = User.builder()
                .fullName("Nguyen Duc Chung")
                .email("test@gmail.com")
                .password("verystrongpassword")
                .dob(LocalDate.of(2001, 9, 19))
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();


        User expected = User.builder()
                .fullName("Nguyen Duc Chung")
                .email("huyraw@gmail.com")
                .password("verystrongpassword")
                .dob(LocalDate.of(2001, 9, 19))
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();

        UpdateUserRequest data = UpdateUserRequest.builder()
                .name("Nguyen Duc Chung")
                .dob("19/09/2001")
                .email("huyraw@gmail.com")
                .build();

        User actual = userMapper.updateUser(data, user);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}