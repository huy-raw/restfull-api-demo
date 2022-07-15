package com.huyraw.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huyraw.demo.util.annotations.validator.Password;
import com.huyraw.demo.util.constants.UserRole;
import com.huyraw.demo.util.constants.UserStatus;
import lombok.*;
import org.jetbrains.annotations.NotNull;


import javax.validation.constraints.Email;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {


    private String id;

    @NotNull
    private String name;


    @JsonIgnore()
    @Password
    private String password;


    @NotNull
    private String dob;

    @Email()
    private String email;

    @NotNull
    private UserRole role;

    @NotNull
    private UserStatus status;



}
