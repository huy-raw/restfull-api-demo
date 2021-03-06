package com.huyraw.demo.model.mapper.request;

import com.huyraw.demo.util.annotations.validator.Birthday;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {
    @Nullable
    @Size(min = 3, max = 30)
    private String name;

    @Email
    @Nullable
    private String email;

    @Birthday
    @Nullable
    private String dob;
}
