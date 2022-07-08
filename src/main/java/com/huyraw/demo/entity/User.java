package com.huyraw.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;



import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;



@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User  {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "Id", updatable = false)
    @Schema(description = "User Id in  the database")
    @JsonProperty("id")
    private String id;

    @Column(nullable = false)
    @Schema(description = "Full name of user")
    private String fullName;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    private String email;


    @Schema(description = "Birthday")
    @Past(message = "birthday must be a date value in the past")
    @Column(name = "birthday", nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private UserStatus status;


    @Column(nullable = false)
    private UserRole role;

    public User(final String fullName, final String password, final String email, final LocalDate dob, final UserStatus status, final UserRole role) {
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.status = status;
        this.role = role;
    }


}
