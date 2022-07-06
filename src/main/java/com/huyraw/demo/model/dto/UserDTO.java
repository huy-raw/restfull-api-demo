package com.huyraw.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    @NotNull
    private String id;

    @NotNull
    private String name;


    @JsonIgnore()
    private String password;

    @DateTimeFormat
    @Past(message = "birthday must be a date value in the past")
    private LocalDate dob;

    @Email()
    private String email;

    @NotNull
    private UserRole role;

    @NotNull
    private UserStatus status;

}
