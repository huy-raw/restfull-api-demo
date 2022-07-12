package com.huyraw.demo.controller;

import com.huyraw.demo.entity.User;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.UserMapper;
import com.huyraw.demo.repository.UserRepository;
import com.huyraw.demo.service.UserService;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    private UserController userController;
    private MockMvc mockMvc;

    private final String id = UUID.randomUUID().toString();
    private final LocalDate dob = LocalDate.of(2001,9,19);

    @BeforeEach

    void setUp() {
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    @Disabled
    void tearDown() {
    }

    @Test
    @Disabled
    void createUser_getUserByEmail_whenUserExist() throws  Exception {
        String fullName = "Trieu Huy";
        String password = "passwordverystrong";
        String email = "huyraw@gmail.com";

        UserDTO expectedUser = UserDTO.builder()
                .id(id)
                .email(email)
                .name(fullName)
                .password(password)
                .dob(dob)
                .status(UserStatus.ACTIVE)
                .role(UserRole.USER)
                .build();

        Mockito.when(userService.findUserByEmail(email)).thenReturn(expectedUser);

        String url = "/api/user/" + email;
        mockMvc
                .perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }


    @Test
    void findUser_giveEmail_whenUserNotExist() throws Exception {
        String id = UUID.randomUUID().toString();
        String url = "/api/user/" + id;
        mockMvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(status().isOk());
        Mockito.verify(userService).deleteUserById(id);
    }
}