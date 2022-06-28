package com.huyraw.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByName(book.getTitle());

        if (bookOptional.isPresent()) throw new IllegalStateException("Book exist");

        bookRepository.save(book);
        return book;
    }

    public void deleteBook(Long id) {
        boolean exist = bookRepository.existsById(id);
        if (!exist) throw new IllegalStateException("Book have id " + id + " don't exist");
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
