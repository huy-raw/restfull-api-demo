package com.huyraw.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

    @Schema(description = "Status of Book")
    @Column(name = "isDelete")
    private Boolean status;


//    @Column(name = "image of Book")
//    private String image;

    public Book(String title, String author, LocalDate importDate, Double price, Integer quantity){
        this.title = title;
        this.author = author;
        this.importDate = importDate;
        this.price = price;
        this.quantity = quantity;
        this.status = true;
    }

    public LocalDate getImportDate(){
        return LocalDate.now();
    }


}
