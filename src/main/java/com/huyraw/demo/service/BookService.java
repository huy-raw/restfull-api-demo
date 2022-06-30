package com.huyraw.demo.service;

import com.huyraw.demo.repository.BookRepository;
import com.huyraw.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        log.info("Get all books");
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Book have id" + id + " does not exist"));
    }

    public Book addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByName(book.getTitle());

        if (bookOptional.isPresent()) {
            log.info("Book doesn't exist");
            throw new IllegalStateException("Book exist");
        }

        bookRepository.save(book);
        return book;
    }

    public void deleteBook(Long id) {
        boolean exist = bookRepository.existsById(id);
        if (!exist) {
            log.info(("Delete book failed"));
            throw new IllegalStateException("Book have id " + id + " don't exist");
        }
        System.out.printf("Id: " + id);

        bookRepository.deleteById(id);

    }

    @Transactional
    public void updateBook(Long id, Book book){
        Book bookOptional = bookRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Book have id" + id + " does not exist"));

        if(book.getTitle() != null
                && !bookOptional.getTitle().equalsIgnoreCase(book.getTitle())
                && book.getTitle().length() > 0){
            bookOptional.setTitle(book.getTitle());
        }

        if(book.getAuthor() != null
                && !bookOptional.getAuthor().equalsIgnoreCase(book.getAuthor())
                && book.getAuthor().length() > 0
        ){
            bookOptional.setAuthor(book.getAuthor());
        }

        if(!bookOptional.getPrice().equals(book.getPrice())
                && book.getPrice() != null
        ){
            bookOptional.setPrice(book.getPrice());
        }

        if(!bookOptional.getQuantity().equals(book.getQuantity())
                && book.getQuantity() != null
        ){
            bookOptional.setQuantity(book.getQuantity());
        }

        bookRepository.save(bookOptional);
    }
}
