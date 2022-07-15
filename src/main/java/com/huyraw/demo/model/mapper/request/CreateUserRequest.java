package com.huyraw.demo.model.mapper.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.huyraw.demo.util.annotations.validator.Birthday;
import com.huyraw.demo.util.annotations.validator.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import javax.validation.constraints.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
    @Password
    @Schema(
            example="verysecretpassword",
            description="Password can't be empty",
            required=true
    )
    private String password;

    @Birthday()
    @NotNull
    @Schema(
            example = "19/09/2001",
            required = true,
            description = "dd/MM/yyyy"
    )
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}")
    @JsonProperty("birthday")
    @Past(message = "birthday must be a date value in the past")
    private String dob;


}
