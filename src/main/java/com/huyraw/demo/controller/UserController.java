package com.huyraw.demo.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user")
@RestController
@Getter
@Setter
@AllArgsConstructor
@RequestMapping(path = "api/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new user")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest req) {
       userService.createUser(req);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

}
