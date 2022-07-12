package com.huyraw.demo.controller;


import com.huyraw.demo.model.dto.UserDTO;
import com.huyraw.demo.model.mapper.request.CreateUserRequest;
import com.huyraw.demo.model.mapper.request.UpdateUserRequest;
import com.huyraw.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Email;
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
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody CreateUserRequest req) {
       userService.createUser(req);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Find user by id")
    public ResponseEntity<UserDTO> findUserById(@Validated @PathVariable(value = "id") String id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/email")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Find user by email")
    public ResponseEntity<UserDTO> findUserByEmail(@Validated @Email @RequestParam(value = "email") String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validated
    public ResponseEntity<UserDTO> deleteUserById(@Validated @PathVariable(value = "id") String id ) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validated
    public ResponseEntity<UserDTO> updateUserById(@Validated @PathVariable(value = "id") String id,
                                                  @Validated @RequestBody UpdateUserRequest req) {
        return  new ResponseEntity<>(userService.updateUser(id, req), HttpStatus.OK);
    }


}
