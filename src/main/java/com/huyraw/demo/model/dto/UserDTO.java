package com.huyraw.demo.model.dto;

import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    private UUID id;
    private String name;
    private String password;
    private LocalDate dob;
    private String email;
    private UserRole role;
    private UserStatus status;

}
