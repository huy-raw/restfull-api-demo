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

    @Column()
    private String title;

    @Column()
    private String author;

    @Column()
    private LocalDate importDate;

    @Column()
    private Double price;

    @Column
    private Integer quantity;

    @Column(name = "isDelete")
    private Boolean status;

    public LocalDate getImportDate(){
        return LocalDate.now();
    }

}
