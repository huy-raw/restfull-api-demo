package com.huyraw.demo.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping(path = "api/book")
public class BookController {


    private final  BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping(value = "/")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    @PostMapping(value = "/")
    @ResponseStatus
    public Book importNewBook(@RequestBody Book book){
        return bookService.addNewBook(book);

    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus
    public void deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
    }

    @PutMapping(path = "/{id}")
    public void updateBook(
        @PathVariable("id") Long id,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String author,
        @RequestParam(required = false) Double price,
        @RequestParam(required = false) Integer quantity
    ){
        bookService.updateBook(id, title, author, price, quantity);
    }
}
