package com.huyraw.demo.model.mapper.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {
    @NotNull(message = "Full name is required")
    @NotEmpty(message = "Full name is required")
    @Schema(
            example="Minh Beo",
            description="Full name cannot be empty",
            required=true
    )
    @JsonProperty("full_name")
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @Schema(
            example="example@gmail.com",
            description="Email cannot be empty",
            required=true
    )
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Schema(
            example="verysecretpassword",
            description="Password can't be empty",
            required=true
    )
    private String password;

    @DateTimeFormat()
    @NotNull
    @Schema(
            example = "2001-09-19",
            required = true,
            description = "YYYY-MM-DD"
    )
    @JsonProperty("birthday")
    private LocalDate dob;


}
