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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequest req) {
       userService.createUser(req);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(description = "Find user by id")
    public ResponseEntity<UserDTO> findUserById(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "/email")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(description = "Find user by email")
    public ResponseEntity<UserDTO> findUserByEmail(@RequestParam(value = "email") String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.FOUND);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable(value = "id") String id ) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDTO> updateUserById(@PathVariable(value = "id") String id,
                                                  @RequestBody UpdateUserRequest req) {
        return  new ResponseEntity<>(userService.updateUser(id, req), HttpStatus.OK);
    }


}
