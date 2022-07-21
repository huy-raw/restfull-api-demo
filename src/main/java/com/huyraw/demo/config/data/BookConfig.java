package com.huyraw.demo.config.data;

import com.huyraw.demo.entity.Book;
import com.huyraw.demo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookConfig {

    @Bean(name = "Book")
    CommandLineRunner commandLineRunner (BookRepository repository) {
        return  args -> {
            Book book1 = new
                    Book( 1L,"Connan", "Aoyama Gōshō", LocalDate.of(2022, 01, 01), 25000.0, 125, true);
            Book book2 = new
                    Book( 2L,"Doraemon", "Fujiko F. Fujio", LocalDate.of(2022, 01, 01), 55000.0, 100, true);
            Book book3 = new
                    Book( 3L,"Nhat ki trong tu", "Ho Chi Minh", LocalDate.of(2022, 01, 01), 55000.0, 200, true);
            Book book4 = new
                    Book( 4L,"Tokyo Ghost", "NoName", LocalDate.of(2022, 01, 01), 55000.0, 157, true);
            Book book5 = new
                    Book( 5L,"One Piece", "NoName", LocalDate.of(2022, 01, 01), 55000.0, 90, true);

            repository.saveAll(List.of(book1, book2, book3, book4, book5));
        };
    }
}
