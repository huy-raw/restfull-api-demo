package com.huyraw.demo.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
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
    private String title;
    private String author;
    private LocalDate importDate;
    private Double price;
    private Integer quantity;
    private Boolean status;

    public LocalDate getImportDate(){
        return LocalDate.now();
    }

}
