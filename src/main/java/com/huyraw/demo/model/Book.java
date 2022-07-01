package com.huyraw.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book")
@Entity
public class Book {
    @Id
    @SequenceGenerator(
            name= "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "book_sequence"
    )
    private Long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column()
    private LocalDate importDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "isDelete")
    private Boolean status;

    public LocalDate getImportDate(){
        return LocalDate.now();
    }

}
