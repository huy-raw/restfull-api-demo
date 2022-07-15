package com.huyraw.demo.service;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.UserMapper;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.model.mapper.request.UpdateUserRequest;
import com.huyraw.demo.repository.UserRepository;
import com.huyraw.demo.util.constants.UserRole;
import com.huyraw.demo.util.constants.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    private UserService underTest;
    private AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, userMapper);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllUserTest_whenNoUserExit_thenThrowException() {
        List<User> expectedList = List.of();
        Mockito.when(userRepository.findAll()).thenReturn(expectedList);
        assertThatThrownBy(() -> underTest.getAllUser()).hasMessageContaining("Not found any users");

    }

    @Test
    void createUserTest_giveRequest_thenCreateUse() {
        String email = "huyraw@gmail.com";
        //given
        CreateUserRequest request = CreateUserRequest.builder().
                name("Nguyen Van A")
                .email(email)
                .dob("19/09/2001")
                .password("12345678")
                .build();
        //when
        underTest.createUser(request);
        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser.getEmail()).isEqualTo(request.getEmail());
    }


    @Test
    void findUserByEmail_whenUserExist_thenFindUser() {
        String email = "huyraw@gmail.com";
        //given
        User expected = User.builder().
                id(UUID.randomUUID().toString()).
                fullName("Nguyen Van A")
                .email(email)
                .dob(LocalDate.of(2001, 9, 19))
                .password("12345678")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();

        //when
        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(expected));

        UserDTO actualUser = underTest.findUserByEmail(email);
        //expected
        assertThat(actualUser.getEmail()).isEqualTo(expected.getEmail());
    }

    @Test
    void GivenEmail_WhenUserNotExist_ThenThrowException() {
        String email = "huyraw@gmail.com";
        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findUserByEmail(email))
                .hasMessageContaining("User is have email " + email + " doesn't existed");
    }


    @Test
    void givenEmail_TryDelete_WhenUserDoesntExisted_ThenThrowException() {

        String email = "huyraw@gmail.com";

        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.deleteUserById(underTest.findUserByEmail(email).getId()))
                .hasMessageContaining(String.format("User is have email %s doesn't existed", email));
    }

    @Test
    void givenEmail_TryDelete_WhenUserExisted() {
        String email = "huyraw@gmail.com";
        String id = UUID.randomUUID().toString();
        //given
        User expected = User.builder().
                id(id).
                fullName("Nguyen Van A")
                .email(email)
                .dob(LocalDate.of(2001, 9, 19))
                .password("12345678")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();

        //when
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(expected));
        //then
        underTest.deleteUserById(id);
        Mockito.verify(userRepository).deleteById(id);

    }


    @Test
    void updateUser_giveId_whenUserExist() {
        String email = "huyraw@gmail.com";
        String id = UUID.randomUUID().toString();
        //given
        User expected = User.builder()
                .id(id)
                .fullName("Nguyen Van A")
                .email(email)
                .dob(LocalDate.of(2001, 9, 19))
                .password("12345678")
                .role(UserRole.USER)
                .status(UserStatus.ACTIVE)
                .build();

        UpdateUserRequest request =
                UpdateUserRequest.builder().
                        name("Nguyen Van B").
                        dob("19/09/2001").
                        email(email).build();
        //when
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(expected));
//        Mockito.when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(expected));

        //then
        underTest.updateUser(id, request);
        Mockito.verify(userRepository).save(any());
    }

    @Test
    void updateUser_giveId_whenUserNotExist_thenThrowException(){
        String id = UUID.randomUUID().toString();
        String email = "huyraw@gmail.com";
        UpdateUserRequest request =
                UpdateUserRequest.builder().
                        name("Nguyen Van B").
                        dob("19/09/2001").
                        email(email).build();
        //when
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.updateUser(id, request))
                .hasMessageContaining(String.format("User is have id %s doesn't existed", id));
    }
}