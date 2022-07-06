package com.huyraw.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.huyraw.demo.util.constant.UserRole;
import com.huyraw.demo.util.constant.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "userId", updatable = false)
    @Schema(description = "User UUID in  the database")
    @JsonProperty("id")
    private UUID id;

    @Column(nullable = false)
    @Schema(description = "Full name of user")
    private String fullName;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    private String email;


    @Schema(description = "Birthday")
    @Column(name = "birthday", nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private UserStatus status;


    @Column(nullable = false)
    private UserRole role;



}
