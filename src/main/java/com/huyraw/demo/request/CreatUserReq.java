package com.huyraw.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatUserReq {
    private String userName;
    private String password;
    private String email;
    private LocalDate dob;
}
