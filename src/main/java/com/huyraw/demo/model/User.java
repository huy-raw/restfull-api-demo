package com.huyraw.demo.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(name = "userId", updatable = false, nullable = false )
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(name = "status", nullable = false)
    private Status status;

    private Role role;

    @Transient
    private Integer age;


    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }


    enum Role {
        USER,
        ADMIN
    }

    enum Status {
        ACTIVE,
        INACTIVE
    }
}
